# Lab 6-9: The Global Hijack (MUTABLE-9)

## Guideline Reference
**Guideline 6-9 / MUTABLE-9**: Make public static fields final.

## Overview
Public static fields act as global variables. If they are not `final`, any code running in the JVM can modify them at any time. This can lead to unpredictable behavior, data corruption, and security vulnerabilities (e.g., bypassing environment checks).

Furthermore, even if a field is `final`, if it refers to a mutable object like an **array**, the contents of that object can still be modified.

## Lab Materials
- `SystemConfig.java`: A configuration class with vulnerable public constants.
- `ConfigExploit.java`: A module that hijacks the global configuration.
- `ConfigLab.java`: PoC app demonstrating the impact.

## Instructions

1.  **Observe the Vulnerability**:
    ```bash
    javac *.java
    java ConfigLab
    ```
    Note how the "PRODUCTION" environment is changed to "HACKED_BY_MALWARE" and the admin IP is replaced.

2.  **Analyze the Failure**:
    In `SystemConfig.java`, `ENVIRONMENT` lacks the `final` keyword, and `ADMIN_IPS` is an array.

3.  **Inspect the Fix**:
    Check `SecureSystemConfig.java`. It uses `final` for literals and `List.of()` or `Collections.unmodifiableList()` for collections to ensure absolute immutability.

## Key Takeaway
**Public constants must be `public static final` AND immutable.** Avoid using arrays for public constants; use `List.of()` or other immutable collections instead.
