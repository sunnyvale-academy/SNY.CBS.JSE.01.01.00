public class SecurityApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 6-12: The Bypassed Blacklist ---");

        String blockedIP = "192.168.1.100";

        // 1. Initial State: IP is blocked
        System.out.println("\n[1] Initial State:");
        BlacklistManager.displayBlacklist();
        System.out.println("Check: Is '" + blockedIP + "' allowed? " + BlacklistManager.isAllowed(blockedIP));

        // 2. Run the exploit
        System.out.println("\n[2] Triggering BlacklistExploit...");
        BlacklistExploit.clearBlacklist();

        // 3. Re-verify State: IP is now allowed!
        System.out.println("\n[3] Final State:");
        BlacklistManager.displayBlacklist();
        System.out.println("Check: Is '" + blockedIP + "' allowed? " + BlacklistManager.isAllowed(blockedIP));

        if (BlacklistManager.isAllowed(blockedIP)) {
            System.out.println("\nALERT: Blacklist bypassed! Restricted IP is now allowed through. (Vulnerable)");
        } else {
            System.out.println("\nCheck: Blacklist integrity preserved. (Secure)");
        }
    }
}
