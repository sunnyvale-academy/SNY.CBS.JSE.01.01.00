/**
 * Main application to demonstrate the Masked Transactions exercise.
 */
public class TransactionApp {
    public static void main(String[] args) {
        VulnerableTransactionService service = new VulnerableTransactionService();

        System.out.println("--- Masked Transactions Exercise ---");

        // Simulating a transaction
        String account = "ACC-99821";
        String secretCard = "4532-1100-2299-8841";
        double amount = 149.99;

        System.out.println("[Action] Processing payment for " + account + "...");
        service.processPayment(account, secretCard, amount);

        System.out.println("\nSECURITY BREACH: The full credit card number was written to the audit log!");
    }
}
