# Lab 5-4: Verify API Behavior

## Guideline Reference
**Guideline 5-4 / INPUT-4: Verify API behavior related to input validation**

## Vulnerability Explanation
Developers often rely on standard APIs (like `java.nio.file.Path` or `java.net.URL`) to perform validation or "normalization" of data. This can be dangerous for two reasons:
1.  **Assumed Rejection**: Assuming an API will throw an exception for malformed or dangerous input (e.g., assuming `Paths.get()` rejects `..` sequences).
2.  **Raw Input Reuse**: Performing validation but then continuing to use the original, untrusted input instead of the object returned or transformed by the API.

In this lab, we use `Paths.get()` to demonstrate how a directory traversal attack can succeed if the developer doesn't explicitly normalize and verify the resulting `Path` object.

## Instructions
1.  **Compile the code**:
    ```bash
    javac com/security/api/*.java
    ```

2.  **Run the demo**:
    ```bash
    java com.security.api.LabRunner
    ```
    Notice how the `VulnerableFileService` resolves the path to `/app/data/storage/../../etc/passwd`, effectively pointing to `/etc/passwd`, while the `SecureFileService` detects the traversal and blocks it.

3.  **Explore the Fix**:
    Open `SecureFileService.java`. We follow the guideline by:
    -   Creating the `Path` object first.
    -   Calling `normalize()` to resolve the `..` segments.
    -   **Crucially**: Validating the *normalized result* to ensure it's still within the allowed base directory.

## Key Takeaways
- Never assume an API does validation "for free" unless explicitly documented.
- Always use the sanitized/normalized object returned by an API, not the raw input.
- Different APIs may interpret the same data differently; consistency is key.
