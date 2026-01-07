import java.io.File;
import java.io.IOException;

/**
 * SecureTrustBoundary.java
 * 
 * This class demonstrates how to correctly establish a trust boundary
 * (Guideline 0-4).
 * It refuses to trust the input without validation and ensures that operations
 * stay
 * within the designated security zone.
 */
public class SecureTrustBoundary {

    /**
     * Processes a file located in the 'uploads' directory, with boundary
     * validation.
     * 
     * @param filename The name of the file to process.
     * @throws IOException If path resolution fails.
     */
    public void processDataFile(String filename) throws IOException {
        // Step 1: Define the trusted base directory and canonicalize it
        // Canonicalization resolves all symlinks and '..' segments.
        File baseDir = new File("uploads").getCanonicalFile();

        // Step 2: Resolve the full path of the requested file and canonicalize it
        File file = new File(baseDir, filename).getCanonicalFile();

        System.out.println("--- Secure Service ---");
        System.out.println("Requested filename: " + filename);

        // SECURE: Explicitly establish a trust boundary by validating that
        // the resulting path is still within the expected base directory.
        if (!file.getPath().startsWith(baseDir.getPath())) {
            System.err.println("SECURITY ALERT: Trust boundary violation detected!");
            System.err.println("Attempted path: " + file.getPath());
            System.err.println("Required base: " + baseDir.getPath());
            throw new SecurityException("Insecure file path detected: Access denied.");
        }

        System.out.println("Resolved path: " + file.getPath());

        if (file.exists()) {
            System.out.println("SUCCESS: Accessing file. Sensitive operation performed.");
        } else {
            System.out.println("INFO: File not found.");
        }
        System.out.println("----------------------\n");
    }
}
