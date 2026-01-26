package com.security.superclass;

/**
 * Subclass representing a Security Provider (similar to
 * java.security.Provider).
 * 
 * It attempts to guard its mappings by overriding put and remove with
 * security checks. However, it fails to account for entrySet() inherited
 * from LegacyMap.
 */
public class SecurityProvider extends LegacyMap {

    private void checkPermission(String action) {
        // Simulating a SecurityManager check
        System.out.println("[SECURE] Checking permission for: " + action);
        // In a real system, this would throw a SecurityException if denied
    }

    @Override
    public void put(String key, String value) {
        checkPermission("put." + key);
        super.put(key, value);
    }

    @Override
    public void remove(String key) {
        checkPermission("remove." + key);
        // Imagine this logic specifically blocks removing certain critical mappings
        if (key.startsWith("SYSTEM.")) {
            System.out.println("[SECURE] Access Denied: Cannot remove system property " + key);
            return;
        }
        super.remove(key);
    }

    // VULNERABILITY: entrySet() is NOT overridden, allowing direct access to the
    // map.
}
