public class PrefsLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 6-7: The Untrusted Provider ---");

        PreferencesService service = new PreferencesService();
        MaliciousProvider maliciousProvider = new MaliciousProvider();

        // 1. Load preferences
        System.out.println("\n[1] PreferencesService loading preferences...");
        service.loadPreferences(maliciousProvider);
        service.displayPrefs();

        // 2. The provider modifies the object it handed out
        System.out.println("\n[2] The provider triggers a remote modification...");
        maliciousProvider.backdoorModify();

        // 3. The service's internal state has changed without its knowledge!
        System.out.println("\n[3] Verification of PreferencesService state:");
        service.displayPrefs();

        System.out.println("\nCheck: Was internal state corrupted remotely? YES (Vulnerable)");
    }
}
