# Lab 6-11: The Hijacked Counter (MUTABLE-11)

## Guideline Reference
**Guideline 6-11 / MUTABLE-11**: Do not expose mutable statics.

## Overview
Exposing a `public static` mutable field creates a "global variable" that any part of the program can modify. When such a variable is used for security-critical logic—such as a lockout counter for brute-force protection—it becomes a major liability. A malicious module can simply reset the counter whenever it wants, effectively disabling the security measure.

## Lab Materials
- `SecurityMonitor.java`: Tracks failures using an exposed static counter.
- `CounterExploit.java`: Maliciously resets the counter.
- `MonitorLab.java`: PoC demonstrating the lockout bypass.

## Instructions

1.  **Observe the Bypass**:
    ```bash
    javac *.java
    java MonitorLab
    ```
    Note how the system should have locked out after 3 failures, but because `CounterExploit` reset the value, it continues to accept more failures.

2.  **Analyze the Failure**:
    In `SecurityMonitor.java`, the `failureCount` field is `public static int`. This lack of encapsulation allows anyone to reach in and change the state.

3.  **Inspect the Fix**:
    Check `SecureSecurityMonitor.java`. It makes the state `private`, provides controlled access via `recordFailure()`, and ensures that no unauthorized reset is possible.

## Key Takeaway
**Never use public static mutable fields for state.** Always encapsulate static state behind private fields and provide controlled, validated access via static methods.
