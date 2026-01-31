/**
 * VULNERABLE CLASS: Gatekeeper
 * Controls access to a secure facility.
 * VIOLATION: Uses a public static final array for security-critical constants.
 */
public class Gatekeeper {
    // Guideline 6-9 Violation: Public static final arrays are still mutable!
    // An attacker can change the contents of this array.
    public static final String[] MASTER_KEYS = { "SUPER-SECRET-MASTER-001", "SUPER-SECRET-MASTER-002" };

    public static boolean checkKey(String key) {
        for (String masterKey : MASTER_KEYS) {
            if (masterKey.equals(key)) {
                return true;
            }
        }
        return false;
    }
}
