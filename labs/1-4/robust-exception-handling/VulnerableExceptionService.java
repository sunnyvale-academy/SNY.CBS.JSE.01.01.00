import java.sql.SQLException;

/**
 * Guideline 1-4 (DOS-4): Robust exception handling.
 * 
 * VULNERABLE: Direct exposure of internal details via exceptions.
 */
public class VulnerableExceptionService {

    public String getUserData(String userId) throws Exception {
        try {
            if ("invalid".equals(userId)) {
                // Simulate a database error with sensitive connection info
                throw new SQLException("Failed to connect to database at 10.0.0.5:5432 with user 'db_admin'", "08001");
            }
            return "User data for " + userId;
        } catch (Exception e) {
            // VULNERABLE: Directly returning the exception message or stack trace to the
            // user
            return "ERROR: " + e.toString();
        }
    }

    public void processTask() {
        // VULNERABLE: This code doesn't handle RuntimeExceptions, which might crash the
        // calling thread
        Object obj = null;
        obj.hashCode(); // Triggers NullPointerException
    }
}
