import java.util.HashMap;
import java.util.Map;

/**
 * VULNERABLE CLASS: SystemConfig
 * Stores global system settings in a mutable Map.
 * VIOLATION: Does not provide a way to be safely copied.
 */
public class SystemConfig {
    private final Map<String, String> settings;

    public SystemConfig() {
        this.settings = new HashMap<>();
    }

    // Constructor with initial settings
    public SystemConfig(Map<String, String> initialSettings) {
        this.settings = new HashMap<>(initialSettings);
    }

    public void setSetting(String key, String value) {
        settings.put(key, value);
    }

    public String getSetting(String key) {
        return settings.get(key);
    }

    @Override
    public String toString() {
        return "SystemSettings: " + settings;
    }
}
