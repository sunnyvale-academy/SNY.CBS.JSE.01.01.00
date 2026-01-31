# Lab 6-6: The Leaky Callback (MUTABLE-6)

## Guideline Reference
**Guideline 6-6 / MUTABLE-6**: Treat passing input to untrusted object as output.

## Overview
When you pass an internal mutable object as an argument to a method of an untrusted object (such as a callback, a listener, or an overridable method), you are effectively **exporting** that object. 

Even though you are providing an "input" to the other object, from the perspective of your class's integrity, this is an **output**. If the object you are passing is mutable, the untrusted code can modify it, potentially corrupting your class's internal state.

## Lab Materials
- `ProcessorCallback.java`: Interface for data handlers.
- `DataProcessor.java`: A service that leaks its internal result list to callbacks.
- `MaliciousCallback.java`: An exploit that sabotages the list it receives.
- `CallbackLab.java`: PoC demonstrating the internal state corruption.

## Instructions

1.  **Observe the Vulnerability**:
    ```bash
    javac *.java
    java CallbackLab
    ```
    Note how the `DataProcessor` has its internal results replaced by the malicious callback.

2.  **Analyze the Failure**:
    In `DataProcessor.java`, the `process` method passes its private `results` list directly. 
    ```java
    callback.handleData(results); // LEAK!
    ```

3.  **Inspect the Fix**:
    Check `SecureDataProcessor.java`. It wraps the list in an unmodifiable view:
    ```java
    callback.handleData(Collections.unmodifiableList(results)); // SECURE
    ```
    Alternatively, it could pass a defensive copy: `new ArrayList<>(results)`.

## Key Takeaway
**Never hand over internal mutable state to code you don't fully trust.** This includes callbacks, listeners, and methods that can be overridden by subclasses. Always use unmodifiable wrappers or defensive copies.
