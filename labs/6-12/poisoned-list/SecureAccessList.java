import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SECURE CLASS: SecureAccessList
 * FIX: Protects the collection by making it private and returning an
 * unmodifiable view.
 */
public class SecureAccessList {
    // FIX 1: Field is private.
    private static final List<String> ADMINS = new ArrayList<>(List.of("alice", "bob"));

    // FIX 2: Return an unmodifiable view for reading.
    public static List<String> getAdmins() {
        return Collections.unmodifiableList(ADMINS);
    }

    public static boolean isAdmin(String user) {
        return ADMINS.contains(user.toLowerCase());
    }

    public static void displayAdmins() {
        System.out.println("Secure Admins: " + ADMINS);
    }
}
