/**
 * Driver app for The Vanishing Key exercise.
 */
public class KeyApp {
    public static void main(String[] args) {
        String secret = "AES_KEY_88291001";

        System.out.println("--- Exercise 2-3: The Vanishing Key ---");

        // Vulnerable Version
        VulnerableKeyService vService = new VulnerableKeyService();
        System.out.println("\n[Action] Running Vulnerable Key Service...");
        vService.setKey(secret);
        vService.encryptData("Some sensitive data");
        System.out.println("[INFO] Key was nulled in service, but still exists in the Heap String Pool.");

        // Note: Students are expected to implement the Secure version.
        System.out.println("\nNext step: Implement SecureKeyService using mutable buffers and explicit purging!");
    }
}
