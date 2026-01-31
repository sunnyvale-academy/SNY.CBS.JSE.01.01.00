# Exercise 6-8: The Bypassed RBAC (MUTABLE-8)

## The Scenario
The "SecureCorp" internal portal uses a `UserRoleManager` to track user permissions. The manager has a strict policy: the "ADMIN" role cannot be added via the standard `addRole()` method—it must be assigned through a separate, secure process (not shown here).

However, the `UserRoleManager` provides a `getRoles()` method that returns its internal list of roles. A malicious module can use this list to bypass the restriction and grant itself administrative privileges.

## Your Task
Secure the `UserRoleManager` by following **Guideline 6-8 / MUTABLE-8: Define wrapper methods around modifiable internal state**.

1.  **Analyze the Vulnerability**:
    Run `RBACApp.java`. Notice how the `RoleExploit` successfully adds the "ADMIN" role despite the check in `addRole()`.

2.  **Fix the Manager**:
    Modify `UserRoleManager.java` so that callers can still *see* the roles, but cannot *modify* the list directly.

    *Hint: Wrap the internal collection in an unmodifiable view.*

## Instructions

1.  **Observe the Privilege Escalation**:
    ```bash
    javac *.java
    java RBACApp
    ```
    Note how the user ends up with "ADMIN" privileges.

2.  **Apply the Fix**:
    Update the `getRoles()` method in `UserRoleManager.java` to return a read-only view of the list.

3.  **Verify**:
    Compile and run again. The exploit should now throw an `UnsupportedOperationException`, and the user should NOT gain "ADMIN" access.
