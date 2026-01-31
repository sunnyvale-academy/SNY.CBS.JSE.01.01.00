/**
 * VULNERABLE CLASS: PreferencesService
 * Loads and stores user preferences from an external provider.
 * VIOLATION: Trusts the mutable object returned by an untrusted provider.
 */
public class PreferencesService {
    private UserPrefs currentPrefs;

    /**
     * Loads preferences from the given provider.
     * VULNERABILITY: Does not perform a defensive copy of the output from an
     * untrusted provider.
     */
    public void loadPreferences(PrefsProvider provider) {
        System.out.println("PreferencesService: Loading preferences from provider...");
        // GUIDELINE 6-7 Violation: Output from an untrusted object (the provider)
        // must be treated as untrusted input.
        this.currentPrefs = provider.getPreferences();
    }

    public void displayPrefs() {
        System.out.println("PreferencesService: Current Preferences -> " + currentPrefs);
    }
}
