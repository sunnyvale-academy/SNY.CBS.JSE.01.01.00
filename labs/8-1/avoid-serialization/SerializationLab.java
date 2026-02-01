public class SerializationLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 8-1: The Tampered Vault ---");

        try {
            // 1. Create a legitimate account
            SensitiveAccount account = new SensitiveAccount("ACCT-12345", 100);
            System.out.println("[1] Original: " + account);

            // 2. Attacker tamers with the serialization stream
            System.out.println("\n[2] Attacker intercepting and tampering with the serialized stream...");
            byte[] tamperedData = SerializeExploit.tamper(account);

            // 3. Deserializing the tampered data
            SensitiveAccount tamperedAccount = SerializeExploit.deserialize(tamperedData);
            System.out.println("\n[3] Result after deserialization: " + tamperedAccount);

            if (tamperedAccount.getBalance() == 999999) {
                System.out
                        .println("\nALERT: Serialization attack successful! Private state was modified. (Vulnerable)");
            }

        } catch (Exception e) {
            System.out.println("\nCheck: Serialization failed. " + e.getMessage());
        }
    }
}
