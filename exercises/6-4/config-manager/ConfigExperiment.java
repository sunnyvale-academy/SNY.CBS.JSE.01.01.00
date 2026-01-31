import java.util.HashMap;
import java.util.Map;

public class ConfigExperiment {
    public static void main(String[] args) {
        System.out.println("--- Starting Exercise 6-4: Config Experiment ---");

        // 1. Setup Production Configuration
        SystemConfig productionConfig = new SystemConfig();
        productionConfig.setSetting("db_host", "prod-db.internal");
        productionConfig.setSetting("performance_mode", "standard");

        System.out.println("Production Config (Initial): " + productionConfig);

        // 2. Scenario: A developer wants to run an experiment with "high-performance"
        // mode.
        // They try to "copy" the production config.

        // MISTAKE: Since SystemConfig has no copy constructor or copyOf(),
        // the developer might try something like this (shallow copying it manually):
        SystemConfig experimentalConfig = productionConfig;

        System.out.println("\n--- Starting Experiment ---");
        experimentalConfig.setSetting("performance_mode", "EXTREME");

        System.out.println("Experimental Config: " + experimentalConfig);

        // 3. The catastrophe
        System.out.println("\n[Production Status Report]");
        System.out.println("Production Config (Final):   " + productionConfig);

        if ("EXTREME".equals(productionConfig.getSetting("performance_mode"))) {
            System.out.println(
                    "\nCRITICAL ALERT: Production settings were tampered with by the experiment! (Vulnerable)");
        } else {
            System.out.println("\nProduction settings are safe. (Secure)");
        }
    }
}
