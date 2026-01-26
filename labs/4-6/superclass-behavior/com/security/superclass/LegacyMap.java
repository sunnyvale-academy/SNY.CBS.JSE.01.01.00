package com.security.superclass;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Superclass representing a legacy map structure (similar to Hashtable).
 * 
 * It stores mappings and provides methods to manipulate them.
 */
public class LegacyMap {
    protected final Map<String, String> internalMap = new HashMap<>();

    public void put(String key, String value) {
        internalMap.put(key, value);
    }

    public void remove(String key) {
        internalMap.remove(key);
    }

    /**
     * This method was added later (like entrySet in Hashtable).
     * It returns a mutable set view of the mappings.
     */
    public Set<Map.Entry<String, String>> entrySet() {
        return internalMap.entrySet();
    }

    public String get(String key) {
        return internalMap.get(key);
    }
}
