# Exercise 6-9: The Master Key Injection (MUTABLE-9)

## The Scenario
The "IronGate" security system uses a `Gatekeeper` class to manage access keys. The class defines a set of "master keys" in a `public static final` array. The developers believed that using the `final` keyword would prevent anyone from changing the list of master keys.

However, in Java, `final` on an array only prevents the array variable from being reassigned to a *new* array. It does **not** prevent the elements *inside* the array from being modified. A malicious module can exploit this to overwrite one of the master keys with its own, gaining unauthorized access.

## Your Task
Secure the `Gatekeeper` by following **Guideline 6-9 / MUTABLE-9: Make public static fields final**.

1.  **Analyze the Vulnerability**:
    Run `GateApp.java`. Notice how the `KeyExploit` successfully replaces a master key, allowing the hacker's key to grant access.

2.  **Fix the Gatekeeper**:
    Modify `Gatekeeper.java` so that the master keys are truly immutable.

    *Hint: Don't use an array for public constants. Use an immutable collection like `List.of()` or `Collections.unmodifiableList()`.*

## Instructions

1.  **Observe the Key Injection**:
    ```bash
    javac *.java
    java GateApp
    ```
    Note how the hacker's key is accepted *after* the exploit runs.

2.  **Apply the Fix**:
    Update `Gatekeeper.java` to use an immutable structure for its master keys.

3.  **Verify**:
    Compile and run again. The exploit should now fail (it will likely throw an exception or fail to compile if you changed the field type appropriately).
