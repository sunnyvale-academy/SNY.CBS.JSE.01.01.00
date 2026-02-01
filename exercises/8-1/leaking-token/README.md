# Exercise 8-1: The Leaking Token (SERIAL-1)

## The Scenario
The "SecureSession" library uses a `SessionToken` class to manage user identities. To keep things safe, the authentication secret is stored in a `private final String secretValue` field with no getter method. The developer thought this was enough to keep the secret hidden inside the memory of the application.

However, the class implements `java.io.Serializable` so that session tokens can be stored in a distributed cache. An attacker discovered that they can simply serialize the token to a byte stream and read the "private" secret directly from the data!

## Your Task
Secure the `SessionToken` class by following **Guideline 8-1 / SERIAL-1: Avoid serialization for security-sensitive classes**.

1.  **Observe the Leak**:
    Run `SessionApp.java`. Notice how the `TokenSniffer` extracts the `secretValue` even though it's private and has no getter.

2.  **Fix the Token**:
    Modify `SessionToken.java` to prevent this information disclosure.

    *Hint: If the class truly contains secrets that should never leave the JVM's memory, the best fix is to remove `Serializable`. If you MUST serialize parts of the object but not the secret, look into using the `transient` keyword.*

## Instructions

1.  **Observe the Vulnerability**:
    ```bash
    javac *.java
    java SessionApp
    ```
    Confirm that the "hidden" secret is printed by the sniffer.

2.  **Apply the Fix**:
    Update `SessionToken.java` to block serialization or hide the secret.

3.  **Verify**:
    Compile and run again. If you removed `Serializable`, the `TokenSniffer` (which depends on serialization) will fail to extract the data, and the application will throw an error or fail to compile.
