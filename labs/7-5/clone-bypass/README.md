# Lab 7-5: The Cloning Bypass (OBJECT-5)

## Guideline Reference
**Guideline 7-5 / OBJECT-5**: Defend against cloning of non-final classes.

## Overview
In Java, the `Object.clone()` method creates a bitwise copy of an object *without calling any constructor*.

If a sensitive class is not `final`, an attacker can create a subclass that implements the `Cloneable` interface. This allows them to create new instances of the sensitive class by cloning existing ones, effectively bypassing any security checks or logging that you've placed in the class constructor.

## Lab Materials
- `SensitiveManager.java`: Vulnerable base class that doesn't defend against cloning.
- `CloneExploit.java`: Malicious subclass that implements `Cloneable` to enable attacks.
- `CloneLab.java`: PoC demonstrating how an attacker can duplicate a sensitive object without authorization.
- `SecureSensitiveManager.java`: Secure version that blocks cloning using a `final` method.

## Instructions

1.  **Observe the Bypass**:
    ```bash
    javac *.java
    java CloneLab
    ```
    Notice how the attacker manages to get a `SensitiveManager` instance even though `AuthService.isAuthorized()` is false when the duplication occurs.

2.  **Analyze the Failure**:
    In `CloneExploit.java`, the call to `super.clone()` (which maps to `Object.clone()`) allocates memory and copies fields but never triggers the `SensitiveManager` constructor. This is a "backdoor" for object creation.

3.  **Inspect the Fix**:
    Check `SecureSensitiveManager.java`. It defines a `public final Object clone()` method that throws `CloneNotSupportedException`. By making it `final`, no subclass can ever provide a working `clone()` implementation, closing the backdoor.

## Key Takeaway
**Unless a class is `final`, it must proactively defend against cloning if it's sensitive.** The most robust defense is to provide a `final` `clone()` method that simply throws `CloneNotSupportedException`.
