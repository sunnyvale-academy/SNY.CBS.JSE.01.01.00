package com.security.superclass;

/**
 * Lab Runner for Guideline 4-6.
 */
public class LabRunner {
    public static void main(String[] args) {
        SecurityProvider provider = new SecurityProvider();

        // Initialize with sensitive data
        provider.put("SYSTEM.SECRET_KEY", "highly-confidential-value");
        provider.put("PUBLIC.INFO", "regular-value");

        System.out.println("\n--- Current State ---");
        System.out.println("SYSTEM.SECRET_KEY: " + provider.get("SYSTEM.SECRET_KEY"));
        System.out.println("PUBLIC.INFO: " + provider.get("PUBLIC.INFO"));

        // Demonstrate that normal removal is protected
        System.out.println("\n--- Attempting legitimate removal ---");
        provider.remove("SYSTEM.SECRET_KEY");

        // Run the exploit
        Attacker.exploit(provider);

        System.out.println("\n--- Final State ---");
        System.out.println("SYSTEM.SECRET_KEY: "
                + (provider.get("SYSTEM.SECRET_KEY") == null ? "[DELETED]" : provider.get("SYSTEM.SECRET_KEY")));
        System.out.println("PUBLIC.INFO: " + provider.get("PUBLIC.INFO"));
    }
}
