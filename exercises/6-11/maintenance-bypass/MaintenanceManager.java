/**
 * VULNERABLE CLASS: MaintenanceManager
 * Manages system maintenance state.
 * VIOLATION: maintenanceMode is a public static mutable field.
 */
public class MaintenanceManager {
    // Guideline 6-11 Violation: Public static mutable field.
    // This flag controls administrative access and can be flipped by anyone.
    public static boolean maintenanceMode = false;

    public static void performAdministrativeAction(String action) {
        if (maintenanceMode) {
            System.out.println("ADMISSION GRANTED: Maintenance mode is active. Executing: " + action);
        } else {
            System.out.println("ADMISSION DENIED: Administrative access requires Maintenance Mode.");
        }
    }
}
