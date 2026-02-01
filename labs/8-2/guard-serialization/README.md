# Lab 8-2: The Visible Secret (SERIAL-2)

## Guideline Reference
**Guideline 8-2 / SERIAL-2**: Guard sensitive data during serialization.

## Overview
Even if a class should be serializable, specific fields within that class may be too sensitive to leave the JVM's memory. By default, Java serialization includes the values of all non-static, non-transient fields—even if they are `private`.

To protect sensitive data while still allowing the rest of the object to be serialized, you must explicitly guard those fields.

## Lab Materials
- `CreditCard.java`: Vulnerable class where the CVV is not guarded.
- `StreamInspector.java`: Utility that scans serialized byte streams for secrets.
- `GuardLab.java`: PoC showing how the CVV leaks into the byte stream.
- `SecureCreditCard.java`: Secure version using the `transient` keyword.

## Instructions

1.  **Observe the Leak**:
    ```bash
    javac *.java
    java GuardLab
    ```
    Notice that the "private" CVV is easily found within the serialized byte stream.

2.  **Analyze the Failure**:
    Serialization acts as a "backdoor" to the object's state. It doesn't respect access modifiers like `private`. Any data stored in a non-transient field is exported in plain text (or a very easily reversible format).

3.  **Inspect the Fix**:
    Check `SecureCreditCard.java`. The `cvv` field is marked as **`transient`**.
    *   **Effect**: The JVM will skip this field during the serialization process.
    *   **Result**: When the object is deserialized, the `cvv` field will revert to its default value (null), protecting the secret from being stored or transmitted.

## Key Takeaway
**Always mark sensitive fields as `transient` in serializable classes.** This ensures that secrets like passwords, keys, or security digits never leave the secure environment of the JVM via the serialization mechanism. peace
