/**
 * VULNERABLE CLASS: UserProfile
 * Represents a sensitive user identity.
 * VIOLATION: Non-final class that doesn't proactively block cloning.
 */
public class UserProfile {
    private String username;
    private String role;

    public UserProfile(String username, String role) {
        System.out.println("UserProfile: [SECURE] Validating login for user: " + username);
        // Pretend there is sophisticated authentication logic here...
        this.username = username;
        this.role = role;
        System.out.println("UserProfile: [SECURE] Profile created for " + username + " with role " + role);
    }

    public void accessResource() {
        System.out.println("UserProfile: Accessing resource as " + username + " (" + role + ")");
    }
}
