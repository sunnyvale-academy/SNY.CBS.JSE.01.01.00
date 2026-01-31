# Lab 6-5: Trusting Identity Equality (MUTABLE-5)

## Guideline Reference
**Guideline 6-5 / MUTABLE-5**: Do not trust identity equality when overridable on input reference objects.

## Overview
Using `==` (identity equality) to check if an object represents a specific state or role is dangerous if an attacker can create multiple instances of that object. 

Even if you intend a class to be a singleton or use static constants, if there's any way to create another instance (via public constructors, deserialization, reflection, etc.), your security check using `==` can be bypassed.

## Lab Materials
- `AccessLevel.java`: A class with static constants but a public constructor.
- `GateService.java`: Uses `==` to block guests from the vault.
- `BypassLab.java`: Demonstrates the bypass attack.

## Instructions

1.  **Compile and Run the Lab**:
    ```bash
    javac *.java
    java BypassLab
    ```

2.  **Analyze the Failure**:
    - Observe how the forged guest with name "GUEST" is allowed into the vault.
    - The `GateService` only blocks the specific object `AccessLevel.GUEST`. Since `fakeGuest` is a different object in memory, the check returns `false`, and access is granted.

3.  **Inspect the Fixes**:
    Read `SecureAccessLevel.java`. 
    - **Using Enums**: This is the best fix. JVM guarantees enums have unique instances, making `==` safe.
    - **Alternative**: If you must use a class, make the constructor `private`, provide no ways to duplicate it, and **use `.equals()`** for security checks.

## Key Takeaway
**Never use `==` for security-critical logic on objects** unless the type is an `enum` or you have absolute control over object instantiation and identity. Always prefer `.equals()` or state-based checks (e.g., `level.getName().equals("GUEST")`).
