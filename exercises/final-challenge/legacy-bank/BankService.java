/**
 * VULNERABLE CLASS: BankService
 * Guidelines:
 * - CONFIDENTIAL-1 (2-1): Leak sensitive info in exceptions.
 */
public class BankService {
    public void performTransaction(String accountId) throws Exception {
        try {
            // Simulate some database logic
            if (accountId == null) {
                throw new java.sql.SQLException("Connection failed for DB at /internal/secure/db01");
            }
        } catch (Exception e) {
            // Vulnerability: Printing full stack trace to console/log
            // which might include server paths and database connection strings.
            e.printStackTrace();
            throw e;
        }
    }
}
