import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SecureUserRegistry.java
 * 
 * This class demonstrates how to protect internal state by creating
 * defensive copies or unmodifiable views of mutable output (Guideline 6-2).
 */
public class SecureUserRegistry {
    private List<String> users = new ArrayList<>();

    public SecureUserRegistry() {
        users.add("admin");
        users.add("manager");
    }

    /**
     * @return A safe view of the users list.
     */
    public List<String> getUsers() {
        // SECURE: Return an unmodifiable view of a defensive copy of the list.
        // This ensures that:
        // 1. The caller cannot modify the returned list (UnmodifiableList).
        // 2. The returned list is a snapshot that won't change even if the
        // internal 'users' list is modified later (new ArrayList).
        return Collections.unmodifiableList(new ArrayList<>(users));
    }
}
