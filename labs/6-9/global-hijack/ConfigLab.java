public class ConfigLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 6-9: The Global Hijack ---");

        System.out.println("\nInitial Configuration:");
        SystemConfig.display();

        // Simulate an untrusted plugin or module running
        System.out.println("\nRunning untrusted module...");
        ConfigExploit.hijack();

        System.out.println("\nVerification of System State:");
        SystemConfig.display();

        if ("PRODUCTION".equals(SystemConfig.ENVIRONMENT)) {
            System.out.println("\nCheck: Global state integrity preserved? YES (Secure)");
        } else {
            System.out.println("\nCheck: Global state integrity preserved? NO (Vulnerable)");
        }
    }
}
