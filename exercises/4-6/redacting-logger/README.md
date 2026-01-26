# Exercise 4-6: The Fragile Base Class Bypass

## Guideline Reference
**Guideline 4-6 / EXTEND-6: Understand how a superclass can affect subclass behavior**

## Vulnerability Explanation
Subclasses inherit the behavior of their superclasses. If a superclass is updated with new methods, the subclass inherits them automatically. This can lead to security vulnerabilities if the subclass was designed to enforce a security policy (like redacting sensitive data) by overriding a *subset* of methods, unaware that new methods in the superclass might provide a way to bypass those protections.

In this exercise:
1. `BaseLogger` (Superclass) was updated with a new `logMultiple()` method.
2. `SecureRedactingLogger` (Subclass) overrides `log(String)` to mask passwords but doesn't know about `logMultiple()`.
3. Calls to `logMultiple()` bypass the redaction logic entirely.

## Your Task
Your goal is to fix `SecureRedactingLogger` so that it is no longer vulnerable to superclass "method injection". Instead of extending `BaseLogger`, you should use **composition** (the Wrapper/Decorator pattern).

1.  **Compile and Run the vulnerable version**:
    ```bash
    javac com/security/logger/*.java
    ```
    ```bash
    java com.security.logger.ExerciseRunner
    ```
    Notice how "Scenario 1" is redacted, but "Scenario 2" leaks the password!

2.  **Apply the fix**:
    -   Modify `SecureRedactingLogger.java` to *not* extend `BaseLogger`.
    -   Give it a `private final BaseLogger logger` field.
    -   Re-implement the `log` method to use the internal logger after redacting.
    -   Decide whether to expose `logMultiple` at all, or implement it securely by delegating to your secure `log` method.

3.  **Verify the fix**:
    Update the `ExerciseRunner.java` if needed (if you changed the API), then compile and run again. The leak should be gone.

## Key Takeaways
- Inheritance is "fragile" because you don't have complete control over what you inherit.
- Prefer **composition over inheritance** for security-critical components.
- A wrapper (or decorator) ensures that you only expose the methods you explicitly intend to support and can easily apply security policies to all of them.
