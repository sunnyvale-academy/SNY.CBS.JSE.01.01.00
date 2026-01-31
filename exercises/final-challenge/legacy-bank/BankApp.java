import java.io.File;

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

        // 2/9. Exceptions & doPrivileged
        InternalLogViewer logViewer = new InternalLogViewer();
        logViewer.readLog("/etc/passwd"); // Attempt traversal

        BankService service = new BankService();
        try {
            service.performTransaction(null);
        } catch (Exception e) {
        }

        System.out.println("--- End of Demo ---");
    }
}
