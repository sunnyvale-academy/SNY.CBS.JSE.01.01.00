# Exercise 5-3: The Buffer Boundary

## Objective
Identify and fix security flaws in a Java class that wraps native methods, following **Guideline 5-3 / INPUT-3: Define wrappers around native methods**.

## The Scenario
`NativeImageProcessor` is a class designed to interface with a high-performance native C++ library. However, it currently contains three critical security flaws:
1.  **Exposed Native Method**: The `native` method is `public`, allowing any code in the JVM to bypass the Java-side validation logic.
2.  **Lack of Defensive Copying**: The input array is used directly. This makes the code vulnerable to **TOCTOU** (Time of Check to Time of Use) attacks where another thread modifies the array *after* it has been validated but *before* the native code processes it.
3.  **Arithmetic Overflow**: The validation check `(offset + length) > pixelData.length` is vulnerable to integer overflow.

## Instructions
1.  **Run the Exploit**:
    Compile and run the exercise to see how the integer overflow bypasses the validation:
    ```bash
    javac com/security/nativecode/*.java && java com.security.nativecode.ExerciseRunner
    ```

2.  **Refactor for Security**:
    Modify `NativeImageProcessor.java` (or create a new class for the solution) to:
    -   Change the native method visibility to `private`.
    -   Implement defensive copying using `pixelData.clone()`.
    -   Rewrite the range check to be overflow-safe (using subtraction instead of addition).

## Verification
After fixing the code, the `ExerciseRunner` should correctly identify and block the exploit parameters.
