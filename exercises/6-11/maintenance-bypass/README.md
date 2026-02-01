# Exercise 6-11: The Maintenance Bypass (MUTABLE-11)

## The Scenario
The "SuperServer" administration tool uses a `MaintenanceManager` to control high-privileged actions. To simplify debugging, the developers used a `public static boolean maintenanceMode` flag. When this flag is `true`, the system skips standard authentication and allows administrative commands.

However, because the flag is a **public static mutable field**, any code running in the JVM—including untrusted plugins—can reach out and flip the switch, granting themselves full administrative power without a password.

## Your Task
Secure the `MaintenanceManager` by following **Guideline 6-11 / MUTABLE-11: Do not expose mutable statics**.

1.  **Analyze the Vulnerability**:
    Run `MaintenanceApp.java`. Notice how the `MaintenanceExploit` successfully enables administrative access by directly modifying the static flag.

2.  **Fix the Maintenance Manager**:
    Modify `MaintenanceManager.java` so that the flag is no longer exposed.

    *Hint: Encapsulate the flag (make it `private`) and provide controlled methods for checking and setting it. In a real system, the setter would require a secure token or permission.*

## Instructions

1.  **Observe the Bypass**:
    ```bash
    javac *.java
    java MaintenanceApp
    ```
    Note how the "FORMAT DATABASE" action is allowed *after* the exploit runs.

2.  **Apply the Fix**:
    Update `MaintenanceManager.java` to encapsulate its state.

3.  **Verify**:
    Compile and run again. The exploit should fail to compile (as it can no longer see the private flag).
