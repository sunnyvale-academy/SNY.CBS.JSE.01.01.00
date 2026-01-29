package com.security.api;

/**
 * Runner for Guideline 5-4 lab.
 */
public class LabRunner {
    public static void main(String[] args) {
        VulnerableFileService vulnerable = new VulnerableFileService();
        SecureFileService secure = new SecureFileService();

        // The "evil" input: using directory traversal to escape the sandbox.
        String maliciousInput = "../../etc/passwd";

        System.out.println("--- Scenario: Directory Traversal via API Misunderstanding ---");
        System.out.println("Base Directory: /app/data/storage/");
        System.out.println("User Input: " + maliciousInput);

        System.out.println("\n[TEST] Running Vulnerable Service...");
        vulnerable.accessFile(maliciousInput);

        System.out.println("\n[TEST] Running Secure Service...");
        secure.accessFile(maliciousInput);
    }
}
