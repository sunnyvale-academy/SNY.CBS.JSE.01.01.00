import java.sql.SQLException;

/**
 * Exercise 1-4 (DOS-4): Robust exception handling.
 * 
 * VULNERABLE: Direct exposure of internal details via exceptions and
 * unhandled background crashes.
 */
public class VulnerablePaymentService {

    public String processPayment(String cardId, double amount) throws Exception {
        try {
            if (amount > 1000.0) {
                // Simulate an external API failure with sensitive credentials
                throw new SQLException("External Payment Gateway 'GlobalPay' unreachable. " +
                        "Endpoint: https://api.globalpay.com/v1/charge, " +
                        "API_KEY: live_sk_4f92b10a9c8d7e6f", "GP001");
            }
            return "SUCCESS: Payment of $" + amount + " processed.";
        } catch (Exception e) {
            // VULNERABLE: Leaking internal details to the caller
            return "TRANSACTION FAILED: " + e.getMessage();
        }
    }

    public void startAuditWorker() {
        System.out.println("[AuditWorker] Starting background audit...");

        Thread worker = new Thread(() -> {
            // VULNERABLE: No try-catch here means this thread will die silently
            // if an exception occurs.
            String data = null;
            if (data.length() == 0) { // Throws NPE
                System.out.println("Audit logic...");
            }
        });

        worker.setName("PaymentAuditThread");
        worker.start();
    }
}
