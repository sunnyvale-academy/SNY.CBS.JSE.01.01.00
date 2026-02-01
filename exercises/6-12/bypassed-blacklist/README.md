# Exercise 6-12: The Bypassed Blacklist (MUTABLE-12)

## The Scenario
The "IronWall" firewall uses a `BlacklistManager` to track IPs that are banned for suspicious activity. The banned IPs are stored in a `public static final Set<String> BLACKLISTED_IPS`. 

The developers believed that by using `final`, the blacklist was immutable. However, an attacker discovered that while they couldn't *replace* the set with a new one, they could still call `.clear()` on the existing set, effectively deleting all security restrictions.

## Your Task
Secure the `BlacklistManager` by following **Guideline 6-12 / MUTABLE-12: Do not expose modifiable collections**.

1.  **Analyze the Vulnerability**:
    Run `SecurityApp.java`. Observe how the `BlacklistExploit` manages to bypass the firewall by clearing the blacklist.

2.  **Fix the Blacklist Manager**:
    Modify `BlacklistManager.java` to ensure that the collection cannot be modified from the outside.

    *Hint: Use `Collections.unmodifiableSet()` to return a read-only view, or use `Set.of()` to create a truly immutable set (if the list of IPs is static).*

## Instructions

1.  **Observe the Bypass**:
    ```bash
    javac *.java
    java SecurityApp
    ```
    Note how the blocked IP `192.168.1.100` is allowed through *after* the exploit runs.

2.  **Apply the Fix**:
    Update `BlacklistManager.java` to protect the collection.

3.  **Verify**:
    Compile and run again. The exploit should now throw an `UnsupportedOperationException` when it tries to call `.clear()`.
