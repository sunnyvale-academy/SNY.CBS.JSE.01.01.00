import java.io.*;
import java.util.*;

public class BankApp {
    public static void main(String[] args) {
        System.out.println("--- Starting Legacy Bank Heist Demo ---");

        // 0. Trust Boundary
        UserSession session = new UserSession("malicious_user", true);
        System.out.println("User is admin: " + session.isAdmin());

        // 1. Integer Overflow
        Vault vault = new Vault();
        vault.deposit(Long.MAX_VALUE);
        vault.deposit(100);

        // 3. SQL Injection
        TransactionDB db = new TransactionDB();
        db.getLogs("fraud' OR '1'='1");

        // 4/6. Accessibility & Immutability
        UserAccount bob = new UserAccount("Bob", "secret123", 1000);
        bob.balance = 0; // Directly modified!
        bob.getLastLogin().setYear(70); // Modified internal state!

        // 5. Input Validation
        UserAccount alice = new UserAccount("Alice", "pass456", 500);
        TransferProcessor processor = new TransferProcessor();
        processor.transfer(alice, bob, -1000); // Alice "steals" from Bob
        System.out.println("Alice's balance: " + alice.balance);

        // 2. Exceptions
        BankService service = new BankService();
        try {
            service.performTransaction(null);
        } catch (Exception e) {
        }

        // 6. Mutable Collection (MUTABLE-12)
        System.out.println("\n[6] Mutating Roles...");
        bob.getRoles().add("ADMIN"); // Bob makes himself admin!
        System.out.println("Bob's roles: " + bob.getRoles());

        // 6. Lack of Defensive Copy (MUTABLE-3)
        System.out.println("\n[6] Hijacking Transaction History...");
        List<String> txs = new ArrayList<>();
        txs.add("Deposit 100");
        TransactionHistory history = new TransactionHistory(txs);
        txs.clear(); // History is now empty!
        history.showHistory();

        // 7. Constructor Trap (OBJ-4)
        System.out.println("\n[7] Triggering Constructor Trap...");
        BaseGateway gateway = new InsecureGateway();

        // 8. Unsafe Deserialization (SERIAL-4)
        System.out.println("\n[8] Bypassing checks via Deserialization...");
        try {
            ExportableReport report = new ExportableReport("Secret", "CONFIDENTIAL: Plan for heist");
            byte[] data = serialize(report);
            // Imagine we modify the data here to bypass some newer check...
            Object obj = deserialize(data);
            System.out.println("Recovered: " + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n--- End of Demo ---");
    }

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
        }
        return baos.toByteArray();
    }

    private static Object deserialize(byte[] data) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return ois.readObject();
        }
    }
}
