/**
 * A logger that incorrectly handles sensitive information.
 * VULNERABILITY: Logs PII (SSN) in plain text.
 */
public class VulnerableLogger {

    public void logUserData(String username, String ssn, String email) {
        // Logging sensitive PII directly for "visibility"
        System.out.println("[VULNERABLE LOG] User Activity: " + username +
                ", SSN: " + ssn +
                ", Email: " + email);
    }
}
