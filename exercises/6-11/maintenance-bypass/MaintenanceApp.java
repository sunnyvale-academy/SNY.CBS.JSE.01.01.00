public class MaintenanceApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 6-11: The Maintenance Bypass ---");

        // 1. Initial State: Mode is false
        System.out.println("\n[1] Initial State:");
        MaintenanceManager.performAdministrativeAction("FORMAT DATABASE");

        // 2. Run the exploit
        System.out.println("\n[2] Triggering MaintenanceExploit...");
        MaintenanceExploit.enableMaintenance();

        // 3. Re-verify State: Mode is now true
        System.out.println("\n[3] Final State:");
        MaintenanceManager.performAdministrativeAction("FORMAT DATABASE");

        if (MaintenanceManager.maintenanceMode) {
            System.out.println("\nALERT: Administrative access hijacked! (Vulnerable)");
        } else {
            System.out.println("\nCheck: System state preserved. (Secure)");
        }
    }
}
