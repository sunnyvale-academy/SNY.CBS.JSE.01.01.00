import java.util.concurrent.atomic.AtomicInteger;

/**
 * SECURE CLASS: SecureSecurityMonitor
 * Encapsulates static state to prevent unauthorized tampering.
 */
public class SecureSecurityMonitor {
    // FIX 1: Field is private to prevent direct external access.
    // FIX 2: Using AtomicInteger for thread-safety (though not strictly required
    // for this demo).
    private static final AtomicInteger failureCount = new AtomicInteger(0);

    private static final int MAX_ATTEMPTS = 3;

    /**
     * Controlled update: Only allows incrementing, never resetting.
     */
    public static void recordFailure() {
        failureCount.incrementAndGet();
    }

    public static boolean isLockedOut() {
        return failureCount.get() >= MAX_ATTEMPTS;
    }

    public static void displayStatus() {
        System.out.println(
                "Secure Security Status: [Failures: " + failureCount.get() + ", Locked: " + isLockedOut() + "]");
    }

    // NOTE: We do NOT provide a reset method unless it's properly protected
    // by authentication or other security checks.
}
