# Lab 4-5: Limit Extensibility of Classes and Methods

## Guideline Reference
**Guideline 4-5 / EXTEND-5: Limit the extensibility of classes and methods**

## Vulnerability Explanation
In Java, classes and methods are extensible by default (unless they are `final`). If a class performing security-critical tasks can be subclassed, an attacker can override its methods to bypass security checks, leak data, or change intended logic.

In this lab:
1. `VulnerablePermissionChecker` is a security-critical class that is not `final`.
2. `isAuthorized()` is a non-final method.
3. `MaliciousChecker` subclasses `VulnerablePermissionChecker` and overrides `isAuthorized()` to always return `true`, effectively bypassing the authorization mechanism.

## Instructions
1.  **Compile the code**:
    ```bash
    javac com/security/extensibility/*.java
    ```

2.  **Run the lab**:
    ```bash
    java com.security.extensibility.LabRunner
    ```
    Observe how the `MaliciousChecker` grants access to a "guest" user who should normally be denied.

3.  **Fix the vulnerability**:
    Modify `com/security/extensibility/VulnerablePermissionChecker.java` to prevent extensibility. You can either:
    -   Make the entire class `final`: `public final class VulnerablePermissionChecker`
    -   Make only the sensitive method `final`: `public final boolean isAuthorized(String username)`

4.  **Verify the fix**:
    Attempt to compile again:
    ```bash
    javac com/security/extensibility/*.java
    ```
    The compilation should now fail because `MaliciousChecker` cannot override a `final` method or extend a `final` class.

## Key Takeaways
- Classes and methods performing security-critical tasks should be made `final` to prevent unauthorized modification via inheritance.
- If inheritance is required, ensure that security-critical logic is either in `final` methods or that the class uses a "secure by design" approach that cannot be easily bypassed by subclasses.
