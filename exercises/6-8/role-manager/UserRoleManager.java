import java.util.ArrayList;
import java.util.List;

/**
 * VULNERABLE CLASS: UserRoleManager
 * Manages user roles for an application.
 * VIOLATION: Exposes the internal mutable list of roles directly.
 */
public class UserRoleManager {
    private final List<String> roles = new ArrayList<>();

    public UserRoleManager() {
        // Everyone starts with the basic USER role
        roles.add("USER");
    }

    /**
     * Adds a new role to the user.
     * BUSINESS LOGIC: Prevents regular users from gaining ADMIN privileges.
     */
    public void addRole(String role) {
        if ("ADMIN".equalsIgnoreCase(role)) {
            System.out.println("SECURITY ALERT: Cannot manually add ADMIN role!");
            return;
        }
        roles.add(role.toUpperCase());
    }

    /**
     * VULNERABILITY: Returns the actual internal list reference.
     * Guideline 6-8 Violation: Modifiable internal state should be wrapped.
     */
    public List<String> getRoles() {
        return roles;
    }

    public boolean hasAdminPrivileges() {
        return roles.contains("ADMIN");
    }
}
