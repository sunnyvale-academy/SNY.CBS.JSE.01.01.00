# Lab 6-10: The Mutable Constant (MUTABLE-10)

## Guideline Reference
**Guideline 6-10 / MUTABLE-10**: Ensure public static final field values are constants.

## Overview
A `public static final` field is intended to be a constant. However, the `final` keyword only guarantees that the *reference* cannot be reassigned. If the reference points to a **mutable object** (like `java.util.Date`, `java.lang.StringBuilder`, or any non-final array), the internal state of that object can still be changed by any part of the program.

This leads to a "mutable constant," which is an oxymoron that can cause severe logic errors and security vulnerabilities.

## Lab Materials
- `SystemInfo.java`: Contains a mutable "constant" using `java.util.Date`.
- `TimeExploit.java`: Demonstrates tampering with the "constant".
- `ConstantsLab.java`: PoC app.

## Instructions

1.  **Observe the Vulnerability**:
    ```bash
    javac *.java
    java ConstantsLab
    ```
    Notice how the "RELEASE_DATE" is successfully changed to 1970, even though the field is `final`.

2.  **Analyze the Failure**:
    In `SystemInfo.java`, the `RELEASE_DATE` field points to a `Date` object. `Date` is notorious for being mutable (methods like `setTime()`).

3.  **Inspect the Fix**:
    Check `SecureSystemInfo.java`. It uses `java.time.LocalDate`, which is part of the modern Java Date/Time API. Objects in the `java.time` package are **immutable**.

## Key Takeaway
**A constant field must point to an immutable value.** When choosing types for constants, prefer immutable classes (like `String`, primitives, or `java.time` classes) over mutable ones.
