# Lab 7-4: The Constructor Trap (OBJECT-4)

## Guideline Reference
**Guideline 7-4 / OBJECT-4**: Prevent constructors from calling methods that can be overridden.

## Overview
In Java, when a subclass is instantiated, the base class constructor is executed first. If the base class constructor calls a method that the subclass has overridden, the subclass's version of that method will run *before* the subclass constructor has had a chance to initialize its own fields.

This results in the subclass method executing on an object in a partially initialized, inconsistent state. For many fields (even `final` ones!), this means they will appear as `null` or their default primitive values (0, false).

This behavior is a common source of bugs (like `NullPointerException`) and can lead to security vulnerabilities if initialization logic that the subclass method depends on (like setting up a security manager or validating tokens) hasn't run yet.

## Lab Materials
- `BaseService.java`: Vulnerable base class that calls an overridable `setup()` method.
- `MaliciousService.java`: Subclass that overrides `setup()` and attempts to access its own (uninitialized) fields.
- `OverrideLab.java`: PoC demonstrating the uninitialized state access.
- `SecureBaseService.java`: Secure version that uses a `final` method to prevent overrides.

## Instructions

1.  **Observe the Trap**:
    ```bash
    javac *.java
    java OverrideLab
    ```
    Notice that in the output, "OBSERVATION: sensitiveToken is NULL!" is printed during the constructor phase, even though we passed "SECRET-123" to the constructor.

2.  **Analyze the Failure**:
    In `BaseService.java`, the constructor calls `setup()`. Because this method is `protected` and not `final`, `MaliciousService` can override it. The JVM dynamic dispatch mechanism ensures that the subclass implementation is called, even if we are currently inside the base class constructor.

3.  **Inspect the Fix**:
    Check `SecureBaseService.java`. It declares the setup method as `final`. This prevents any subclass from overriding it, ensuring that the code executed during construction is exactly what the base class intended and doesn't rely on uninitialized subclass state.

## Key Takeaway
**Never call overridable methods from a constructor.** If you need to perform initialization that subclasses can customize, use a static factory method to coordinate the construction and subsequent setup, or make the methods called from the constructor `final` or `private`.
