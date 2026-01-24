# Lab 3-8: Untrusted Code Interpretation (XMLDecoder)

## Goal
Understand the extreme dangers of interpreting untrusted code or complex data formats that allow arbitrary method execution, following [Guideline 3-8: Take care interpreting untrusted code](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-8).

## The Guideline (3-8 / INJECT-8)
If an application source of code is untrusted, it must be executed in a secure sandbox. This applies not just to obvious "code" (like scripts) but also to data formats that the Java runtime can interpret as instructions.

### The XMLDecoder Risk
The `java.beans.XMLDecoder` class is a classic example of a dangerous interpreter. It was designed to reconstruct Java objects from XML. However, its XML schema allows for calling *any* method on *any* class, which can easily be leveraged for **Remote Code Execution (RCE)**.

## Lab Files
- `VulnerableConfigLoader.java`: Uses `XMLDecoder` to load application settings from an XML file.
- `SecureConfigLoader.java`: Uses standard `java.util.Properties` for simple key-value pairs (or would use a restricted XML parser).
- `ConfigApp.java`: Driver app that demonstrates the vulnerability and the secure alternative.

## Instructions
1.  **Compile the apps**:
    ```bash
    javac *.java
    ```
2.  **Run the application**:
    ```bash
    java ConfigApp
    ```
3.  **Observe the Exploit**:
    Look at the crafted XML payload in `ConfigApp.java`. It doesn't just store "configuration"—it instructs the JVM to use `ProcessBuilder` to execute a system command (printing a message).
4.  **Try to Hack**:
    Can you modify the payload in `ConfigApp.java` to perform a different action (e.g., list files)?

## Secure Coding Fix
Never use `XMLDecoder` with untrusted input. For simple configuration, use `java.util.Properties`. For complex object serialization, use safer alternatives like JSON (with restricted type handling) or properly configured XML parsers that do not support arbitrary method invocation.
