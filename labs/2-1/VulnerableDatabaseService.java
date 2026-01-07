/**
 * VulnerableDatabaseService.java
 * 
 * This class demonstrates a failure to purge sensitive information from
 * exceptions (Guideline 2-1). Leaking sensitive data like passwords or
 * internal file paths in error messages can help an attacker.
 */
public class VulnerableDatabaseService {

    /**
     * Simulates a database connection attempt.
     * 
     * @param url      The database URL.
     * @param username The database user.
     * @param password The database password.
     * @throws Exception with sensitive info in the message.
     */
    public void connect(String url, String username, String password) throws Exception {
        // Simulating a connection failure (e.g., driver not found or network error)
        boolean connectionFailed = true;

        if (connectionFailed) {
            // VULNERABILITY: The exception message contains the plain-text password.
            // This message might be logged to a file, displayed in a UI, or
            // returned in a network response, exposing the secret.
            throw new Exception("Connection failed for '" + url +
                    "' using credentials [" + username + ":" + password + "]");
        }

        System.out.println("Connection successful (simulated).");
    }
}
