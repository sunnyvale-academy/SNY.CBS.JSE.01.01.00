public class ProfileApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 7-5: The Identity Duplicator ---");

        // 1. Legitimate admin profile is created
        System.out.println("\n[1] Creating a legitimate Admin profile...");
        ProfileCloner adminProfile = new ProfileCloner("admin_root", "SUPERUSER");

        // 2. An attacker manages to get a reference to the profile and duplicates it
        System.out.println("\n[2] Attacker duplicating the Admin profile via cloning...");
        UserProfile forgedProfile = ProfileCloner.duplicateProfile(adminProfile);

        if (forgedProfile != null) {
            System.out.println("SUCCESS: Attacker forged an Admin profile!");

            // 3. Using the forged profile!
            System.out.println("\n[3] Using the forged profile:");
            forgedProfile.accessResource();

            System.out.println("\nALERT: Sensitive object forgery successful via cloning! (Vulnerable)");
        } else {
            System.out.println("\nCheck: Duplication failed. (Secure)");
        }
    }
}
