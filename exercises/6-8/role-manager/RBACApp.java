public class RBACApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 6-8: The Bypassed RBAC ---");

        UserRoleManager manager = new UserRoleManager();

        System.out.println("Initial Roles: " + manager.getRoles());
        System.out.println("Has Admin Privileges? " + manager.hasAdminPrivileges());

        // Attempting to add ADMIN via the official API
        System.out.println("\n[1] Attempting to add ADMIN via addRole()...");
        manager.addRole("ADMIN");

        // Now use the exploit
        System.out.println("\n[2] Triggering RoleExploit...");
        RoleExploit.escalatePrivileges(manager);

        System.out.println("\n[3] Final System Check:");
        System.out.println("Final Roles: " + manager.getRoles());
        System.out.println("Has Admin Privileges? " + manager.hasAdminPrivileges());

        if (manager.hasAdminPrivileges()) {
            System.out.println("\nALERT: Privilege Escalation SUCCESSFUL! (Vulnerable)");
        } else {
            System.out.println("\nCheck: Privilege Escalation blocked. (Secure)");
        }
    }
}
