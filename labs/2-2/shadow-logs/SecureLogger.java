/**
 * A secure logger that sanitizes sensitive information.
 * SECURE: Masks PII before writing to logs.
 */
public class SecureLogger {

    public void logUserData(String username, String ssn, String email) {
        String maskedSsn = maskSsn(ssn);

        // SECURE: Only the masked version is logged
        System.out.println("[SECURE LOG] User Activity: " + username +
                ", SSN: " + maskedSsn +
                ", Email: " + email);
    }

    private String maskSsn(String ssn) {
        if (ssn == null)
            return "N/A";
        if (ssn.length() <= 4)
            return "****";

        // Return only the last 4 digits
        return "XXX-XX-" + ssn.substring(ssn.length() - 4);
    }
}
