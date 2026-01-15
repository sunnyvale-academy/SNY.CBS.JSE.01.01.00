/**
 * Lab application demonstrating logging vulnerabilities and mitigations.
 */
public class LogApp {
    public static void main(String[] args) {
        String user = "alice_wonder";
        String ssn = "123-45-6789";
        String email = "alice@example.com";

        System.out.println("--- Lab 2-2: Shadow Logs ---");

        // 1. Demonstrate the vulnerability
        VulnerableLogger vLogger = new VulnerableLogger();
        System.out.println("\n[Action] Running Vulnerable Logger...");
        vLogger.logUserData(user, ssn, email);

        // 2. Demonstrate the secure fix
        SecureLogger sLogger = new SecureLogger();
        System.out.println("\n[Action] Running Secure Logger...");
        sLogger.logUserData(user, ssn, email);

        System.out.println("\nLab Complete: Evaluate your logs and ensure no sensitive PII is leaked.");
    }
}
