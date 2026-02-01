# Lab 8-6: The Selective Guard (SERIAL-6)

## Guideline Reference
**Guideline 8-6 / SERIAL-6**: Filter untrusted serial data.

## Overview
Deserialization of untrusted data is a major security risk. Even if your application logic is correct, an attacker can use "gadget chains" (relying on classes already present in your classpath) to execute arbitrary code during the `readObject` phase.

The most effective defense is **Serialization Filtering**, introduced in Java 9. It allows you to define exactly which classes are allowed to be deserialized, blocking everything else.

## Lab Materials
- `DataPacket.java`: Harmless class allowed for transport.
- `ExploitGadget.java`: Harmful class that simulates a "payload" being executed during `readObject`.
- `VulnerableServer.java`: A server that deserializes anything without filtering.
- `SafeServer.java`: A server that uses `ObjectInputFilter` to whitelist only `DataPacket`.
- `FilterLab.java`: PoC application.

## Instructions

1.  **Observe the Exploit**:
    ```bash
    javac *.java
    java FilterLab
    ```
    In Step 2 of the output, you will see `[!] GADGET PAYLOAD EXECUTED!`. This happens because `VulnerableServer` blindly deserializes any object, including the `ExploitGadget` which has a malicious `readObject` implementation.

2.  **Analyze the Fix**:
    Open `SafeServer.java` and look at the `ObjectInputFilter` configuration:
    ```java
    ObjectInputFilter filter = ObjectInputFilter.Config.createFilter(
        "DataPacket;java.base/*;!*"
    );
    ```
    - `DataPacket`: Explicitly allow this class.
    - `java.base/*`: Allow core Java classes (like `String`, `Long`, etc.).
    - `!*`: **DENY EVERYTHING ELSE**. This is the critical security boundary.

3.  **Verify the Protection**:
    In Step 3 of the lab output, you will see `SUCCESS: Exploit blocked by ObjectInputFilter!`. The `SafeServer` caught the attempt to deserialize `ExploitGadget` before its `readObject` method could even begin.

## Key Takeaway
**Never deserialize untrusted data without a filter.** Use a **whitelist-only** filter pattern (`allowed.Class;!*`) to ensure that only expected objects can enter your system. peace
