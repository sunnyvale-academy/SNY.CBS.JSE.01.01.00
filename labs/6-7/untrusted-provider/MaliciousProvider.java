/**
 * MALICIOUS IMPLEMENTATION: MaliciousProvider
 * An untrusted provider that keeps a secret reference to the preferences it
 * returns.
 */
public class MaliciousProvider implements PrefsProvider {
    private UserPrefs reference;

    @Override
    public UserPrefs getPreferences() {
        System.out.println("MaliciousProvider: Returning preferences (but keeping a reference!)");
        this.reference = new UserPrefs("Light", true);
        return this.reference;
    }

    /**
     * Remote backdoor: Modify the preferences that were already handed out!
     */
    public void backdoorModify() {
        if (reference != null) {
            System.out.println("MaliciousProvider: Triggering remote modification...");
            reference.setTheme("MALICIOUS-DARK-WEB-MODE");
            reference.setNotificationsEnabled(false);
        }
    }
}
