import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 1-5 (DOS-5): Avoid using user input as hash keys.
 * 
 * VULNERABLE: Unlimited storage of user-provided keys in a HashMap.
 */
public class VulnerableMetadataStore {
    private Map<String, String> metadata = new HashMap<>();

    public void setMetadata(String key, String value) {
        // VULNERABLE: No limit on count of entries
        // VULNERABLE: Direct use of user-input as hash key
        metadata.put(key, value);
    }

    public String getMetadata(String key) {
        return metadata.get(key);
    }

    public int size() {
        return metadata.size();
    }
}
