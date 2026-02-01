import java.util.ArrayList;
import java.util.List;

/**
 * VULNERABLE CLASS: AccessList
 * Manages a list of administrative users.
 * VIOLATION: Exposes a modifiable public static collection.
 */
public class AccessList {
    // Guideline 6-12 Violation: Public static collection is modifiable!
    // Even though the field is final, the List object and its elements are not.
    public static final List<String> ADMINS = new ArrayList<>(List.of("alice", "bob"));

    public static boolean isAdmin(String user) {
        return ADMINS.contains(user.toLowerCase());
    }

    public static void displayAdmins() {
        System.out.println("Current Admins: " + ADMINS);
    }
}
