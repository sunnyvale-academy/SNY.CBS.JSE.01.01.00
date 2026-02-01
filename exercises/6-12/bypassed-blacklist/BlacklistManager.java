import java.util.HashSet;
import java.util.Set;

/**
 * VULNERABLE CLASS: BlacklistManager
 * Maintains a set of blacklisted IP addresses.
 * VIOLATION: Exposes a modifiable public static collection.
 */
public class BlacklistManager {
    // Guideline 6-12 Violation: Public static collection is modifiable!
    // An attacker can call .clear() or .remove() to unblock restricted IPs.
    public static final Set<String> BLACKLISTED_IPS = new HashSet<>(Set.of("192.168.1.100", "10.0.0.50"));

    public static boolean isAllowed(String ip) {
        return !BLACKLISTED_IPS.contains(ip);
    }

    public static void displayBlacklist() {
        System.out.println("Blacklisted IPs: " + BLACKLISTED_IPS);
    }
}
