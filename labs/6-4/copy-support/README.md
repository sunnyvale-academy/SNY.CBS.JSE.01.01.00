# Lab 6-4: Support Copy Functionality (MUTABLE-4)

## Guideline Reference
**Guideline 6-4 / MUTABLE-4**: Support copy functionality for a mutable class.

## Overview
Defensive copying is a critical security technique (see Guidelines 6-2 and 6-3). However, it relies on the *ability* to copy objects easily and correctly. 

If your class is mutable, you should provide an explicit mechanism for cloning it safely. Relying on `Object.clone()` is often discouraged due to its complexities and security risks (like the subclassing attack shown in Lab 6-3). Instead, the preferred Java patterns are **Copy Constructors** or **Static Factory Methods**.

## Lab Materials
- `MutableProfile.java`: A mutable class with no copy support.
- `ProfileLab.java`: Demonstrates the pitfalls when a user tries to manually copy a class that doesn't help them.
- `SecureProfile.java`: The fixed version with a copy constructor.

## Instructions

1.  **Compile and Run the Lab**:
    ```bash
    javac *.java
    java ProfileLab
    ```

2.  **Analyze the Failure**:
    - Observe how `MISTAKE 1` (direct assignment) leads to shared state.
    - Observe how `MISTAKE 2` (manual copying) failed because the developer forgot to clone the internal `interests` array. This is a common security bug in large systems.

3.  **Inspect the Secure Version**:
    Open `SecureProfile.java`. 
    - Note the **Copy Constructor**: `public SecureProfile(SecureProfile other)`. 
    - This constructor uses the main constructor which already handles defensive copying of the array.

## Best Practice
Always provide a copy constructor or a factory method for mutable classes. It makes your API's security much harder to break by mistake.

```java
// User code becomes clean and secure:
SecureProfile myCopy = new SecureProfile(originalProfile);
```
