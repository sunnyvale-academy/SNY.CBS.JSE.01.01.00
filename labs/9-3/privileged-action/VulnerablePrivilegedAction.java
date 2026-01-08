import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.File;

/**
 * VulnerablePrivilegedAction.java
 * 
 * This class demonstrates a vulnerability in how elevated privileges are used
 * (Guideline 9-3).
 * It performs a sensitive action (reading a file) inside a doPrivileged block,
 * but
 * uses an unvalidated parameter from the caller, effectively granting the
 * caller
 * the application's full permissions.
 */
public class VulnerablePrivilegedAction {

    /**
     * Reads a file using elevated privileges.
     * 
     * @param filename The path of the file to read (untrusted input).
     */
    @SuppressWarnings("removal") // doPrivileged is deprecated but required for this guideline lab
    public void readFile(final String filename) {
        System.out.println("--- Vulnerable Service ---");
        System.out.println("Requested file: " + filename);

        // VULNERABILITY: Entering a privileged block without validating the input.
        // This allows an untrusted caller to read any file the JVM has access to.
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            try {
                // In a real system with a SecurityManager, the application might have
                // permission to read /etc/passwd while the caller does not.
                // doPrivileged ignores the caller's restricted permissions.
                File file = new File(filename);
                if (file.exists()) {
                    System.out.println("SUCCESS: Privileged read access granted for " + filename);
                } else {
                    System.out.println("INFO: File doesn't exist, but permission check passed.");
                }
            } catch (Exception e) {
                System.err.println("Error during privileged operation: " + e.getMessage());
            }
            return null;
        });
        System.out.println("--------------------------\n");
    }
}
