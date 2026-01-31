/**
 * VULNERABLE CLASS: SystemConfig
 * Contains "global constants" for the application.
 * VIOLATIONS:
 * 1. ENVIRONMENT is not final.
 * 2. ADMIN_IPS is a final field but refers to a mutable array.
 */
public class SystemConfig {
    // Guideline 6-9 Violation: Public static field is not final
    public static String ENVIRONMENT = "PRODUCTION";

    // Guideline 6-9 Violation: Public static final array is still mutable!
    public static final String[] ADMIN_IPS = { "127.0.0.1" };

    public static void display() {
        System.out.println("--- System Configuration ---");
        System.out.println("Environment: " + ENVIRONMENT);
        System.out.print("Admin IPs: ");
        for (String ip : ADMIN_IPS) {
            System.out.print(ip + " ");
        }
        System.out.println();
    }
}
