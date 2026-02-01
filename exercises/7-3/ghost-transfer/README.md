# Exercise 7-3: The Ghost Transfer (OBJECT-3)

## The Scenario
The "IronBank" has a `BankTransfer` class that enforces a security check in its constructor. If a user tries to create a transfer without proper authorization, the constructor throws a `SecurityException`.

The developers assumed that if the constructor fails, the object is never created and cannot be used. However, an attacker has discovered a way to "resurrect" these failed objects using a **Finalizer Attack**. By extending the class and overriding the `finalize()` method, they can capture a reference to the `this` object just before the JVM discards it, even if the constructor threw an exception!

## Your Task
Secure the `BankTransfer` class by following **Guideline 7-3 / OBJECT-3: Defend against partially initialized instances of non-final classes**.

1.  **Analyze the Vulnerability**:
    Run `TransferApp.java`. Observe how the `GhostTransferExploit` manages to capture and use a `BankTransfer` object that should have been blocked.

2.  **Fix the Bank Transfer**:
    Modify `BankTransfer.java` to prevent Finalizer Attacks.

    *Option A (Recommended): Make the class `final`. If the class cannot be subclassed, the attack is impossible.*
    *Option B: Add a final `finalize()` method that does nothing. This prevents subclasses from overriding it for an attack.*
    *Option C: Use an initialization flag and check it in every method.*

## Instructions

1.  **Observe the Attack**:
    ```bash
    javac *.java
    java TransferApp
    ```
    Note how the exploit manages to call `.process()` on a transfer that yielded a `SecurityException`.

2.  **Apply the Fix**:
    Update `BankTransfer.java` to use one of the defense patterns.

3.  **Verify**:
    Compile and run again. If you made the class `final`, `GhostTransferExploit.java` will fail to compile (Access denied - cannot inherit from final class). If you used a final finalize method, the exploit will fail to override it. Both results effectively stop the attack.
