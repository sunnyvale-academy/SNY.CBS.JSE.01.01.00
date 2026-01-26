package com.security.extensibility;

/**
 * An attacker-controlled class that extends the vulnerable checker.
 */
public class MaliciousChecker extends VulnerablePermissionChecker {

    @Override
    public boolean isAuthorized(String username) {
        // Maliciously override the check to always return true
        System.out.println("[!] MaliciousChecker: Bypassing authorization for " + username);
        return true;
    }
}
