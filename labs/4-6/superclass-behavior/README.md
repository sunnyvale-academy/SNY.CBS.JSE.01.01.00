# Lab 4-6: Understand How a Superclass Can Affect Subclass Behavior

## Guideline Reference
**Guideline 4-6 / EXTEND-6: Understand how a superclass can affect subclass behavior**

## Vulnerability Explanation
Subclasses do not have absolute control over their behavior. If a superclass changes—for example, by adding a new method—the subclass might unknowingly inherit that method and its default behavior, potentially bypassing security checks that only exist in the subclass's overridden methods.

This lab recreates a famous historical vulnerability in the JDK involving `java.security.Provider`.

```text
Class Hierarchy                  Inherited Methods
-----------------------          --------------------------
java.util.Hashtable              put(key, val)
          ^                      remove(key)
          | extends
          |
java.util.Properties
          ^
          | extends
          |
java.security.Provider           put(key, val) // SecurityManager
                                 remove(key)   // checks for these
                                               // methods
```

- `Provider` extended `Hashtable` (via `Properties`).
- `Provider` overridden `put` and `remove` to enforce security checks.
- Later, `Hashtable` introduced `entrySet()`, which allowed removal of entries.
- `Provider` did not override `entrySet()`, allowing an attacker to bypass the security checks in `remove()` by using the inherited `entrySet().remove()`.

In this lab:
1. `LegacyMap` (Superclass) has an `entrySet()` method.
2. `SecurityProvider` (Subclass) overrides `put()` and `remove()` to simulate security checks.
3. `Attacker` uses `entrySet()` to delete protected items without triggering the checks in `SecurityProvider`.

## Instructions
1.  **Compile the code**:
    ```bash
    javac com/security/superclass/*.java
    ```

2.  **Run the lab**:
    ```bash
    java com.security.superclass.LabRunner
    ```
    Observe how "Legitimate removal" is blocked/logged, but the "Attacker" successfully deletes the `SYSTEM.SECRET_KEY` using the inherited `entrySet()` method.

## The Fix
The guideline recommends **composition over inheritance**. If `SecurityProvider` *contained* a `LegacyMap` instead of *extending* it, it would have total control over which methods are exposed and how they are guarded.

## Key Takeaways
- Be aware of the methods your class inherits from its superclass.
- Changes in a superclass (like adding new methods in a library update) can create security holes in subclasses.
- Prefer composition to ensure that security-critical components have complete control over their state and access methods.
