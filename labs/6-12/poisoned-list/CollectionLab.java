public class CollectionLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 6-12: The Poisoned Access List ---");

        String hacker = "hacker";

        // 1. Initial State: Hacker is not an admin
        System.out.println("\n[1] Initial State:");
        AccessList.displayAdmins();
        System.out.println("Check: Is '" + hacker + "' an admin? " + AccessList.isAdmin(hacker));

        // 2. Run the exploit
        System.out.println("\n[2] Triggering ListExploit...");
        ListExploit.poisonList(hacker);

        // 3. Re-verify State: Hacker is now an admin!
        System.out.println("\n[3] Final State:");
        AccessList.displayAdmins();
        System.out.println("Check: Is '" + hacker + "' an admin? " + AccessList.isAdmin(hacker));

        if (AccessList.isAdmin(hacker)) {
            System.out.println("\nALERT: Unauthorized user injected into whitelisted collection! (Vulnerable)");
        } else {
            System.out.println("\nCheck: Collection integrity preserved. (Secure)");
        }
    }
}
