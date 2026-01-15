/**
 * Main application to demonstrate the Ghost Path vulnerability.
 */
public class FileApp {
    public static void main(String[] args) {
        VulnerableFileLoader loader = new VulnerableFileLoader();

        System.out.println("--- Ghost Paths Exercise ---");
        try {
            // Attempting to load a non-existent config file
            loader.loadConfig("secret_keys.properties");
        } catch (Exception e) {
            System.err.println("\n[!] CAUGHT EXCEPTION: " + e.getClass().getName());
            System.err.println("[!] MESSAGE: " + e.getMessage());

            System.out.println("\nSECURITY BREACH: Notice how the full internal system path was leaked above!");
        }
    }
}
