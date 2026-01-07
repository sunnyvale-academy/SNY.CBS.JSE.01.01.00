import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.File;

/**
 * SecurePrivilegedAction.java
 * 
 * This class demonstrates how to safely invoke doPrivileged (Guideline 9-3)
 * by validating all inputs BEFORE entering the privileged block.
 */
public class SecurePrivilegedAction {

    /**
     * Reads a file using elevated privileges, with input validation.
     * 
     * @param filename The path of the file to read.
     */
    @SuppressWarnings("removal")
    public void readFile(final String filename) {
        System.out.println("--- Secure Service ---");
        System.out.println("Requested file: " + filename);

        // SECURE: Validate the input BEFORE crossing the privilege boundary
        // with doPrivileged. We ensure the file is within a designated
        // 'config/' directory.
        if (filename == null || !filename.startsWith("config/")) {
            System.err.println("SECURITY ALERT: Unauthorized file read attempt outside trust zone!");
            throw new SecurityException("Access Denied: You are not authorized to read " + filename);
        }

        // Only after validation do we elevate our privileges to perform the
        // actual read operation.
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            try {
                File file = new File(filename);
                if (file.exists()) {
                    System.out.println("SUCCESS: Privileged read access granted for " + filename);
                } else {
                    System.out.println("INFO: File doesn't exist.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
            return null;
        });
        System.out.println("----------------------\n");
    }
}
