/**
 * VULNERABLE CLASS: VulnerableSession
 * Represents a user's authenticated session.
 * VIOLATION: Exposes a public constructor for a sensitive class.
 */
public class VulnerableSession implements Session {
    private final String userId;
    private final String role;

    // Guideline 7-1 Violation: Public constructor for a sensitive class.
    public VulnerableSession(String userId, String role) {
        this.userId = userId;
        this.role = role;
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
        return "VulnerableSession[User: " + userId + ", Role: " + role + "]";
    }
}
