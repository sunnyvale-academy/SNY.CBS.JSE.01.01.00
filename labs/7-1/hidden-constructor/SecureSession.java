/**
 * SECURE CLASS: SecureSession
 * FIX: Uses a private constructor and a static factory method.
 */
public class SecureSession implements Session {
    private final String userId;
    private final String role;

    // FIX: Private constructor prevents direct instantiation.
    private SecureSession(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    /**
     * SECURE FACTOR METHOD: Only way to create a session.
     */
    public static SecureSession createSession(String userId, String token) {
        if ("SECRET-VAL-123".equals(token)) {
            return new SecureSession(userId, "USER");
        }
        return null;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "SecureSession[User: " + userId + ", Role: " + role + "]";
    }
}
