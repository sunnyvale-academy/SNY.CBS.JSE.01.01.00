# Exercise 6-10: The Sabotaged Limit (MUTABLE-10)

## The Scenario
The "AutoCloud" platform uses a `ResourceLimit` class to enforce throttling. It defines a `public static final Limit MAX_REQUESTS` constant to set the maximum allowed requests per second. The developers used a custom `Limit` class and marked the field as `final` to prevent changes.

However, the `Limit` class is **mutable**—it has a `setValue()` method. A malicious module can use this method to change the "constant" limit at runtime, effectively disabling the throttling mechanism for the entire system.

## Your Task
Secure the `ResourceLimit` by following **Guideline 6-10 / MUTABLE-10: Ensure public static final field values are constants**.

1.  **Analyze the Vulnerability**:
    Run `ResourceApp.java`. Notice how the `LimitExploit` successfully hijacks the "constant" limit, allowing 1000 requests to be accepted even though the initial limit was 5.

2.  **Fix the Resource Limit**:
    Modify the `ResourceLimit` system so that the constants are truly immutable.

    *Hint: Either make the `Limit` class immutable (remove setters, use final fields) or use a primitive type that is inherently immutable when final.*

## Instructions

1.  **Observe the Limit Sabotage**:
    ```bash
    javac *.java
    java ResourceApp
    ```
    Note how the limit is changed from 5 to 999999.

2.  **Apply the Fix**:
    Update the `Limit` class in `ResourceLimit.java` to be immutable, or change the field to a primitive `int`.

3.  **Verify**:
    Compile and run again. If you made `Limit` immutable, the exploit will fail to compile (as `setValue` won't exist). If the system is secure, the user should NOT be able to bypass the limit of 5.
