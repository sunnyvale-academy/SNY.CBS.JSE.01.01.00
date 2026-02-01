/**
 * UTILITY: AccessControl
 * Simulates a role-based security context.
 */
public class AccessControl {
    private static final ThreadLocal<String> currentRole = ThreadLocal.withInitial(() -> "GUEST");

    public static void setCurrentRole(String role) {
        currentRole.set(role);
    }

    public static void checkRole(String requiredRole) {
        if (!requiredRole.equals(currentRole.get())) {
            throw new SecurityException("Access Denied: Required role '" + requiredRole +
                    "', but current role is '" + currentRole.get() + "'.");
        }
    }
}
