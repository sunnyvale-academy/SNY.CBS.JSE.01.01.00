package com.security.extensibility;

/**
 * Lab Runner to demonstrate the vulnerability.
 */
public class LabRunner {
    public static void main(String[] args) {
        System.out.println("--- Scenario 1: Normal Operation ---");
        VulnerablePermissionChecker normalChecker = new VulnerablePermissionChecker();
        SystemControl system1 = new SystemControl(normalChecker);
        system1.performSensitiveAction("guest");

        System.out.println("\n--- Scenario 2: Attack via Subclassing ---");
        // An attacker provides a malicious implementation of the checker by uploading a
        // malicious jar file in the classpath
        VulnerablePermissionChecker maliciousChecker = new MaliciousChecker();
        SystemControl system2 = new SystemControl(maliciousChecker);
        system2.performSensitiveAction("guest");
    }
}
