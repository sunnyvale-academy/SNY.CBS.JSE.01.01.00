import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exercise 1-4 (DOS-4): Robust exception handling.
 * 
 * SECURE: Generic error messages for users, detailed internal logging,
 * and guarded background workers.
 */
public class SecurePaymentService {
    private static final Logger logger = Logger.getLogger(SecurePaymentService.class.getName());

    public String processPayment(String cardId, double amount) {
        try {
            if (amount > 1000.0) {
                // In a real app, this would be a real technical failure
                throw new SQLException("External Payment Gateway 'GlobalPay' unreachable. " +
                        "Endpoint: https://api.globalpay.com/v1/charge, " +
                        "API_KEY: live_sk_4f92b10a9c8d7e6f", "GP001");
            }
            return "SUCCESS: Payment of $" + amount + " processed.";
        } catch (SQLException e) {
            String correlationId = UUID.randomUUID().toString();

            // SECURE: Log technical details internally
            logger.log(Level.SEVERE, "[PAYMENT-ERROR-ID:" + correlationId + "] Gateway Connection Failure", e);

            // SECURE: Return generic message to caller
            return "TRANSACTION FAILED: A technical error occurred. Reference ID: " + correlationId;
        } catch (Exception e) {
            String correlationId = UUID.randomUUID().toString();
            logger.log(Level.SEVERE, "[PAYMENT-ERROR-ID:" + correlationId + "] Unexpected Failure", e);
            return "TRANSACTION FAILED: Internal Service Error. Reference ID: " + correlationId;
        }
    }

    public void startAuditWorker() {
        System.out.println("[AuditWorker] Starting background audit...");

        Thread worker = new Thread(() -> {
            // SECURE: Even with a global handler, its good practice to try-catch
            // critical worker loops.
            try {
                String data = null;
                if (data.length() == 0) {
                    System.out.println("Audit logic...");
                }
            } catch (RuntimeException e) {
                // This will still trigger the Global Handler if re-thrown,
                // or we can log and recover here.
                throw e;
            }
        });

        worker.setName("SecurePaymentAuditThread");
        worker.start();
    }
}
