/**
 * VULNERABLE CLASS: SecurityMonitor
 * Tracks failed login attempts to prevent brute-force attacks.
 * VIOLATION: failureCount is a public static mutable field.
 */
public class SecurityMonitor {
    // Guideline 6-11 Violation: Public static mutable field.
    // Any module in the system can read or write to this counter.
    public static int failureCount = 0;

    public static final int MAX_ATTEMPTS = 3;

    public static boolean isLockedOut() {
        return failureCount >= MAX_ATTEMPTS;
    }

    public static void displayStatus() {
        System.out.println("Security Status: [Failures: " + failureCount + ", Locked: " + isLockedOut() + "]");
    }
}
