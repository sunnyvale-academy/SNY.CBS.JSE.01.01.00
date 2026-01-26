package com.security.extensibility;

/**
 * A system component that relies on a PermissionChecker.
 */
public class SystemControl {
    private final VulnerablePermissionChecker checker;

    public SystemControl(VulnerablePermissionChecker checker) {
        this.checker = checker;
    }

    public void performSensitiveAction(String username) {
        if (checker.isAuthorized(username)) {
            System.out
                    .println("[+] SystemControl: ACCESS GRANTED for " + username + ". Performing sensitive action...");
        } else {
            System.out.println("[-] SystemControl: ACCESS DENIED for " + username + ".");
        }
    }
}
