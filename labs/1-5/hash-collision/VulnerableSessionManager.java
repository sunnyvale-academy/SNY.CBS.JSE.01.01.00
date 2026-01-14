import java.util.HashMap;
import java.util.Map;

/**
 * Guideline 1-5 (DOS-5): Avoid using user input as hash keys.
 * 
 * VULNERABLE: Directly uses user-provided strings as keys in a HashMap.
 */
public class VulnerableSessionManager {
    private Map<String, String> sessionStore = new HashMap<>();

    public void createSession(String sessionId, String userData) {
        // VULNERABLE: Direct use of user-controlled string as hash key
        sessionStore.put(sessionId, userData);
    }

    public String getSession(String sessionId) {
        return sessionStore.get(sessionId);
    }

    public int getSessionCount() {
        return sessionStore.size();
    }
}
