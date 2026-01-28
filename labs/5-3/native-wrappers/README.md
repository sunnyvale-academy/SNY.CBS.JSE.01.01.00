# Lab 5-3: Secure Wrappers for Native Methods

## Guideline Reference
**Guideline 5-3 / INPUT-3: Define wrappers around native methods**

## Vulnerability Explanation
Native methods (JNI) bypass many of Java's built-in safety mechanisms. When using native code, you must be extremely careful to:
1.  **Encapsulate the Native Layer**: Make native methods `private` so they cannot be invoked by untrusted code that might try to provide malformed memory pointers or buffer sizes.
2.  **Defensive Copying**: Always clone arrays or mutable objects before passing them to native code. This prevents a "Time of Check to Time of Use" (TOCTOU) attack where another thread modifies the data after it's been validated but before it's processed.
3.  **Robust Validation**: Prevent integer overflows in range checks. Comparing `offset + len > buffer.length` is dangerous because `offset + len` can overflow to a small negative value, bypassing the check.

## Instructions
1.  **Compile the code**:
    ```bash
    javac com/security/nativecode/*.java
    ```

2.  **Run the exploit demo**:
    ```bash
    java com.security.nativecode.LabRunner
    ```
    Observe how `VulnerableNativeWrapper` is tricked by the integer overflow exploit, while `NativeMethodWrapper` (the secure version) correctly identifies the malicious input.

3.  **Explore the Fix**:
    Open `NativeMethodWrapper.java` and notice the check:
    ```java
    if (offset < 0 || len < 0 || offset > data.length - len)
    ```
    Using subtraction (`data.length - len`) instead of addition (`offset + len`) is mathematically safe from overflow in this context.

## Key Takeaways
- Always wrap `private native` methods with `public` non-native checkers.
- Use `clone()` for defensive copying when crossing the native trust boundary.
- Be vigilant about integer overflow in arithmetic range checks.
