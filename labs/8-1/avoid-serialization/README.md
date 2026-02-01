# Lab 8-1: The Tampered Vault (SERIAL-1)

## Guideline Reference
**Guideline 8-1 / SERIAL-1**: Avoid serialization for security-sensitive classes.

## Overview
Java serialization allows an object's complete state, including `private` fields, to be converted into a byte stream. This stream can be saved to disk, sent over a network, or stored in a database.

The vulnerability is that once an object is serialized, its internal state is outside the protection of the JVM. An attacker can:
1.  **Extract Data**: Read sensitive values from the byte stream.
2.  **Tamper with Data**: Manually modify the byte stream to change private field values before the object is deserialized.

## Lab Materials
- `SensitiveAccount.java`: Vulnerable class that implements `Serializable`.
- `SerializeExploit.java`: Utility that performs byte-flipping on the serialization stream.
- `SerializationLab.java`: PoC showing how a private balance can be modified without using any class methods.
- `SecureSensitiveAccount.java`: Secure version that prevents serialization.

## Instructions

1.  **Observe the Tampering**:
    ```bash
    javac *.java
    java SerializationLab
    ```
    Notice how the attacker manages to change the `balance` from 100 to 999999. No setters were called; the attack happened entirely within the data stream.

2.  **Analyze the Failure**:
    In `SerializeExploit.java`, the code identifies the bytes representing the integer 100 and replaces them. Java serialization is a well-documented format, making this trivial for many field types.

3.  **Inspect the Fix**:
    Check `SecureSensitiveAccount.java`. The simplest fix is to **not** implement `Serializable`. 
    
    If your class inherits from a serializable parent, you should override `writeObject` and `readObject` to throw a `NotSerializableException`. This "locks" the class against accidental or malicious serialization.

## Key Takeaway
**Never implement `Serializable` for classes that manage sensitive data or enforce security constraints.** Serialization bypasses the normal encapsulation and "constructor-only" initialization rules of the Java language.
