# Exercise 4-4: Shadow Loader

This exercise demonstrates **Guideline 4-4: Limit exposure of ClassLoader instances**.

Exposing `ClassLoader` instances to untrusted code is a significant security risk. An attacker can use an exposed class loader to:
- Access internal or restricted classes.
- Load malicious classes into the same namespace.
- Gain information from resource URLs.
- Change the assertion status of classes.

## The Scenario: The Vulnerable Service Registry

You are working on a `ServiceRegistry` that helps clients locate and use various system services. However, the current implementation of `ClassLoaderService` has a critical flaw: it provides a `getLoader()` method that returns its internal `ClassLoader`.

A malicious client, `AttackApp`, can use this exposed loader to "shadow" the registry and access the `SecureAuthenticator`, an internal class that contains highly sensitive system secrets.

## Instructions

### 1. Compile the exercise
```bash
# Assumptions: You are in the exercise directory
javac com/service/ClassLoaderService.java com/service/internal/SecureAuthenticator.java com/client/AttackApp.java
```

### 2. Run the exploit
```bash
java com.client.AttackApp
```

**Observation**: Notice how `AttackApp` is able to obtain the `ClassLoader` and then use it to load and invoke a method on `SecureAuthenticator`, which should be completely hidden from the client.

## Your Task

1.  Analyze `com/service/ClassLoaderService.java` and identify why it's violating Guideline 4-4.
2.  Fix the vulnerability by removing the exposure of the `ClassLoader` without compromising the functionality of the service.
3.  Verify that `AttackApp.java` can no longer reach into the internal package.
