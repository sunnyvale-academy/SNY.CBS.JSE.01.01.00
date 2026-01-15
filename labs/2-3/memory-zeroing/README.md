# Lab 2-3: Memory Zeroing

## Goal
Understand why sensitive data should be stored in mutable arrays (`char[]` or `byte[]`) instead of immutable `String` objects, as recommended by [Guideline 2-3: Consider purging highly sensitive information from memory after use](https://www.oracle.com/java/technologies/javase/seccodeguide.html#2-3).

## The Guideline (2-3 / CONFIDENTIAL-3)
Sensitive information, such as passwords or private keys, should not linger in memory longer than necessary. Java's `String` objects are immutable; once created, their content cannot be changed. They stay in memory (often in the String Pool) until they are garbage collected, which is outside the developer's direct control.

By using a `char[]` or `byte[]`, the developer can manually "zero out" (overwrite) the sensitive content immediately after use, significantly reducing the window of exposure.

## The Experiment (StringVsArrayLab.java)
In this lab, we simulate the handling of a secret.

1.  **Immutable String**: We create a string containing a secret. Notice that we cannot modify it to clear it.
2.  **Mutable Array**: We create a `char[]` containing a secret. After use, we call `Arrays.fill(secretArray, '0')` to purge it from memory.

## How to Run the Lab

### 1. Compile
```bash
javac *.java
```

### 2. Run the Lab
```bash
java StringVsArrayLab
```

Observe how the mutable array can be explicitly cleared, while the String remains unchanged even if we try to "reassign" it.
