package com.security.extensibility;

/**
 * Guideline 4-5 / EXTEND-5: Limit the extensibility of classes and methods
 * 
 * This class is security-critical but is not marked as final,
 * nor is its sensitive method.
 */
public class VulnerablePermissionChecker {

    /**
     * Checks if a user has permission to perform a sensitive action.
     * In a real scenario, this would check a database or configuration.
     */
    public boolean isAuthorized(String username) {
        // Only "admin" is authorized by default
        return "admin".equals(username);
    }
}
