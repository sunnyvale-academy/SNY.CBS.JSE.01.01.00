public class ResourceApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 6-10: The Sabotaged Limit ---");

        int currentRequests = 1000;

        // 1. Initial Check: Server should reject high request counts
        System.out.println("\n[1] Current Max Requests: " + ResourceLimit.MAX_REQUESTS);
        System.out.println("Check: Is " + currentRequests + " requests within limit? "
                + ResourceLimit.isWithinLimit(currentRequests));

        // 2. Run the exploit
        System.out.println("\n[2] Triggering LimitExploit...");
        LimitExploit.sabotage();

        // 3. Re-verify Check: Hijacked limit allows anything
        System.out.println("\n[3] Current Max Requests: " + ResourceLimit.MAX_REQUESTS);
        System.out.println("Check: Is " + currentRequests + " requests within limit? "
                + ResourceLimit.isWithinLimit(currentRequests));

        if (ResourceLimit.isWithinLimit(currentRequests)) {
            System.out.println("\nALERT: System Limit bypassed! (Vulnerable)");
        } else {
            System.out.println("\nCheck: System Limit held. (Secure)");
        }
    }
}
