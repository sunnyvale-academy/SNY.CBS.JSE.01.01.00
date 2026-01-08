import java.io.File;

/**
 * VulnerableTrustBoundary.java
 * 
 * This class demonstrates a failure to establish a trust boundary (Guideline 0-4).
 * It trusts that the input provided to it is already in a safe format or has been
 * validated by a previous component, which is a dangerous assumption.
 */
public class VulnerableTrustBoundary {
    
    /**
     * Processes a file located in the 'uploads' directory.
     * 
     * @param filename The name of the file to process.
     */
    public void processDataFile(String filename) {
        // VULNERABILITY: This method assumes the 'filename' is just a simple name
        // and doesn't contain path traversal characters like '../'.
        // It fails to establish its own trust boundary check, relying on the
        // caller (which might be an untrusted source) to be "well-behaved".
        File file = new File("uploads/" + filename);
        
        System.out.println("--- Vulnerable Service ---");
        System.out.println("Requested filename: " + filename);
        System.out.println("Resolved path: " + file.getPath());
        
        // In a real scenario, this would read/write sensitive data.
        // For demonstration, we just check if the file exists.
        if (file.exists()) {
            System.out.println("SUCCESS: Accessing file. Sensitive operation performed.");
        } else {
            System.out.println("INFO: File not found (expected for non-existent system files in this lab environment).");
        }
        System.out.println("--------------------------\n");
    }
}
