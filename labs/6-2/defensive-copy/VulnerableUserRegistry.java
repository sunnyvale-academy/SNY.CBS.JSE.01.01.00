import java.util.ArrayList;
import java.util.List;

/**
 * VulnerableUserRegistry.java
 * 
 * This class demonstrates a mutability vulnerability (Guideline 6-2).
 * It exposes its internal mutable state directly to callers, allowing
 * them to modify the system's "source of truth".
 */
public class VulnerableUserRegistry {
    private List<String> users = new ArrayList<>();

    public VulnerableUserRegistry() {
        users.add("admin");
        users.add("manager");
    }

    /**
     * @return The internal list of users.
     */
    public List<String> getUsers() {
        // VULNERABILITY: Returning a reference to the same list object
        // used internally. The caller can now modify this list.
        return users;
    }
}
