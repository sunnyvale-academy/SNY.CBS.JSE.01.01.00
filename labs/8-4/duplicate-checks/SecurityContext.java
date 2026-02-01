/**
 * UTILITY: SecurityContext
 * Simulates a simple permission system using ThreadLocal.
 */
public class SecurityContext {
    private static final ThreadLocal<Boolean> hasPermission = ThreadLocal.withInitial(() -> false);

    public static void setPermission(boolean value) {
        hasPermission.set(value);
    }

    public static void checkPermission() {
        if (!hasPermission.get()) {
            throw new SecurityException("Permission Denied: User does NOT have 'READ_DOCUMENT' permission.");
        }
    }
}
