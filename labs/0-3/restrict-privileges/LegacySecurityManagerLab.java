import java.io.File;
import java.io.FileOutputStream;

/**
 * Guideline 0-3 (FUNDAMENTALS-3): Restrict privileges.
 * 
 * Demonstration of the legacy SecurityManager approach.
 */
public class LegacySecurityManagerLab {
    public static void main(String[] args) {
        System.out.println("--- Legacy Security Manager Lab ---");

        // The SecurityManager is deprecated and might not be enabled by default.
        // In modern JDKs, it often needs to be enabled via -Djava.security.manager

        System.out.println("Current Security Manager: " + System.getSecurityManager());

        try {
            System.out.println("Attempting to write to a sensitive file...");
            File file = new File("privileged_action.txt");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write("This should be restricted".getBytes());
            }
            System.out.println("SUCCESS: File written (unexpected if restricted).");
        } catch (SecurityException e) {
            System.err.println("BLOCKED: Security Manager prevented the write: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERROR: " + e.toString());
        } finally {
            new File("privileged_action.txt").delete();
        }

        System.out.println("\nNOTE: SecurityManager is deprecated for removal in modern Java.");
    }
}
