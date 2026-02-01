public class VaultApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 7-1: The Unauthorized Vault Key ---");

        // 1. Attacker forges a key
        System.out.println("\n[1] Attacker attempting to forge a MASTER key...");
        VaultKey key = KeyExploit.forgeMasterKey();

        // 2. Vault logic accepts the key because it's a valid VaultKey object
        System.out.println("\n[2] Presenting key to the Vault...");
        if ("MASTER".equals(key.getKeyType())) {
            System.out.println("VAULT STATUS: UNLOCKED! Access to classified data granted.");
            System.out.println("ALERT: Security bypass detected! (Vulnerable)");
        } else {
            System.out.println("VAULT STATUS: LOCKED. Invalid key type.");
            System.out.println("Check: Security enforced. (Secure)");
        }
    }
}
