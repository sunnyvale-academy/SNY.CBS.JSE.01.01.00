# Lab 8-4: The Permission Bypass (SERIAL-4)

## Guideline Reference
**Guideline 8-4 / SERIAL-4**: Duplicate the security-related checks performed in a class during serialization and deserialization.

## Overview
When a security manager (or a custom security context) is used to restrict the creation of an object, developers often place the check in the constructor. However, Java Serialization is a completely different path for object creation that **bypasses the constructor**.

If your security checks are only in the constructor, an attacker can:
1.  Obtain a serialized version of a privileged object.
2.  Wait for a context with lower privileges.
3.  Deserialize the object, effectively "birthing" it in an unauthorized context.

## Lab Materials
- `SecurityContext.java`: A simulation of a permission system.
- `SecureDocument.java`: Vulnerable class with a check only in the constructor.
- `PermissionLab.java`: PoC showing how the check is bypassed.
- `ValidatedSecureDocument.java`: Secure solution that duplicates the check in `readObject`.

## Instructions

1.  **Observe the Bypass**:
    ```bash
    javac *.java
    java PermissionLab
    ```
    The output will show that the `SecureDocument` is successfully recovered even after permissions have been revoked. This is because `readObject` (the "hidden constructor") was never told to check permissions.

2.  **Analyze the Fix**:
    Open `ValidatedSecureDocument.java`.
    *   It implements both `writeObject` and `readObject` methods.
    *   It calls `SecurityContext.checkPermission()` in **`writeObject`** before serializing to prevent unauthorized data export.
    *   It calls `SecurityContext.checkPermission()` in **`readObject`** after `defaultReadObject()` to prevent unauthorized instantiation.
    *   This ensures that no matter how the object is created (constructor or deserialization), the current thread must have the appropriate authority.

## Key Takeaway
**Deserialization is an alternative constructor.** Any security checks, permission validations, or integrity audits performed during normal object construction must be duplicated in **both** `writeObject` (for outgoing data) and `readObject` (for incoming data). peace
