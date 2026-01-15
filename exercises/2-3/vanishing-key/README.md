# Exercise 2-3: The Vanishing Key

## Goal
Implement a secure cryptographic key management service that purges sensitive keys from memory immediately after use, following [Guideline 2-3: Consider purging highly sensitive information from memory after use](https://www.oracle.com/java/technologies/javase/seccodeguide.html#2-3).

## The Problem
The `VulnerableKeyService` handles encryption keys as `String` objects. These keys remain in memory long after the encryption process has finished, increasing the risk that a memory dump or a "heartbleed-style" vulnerability could expose them.

## Tasks
1.  **Refactor the Service**: Create a `SecureKeyService.java` that:
    -   Receives the sensitive key as a `byte[]` or `char[]`.
    -   Uses the key for the operation.
    -   **Immediately** overwrites the contents of the array with zeros (`0`) or random bytes.
2.  **Verify**: Run `KeyApp` to ensure that the key is "vanishing" from the buffer after use.

## Running the Exercise
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java KeyApp
    ```
    Observe how the `Vulnerable` version keeps the "String" secret, while the `Secure` version (which you will implement) should show a cleared buffer.
