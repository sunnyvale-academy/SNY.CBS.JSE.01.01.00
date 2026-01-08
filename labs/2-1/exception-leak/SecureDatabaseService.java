/**
 * SecureDatabaseService.java
 * 
 * This class demonstrates how to correctly handle exceptions by purging
 * sensitive information (Guideline 2-1).
 */
public class SecureDatabaseService {

    /**
     * Simulates a database connection attempt securely.
     * 
     * @param url      The database URL.
     * @param username The database user.
     * @param password The database password.
     * @throws Exception without sensitive info.
     */
    public void connect(String url, String username, String password) throws SafeDatabaseException {
        boolean connectionFailed = true;

        if (connectionFailed) {
            // Internal log (not seen by the caller of getMessage())
            String internalSecretLog = "Failed connection to " + url + " with password: " + password;

            // SECURE: Throw the safe exception.
            // The caller will see the generic message, but we keep the details for internal
            // debugging.
            throw new SafeDatabaseException(
                    "Database connection failed. Please contact your system administrator.",
                    internalSecretLog);
        }

        System.out.println("Connection successful (simulated).");
    }
}
