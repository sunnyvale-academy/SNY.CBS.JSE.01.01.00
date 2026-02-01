# Lab 7-1: The Forged Session (OBJECT-1)

## Guideline Reference
**Guideline 7-1 / OBJECT-1**: Avoid exposing constructors of sensitive classes.

## Overview
A `public` constructor is an open invitation for any code to create instances of a class. For classes that represent security-sensitive state—like user sessions, permission sets, or valid payment transactions—allowing direct instantiation is dangerous.

Security logic often correctly validates credentials *before* calling a factory method to create an object. However, if the implementation class still has a public constructor, an attacker can simply ignore the factory method and "forge" an object with exactly the malicious data they want (e.g., setting their own role to "ADMIN").

## Lab Materials
- `Session.java`: Common interface for session objects.
- `VulnerableSession.java`: A sensitive class with an exposed public constructor.
- `SecureSession.java`: A secure implementation using a private constructor and factory method.
- `SessionExploit.java`: Maliciously creates a forged admin session using `VulnerableSession`.
- `SessionLab.java`: PoC demonstrating how the system accepts the forged object.

## Instructions

1.  **Observe the Forgery**:
    ```bash
    javac *.java
    java SessionLab
    ```
    Notice how `SessionExploit` successfully creates an "ADMIN" session by directly instantiating `VulnerableSession`.

2.  **Analyze the Failure**:
    In `VulnerableSession.java`, the constructor is `public`. This is the single point of failure. Even if your application primarily uses the `Session` interface, an attacker can instantiate any concrete class that has a public constructor and pass it to your methods.

3.  **Inspect the Fix**:
    Check `SecureSession.java`. It makes the constructor `private`, forcing all instantiation to go through the `createSession()` static factory method. This ensures that validation logic cannot be bypassed.

## Key Takeaway
**Hiding constructors is a fundamental defensive coding practice.** If a class represents a "proven" state, ensure only your chosen factory methods can produce it. Use interfaces for the API, but strictly control who can instantiate the implementations.
