/**
 * UserPrefs class.
 * Represents user preferences that can be modified.
 */
public class UserPrefs {
    private String theme;
    private boolean notificationsEnabled;

    public UserPrefs(String theme, boolean notificationsEnabled) {
        this.theme = theme;
        this.notificationsEnabled = notificationsEnabled;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    @Override
    public String toString() {
        return "UserPrefs{theme='" + theme + "', notificationsEnabled=" + notificationsEnabled + "}";
    }
}
