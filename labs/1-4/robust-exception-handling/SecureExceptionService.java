import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Guideline 1-4 (DOS-4): Robust exception handling.
 * 
 * SECURE: Generic error messages for users, detailed internal logging.
 */
public class SecureExceptionService {
    private static final Logger logger = Logger.getLogger(SecureExceptionService.class.getName());

    public String getUserData(String userId) {
        try {
            if ("invalid".equals(userId)) {
                throw new SQLException("Failed to connect to database at 10.0.0.5:5432 with user 'db_admin'", "08001");
            }
            return "User data for " + userId;
        } catch (SQLException e) {
            String correlationId = UUID.randomUUID().toString();

            // SECURE: Log the detailed, sensitive information internally
            logger.log(Level.SEVERE, "[ID:" + correlationId + "] Database connection failure", e);

            // SECURE: Return a generic, safe message to the user/caller
            return "Internal Server Error. Please contact support with Correlation ID: " + correlationId;
        } catch (Exception e) {
            String correlationId = UUID.randomUUID().toString();
            logger.log(Level.SEVERE, "[ID:" + correlationId + "] Unexpected service error", e);
            return "An unexpected error occurred. Reference ID: " + correlationId;
        }
    }

    public void processTask() {
        try {
            Object obj = null;
            obj.hashCode(); // Triggers NullPointerException
        } catch (RuntimeException e) {
            // SECURE: Robustly catch RuntimeExceptions to prevent service disruption
            logger.log(Level.WARNING, "Recovered from task error", e);
        }
    }
}
