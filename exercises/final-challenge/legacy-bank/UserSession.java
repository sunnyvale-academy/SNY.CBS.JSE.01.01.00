/**
 * VULNERABLE CLASS: UserSession
 * Guidelines:
 * - FUNDAMENTALS-4 (0-4): Blindly trusts an isAdmin flag from input.
 */
public class UserSession {
    private final String username;
    private boolean isAdmin;

    public UserSession(String username, boolean isAdmin) {
        this.username = username;
        // Vulnerability: Setting isAdmin directly from constructor argument
        // which might come from an untrusted source (e.g., a hidden HTML field).
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
