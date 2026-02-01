public class TransferApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 7-3: The Ghost Transfer ---");

        // 1. Attacker attempts to create an unauthorized transfer
        System.out.println("\n[1] Starting Finalizer Attack...");
        GhostTransferExploit.runAttack();

        // 2. Check if the exploit captured the "ghost" object
        System.out.println("\n[2] Verifying if exploit captured unauthorized object...");
        if (GhostTransferExploit.capturedGhost != null) {
            System.out.println("SUCCESS: Captured unauthorized Ghost Transfer object!");

            // 3. Using the forbidden object!
            System.out.println("\n[3] Manipulating the 'Ghost' object:");
            GhostTransferExploit.capturedGhost.process();

            System.out.println("\nALERT: Finalizer Attack successful! System compromised. (Vulnerable)");
        } else {
            System.out.println("\nCheck: No ghost object captured. (Secure)");
        }
    }
}
