import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Driver app for Lab 3-6: Care with BMP files.
 */
public class BmpApp {
    public static void main(String[] args) throws IOException {
        System.out.println("--- Lab 3-6: The Color Profile Leak ---");

        // 1. Create a "malicious" BMP (simulated)
        // A real malicious BMP would have a Color Space Type set to LCS_PROFILE
        // and a path to a local file in the header.
        File dummyBmp = new File("untrusted_image.bmp");

        VulnerableImageProcessor vulnerable = new VulnerableImageProcessor();
        SecureImageProcessor secure = new SecureImageProcessor();

        // 1. Create a simple BMP
        createDummyBmp(dummyBmp, "/tmp/not_a_profile.icc");

        System.out.println("\n[Scenario] Processing a BMP with an ICC profile reference.");
        vulnerable.processImage(dummyBmp);

        // 2. DIRECT PROOF: The Risky API
        // Oracle Guideline 3-6 warns about BMPs because they can trigger this:
        System.out.println("\n[PROOF] Demonstrating the risky API used by image parsers:");

        System.out.println("\nCase A: Target file EXISTS (e.g., /etc/passwd)");
        demonstrateRiskyApi("/etc/passwd");

        System.out.println("\nCase B: Target file does NOT exist (e.g., /nonexistent_file)");
        demonstrateRiskyApi("/nonexistent_file");

        // 3. SECURE APPROACH
        System.out.println("\n[Secure] Starting processing...");
        secure.processImage(dummyBmp);

        // Cleanup
        dummyBmp.delete();
    }

    /**
     * Directly demonstrates why Guideline 3-6 exists.
     * BMP/JPEG parsers can be tricked into calling this API with arbitrary paths.
     */
    private static void demonstrateRiskyApi(String path) {
        System.out.println("   -> Attempting to read color profile from: " + path);
        try {
            // This is the call that happens internally during BMP/JPEG parsing.
            // It will attempt to OPEN and READ the file.
            java.awt.color.ICC_Profile.getInstance(path);
            System.out.println("   -> Unexpectedly loaded a profile (this should not happen with /etc/passwd)");
        } catch (IllegalArgumentException e) {
            // In most JDKs, if the file exists but isn't a profile, you get "Invalid ICC
            // Profile Data"
            // If the file DOES NOT exist, you get a different message or even a null
            // pointer internally.
            System.out.println("   -> Caught IllegalArgumentException: " + e.getMessage());

            if (e.getMessage().equals("Invalid ICC Profile Data")) {
                System.out.println("   !!! INFO LEAK: This specific message confirms the file EXISTS on the system.");
            } else {
                System.out.println("   -> This message suggests the file was NOT found or could not be accessed.");
            }
        } catch (Exception e) {
            System.out.println("   -> Caught " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    private static void createDummyBmp(File file, String path) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // BMP File Header (14 bytes)
            fos.write(new byte[] { 'B', 'M' });
            int fileSize = 14 + 124 + path.length() + 1;
            fos.write(intToByte(fileSize));
            fos.write(new byte[] { 0, 0, 0, 0 });
            fos.write(intToByte(14 + 124));

            // BITMAPV5HEADER (124 bytes)
            byte[] v5Header = new byte[124];
            System.arraycopy(intToByte(124), 0, v5Header, 0, 4);
            System.arraycopy(intToByte(1), 0, v5Header, 4, 4);
            System.arraycopy(intToByte(1), 0, v5Header, 8, 4);
            v5Header[12] = 1;
            v5Header[13] = 0;
            v5Header[14] = 24;
            v5Header[15] = 0;

            // bV5CSType = 'PROF' (LCS_PROFILE = 0x50524F46)
            // BMP is little-endian, so we write 0x46, 0x4F, 0x52, 0x50
            v5Header[70] = 0x46;
            v5Header[71] = 0x4F;
            v5Header[72] = 0x52;
            v5Header[73] = 0x50;
            v5Header[73] = 'F';

            // Profile Data Offset and Size
            System.arraycopy(intToByte(124), 0, v5Header, 116, 4);
            System.arraycopy(intToByte(path.length()), 0, v5Header, 120, 4);

            fos.write(v5Header);
            fos.write(path.getBytes());
            fos.write(0);
        }
    }

    private static byte[] intToByte(int value) {
        return new byte[] {
                (byte) (value & 0xFF),
                (byte) ((value >> 8) & 0xFF),
                (byte) ((value >> 16) & 0xFF),
                (byte) ((value >> 24) & 0xFF)
        };
    }
}
