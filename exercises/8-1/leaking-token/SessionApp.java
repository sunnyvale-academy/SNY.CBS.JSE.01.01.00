public class SessionApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 8-1: The Leaking Token ---");

        try {
            // 1. System generates a sensitive session token
            System.out.println("[1] Generating a session token with a hidden secret...");
            SessionToken token = new SessionToken("Alice", "SECRET-JWT-999LX");
            System.out.println("Session created: " + token);

            // 2. Attacker sniffs the token (e.g., from a cache, log, or wire)
            System.out.println("\n[2] Attacker intercepting the token stream...");
            String leakedSecret = TokenSniffer.extractSecretFromStream(token);

            System.out.println("\n[3] Information Disclosure Result:");
            System.out.println("LEAKED SECRET: " + leakedSecret);

            if (leakedSecret.startsWith("SECRET-")) {
                System.out.println("\nALERT: Private field value leaked via serialization! (Vulnerable)");
            }

        } catch (Exception e) {
            System.out.println("\nCheck: Attack failed. " + e.getMessage());
        }
    }
}
