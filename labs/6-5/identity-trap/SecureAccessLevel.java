/**
 * SECURE SOLUTION: Using an enum.
 * Enums in Java are guaranteed to have exactly one instance per constant.
 * Therefore, identity comparison (==) is safe and efficient.
 */
public enum SecureAccessLevel {
    ADMIN,
    GUEST;

    // In a real scenario, the GateService would now be:
    /*
     * public void enterVault(SecureAccessLevel level) {
     * if (level == SecureAccessLevel.GUEST) {
     * // This check is now 100% reliable.
     * }
     * }
     */
}
