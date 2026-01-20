import java.awt.color.ICC_Profile;
import java.io.File;
import java.io.IOException;

/**
 * Vulnerable image processor that trusts metadata references to local files.
 */
public class VulnerableGallery {

    public void processJpeg(File imageFile, String profilePath) {
        System.out.println("[Vulnerable] Processing JPEG: " + imageFile.getName());

        if (profilePath != null && !profilePath.isEmpty()) {
            System.out.println("   -> Found Color-Profile-Reference: " + profilePath);
            try {
                // VULNERABILITY: Directly passing an untrusted path from metadata
                // to a risky API that opens and reads the file.
                ICC_Profile profile = ICC_Profile.getInstance(profilePath);
                System.out.println("   -> Applied color profile.");
            } catch (IllegalArgumentException e) {
                // INFO LEAK: The specific message "Invalid ICC Profile Data"
                // confirms the file exists but isn't a valid profile.
                System.out.println("   !!! ERROR: " + e.getMessage());
                if (e.getMessage().contains("Invalid ICC Profile Data")) {
                    System.out.println("File format not valid: " + profilePath);
                }
            } catch (Exception e) {
                System.out.println("   -> Failed to load profile: " + e.getMessage());
            }
        } else {
            System.out.println("   -> No external color profile referenced.");
        }
    }
}
