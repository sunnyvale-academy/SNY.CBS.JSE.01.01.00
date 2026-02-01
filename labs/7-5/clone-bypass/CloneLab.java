public class CloneLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 7-5: The Cloning Bypass ---");

        // 1. Legitimate user creates an instance
        System.out.println("\n[1] Admin creating a legitimate manager...");
        AuthService.setAuthorized(true);
        CloneExploit adminManager = new CloneExploit("SECRET-PROD-KEYS");
        AuthService.setAuthorized(false);

        // 2. Attacker wants their own instance without having permissions
        System.out.println("\n[2] Attacker attempting to clone the manager without authorization...");
        // AuthService.isAuthorized() is false, but clone() won't call the constructor!
        SensitiveManager stolenManager = CloneExploit.createClone(adminManager);

        if (stolenManager != null) {
            System.out.println("SUCCESS: Attacker obtained a sensitive manager clone!");
            stolenManager.showConfig();
            System.out.println("\nALERT: Constructor security check was bypassed via cloning! (Vulnerable)");
        } else {
            System.out.println("\nCheck: Clone failed. (Secure)");
        }
    }
}
