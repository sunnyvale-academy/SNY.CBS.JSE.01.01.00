package com.security.superclass;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Demonstrates the exploit by using the inherited entrySet() method
 * to bypass SecurityProvider's protections.
 */
public class Attacker {
    public static void exploit(SecurityProvider provider) {
        System.out.println("\n[ATTACK] Starting exploit...");

        // The attacker wants to remove "SYSTEM.ALGORITHM"
        // Calling provider.remove("SYSTEM.ALGORITHM") would be blocked or logged.

        System.out.println("[ATTACK] Bypassing SecurityProvider.remove() by using entrySet()...");
        Set<Map.Entry<String, String>> entries = provider.entrySet();

        Iterator<Map.Entry<String, String>> it = entries.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            if (entry.getKey().equals("SYSTEM.SECRET_KEY")) {
                System.out.println("[ATTACK] Found protected key: " + entry.getKey());
                it.remove(); // This removes the entry directly from the underlying map!
                System.out.println(
                        "[ATTACK] Successfully deleted " + entry.getKey() + " without triggering checkPermission()");
            }
        }
    }
}
