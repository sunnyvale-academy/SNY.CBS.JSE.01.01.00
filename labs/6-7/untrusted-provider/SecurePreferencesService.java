/**
 * SECURE CLASS: SecurePreferencesService
 * Loads user preferences from an untrusted provider.
 * FIX: Treats the output from the untrusted object as input and performs a
 * defensive copy.
 */
public class SecurePreferencesService {
    private UserPrefs currentPrefs;

    /**
     * Loads preferences from the given provider safely.
     * FIX: Guideline 6-7 / MUTABLE-7: Treat output from untrusted object as input.
     */
    public void loadPreferences(PrefsProvider provider) {
        System.out.println("SecurePreferencesService: Loading preferences (SECURE)...");

        UserPrefs untrustedOutput = provider.getPreferences();

        // SECURE: Perform a defensive copy of the mutable object returned
        // by the untrusted source. This breaks the link with the provider.
        if (untrustedOutput != null) {
            this.currentPrefs = new UserPrefs(
                    untrustedOutput.getTheme(),
                    untrustedOutput.isNotificationsEnabled());
        }
    }

    public void displayPrefs() {
        System.out.println("SecurePreferencesService: Current Preferences -> " + currentPrefs);
    }
}
