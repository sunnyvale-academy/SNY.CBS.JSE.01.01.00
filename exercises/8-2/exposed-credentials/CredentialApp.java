public class CredentialApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 8-2: The Exposed Credentials ---");

        try {
            // 1. User logs in and a credential object is created
            System.out.println("[1] Creating credentials for user 'admin'...");
            UserCredentials creds = new UserCredentials("admin", "topSecret123!");

            System.out.println("Object state: " + creds);

            // 2. An attacker (or a malicious plugin) sniffs the serialized stream
            System.out.println("\n[2] Attacker sniffing the serialized session data...");
            String leakedPass = CredentialSniffer.sniffPassword(creds);

            System.out.println("\n[3] Leak Verification:");
            System.out.println("FOUND PASSWORD: " + leakedPass);

            if (leakedPass.contains("topSecret")) {
                System.out.println("\nALERT: Sensitive password leaked via serialization! (Vulnerable)");
            }

        } catch (Exception e) {
            System.out.println("\nCheck: Experiment failed. " + e.getMessage());
        }
    }
}
