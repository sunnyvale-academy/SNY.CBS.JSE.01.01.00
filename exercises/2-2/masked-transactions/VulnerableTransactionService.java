/**
 * A service that processes financial transactions.
 * VULNERABILITY: Logs full credit card numbers to the audit log.
 */
public class VulnerableTransactionService {

    public void processPayment(String accountId, String cardNumber, double amount) {
        // Business logic for payment...
        boolean success = true;

        if (success) {
            // VULNERABILITY: Logging the full credit card number
            System.out.println("[AUDIT LOG] Transaction successful. " +
                    "Account: " + accountId +
                    ", Card: " + cardNumber +
                    ", Amount: $" + amount);
        }
    }
}
