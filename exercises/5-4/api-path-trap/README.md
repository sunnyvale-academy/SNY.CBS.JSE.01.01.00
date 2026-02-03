# Exercise 5-4: The Trusted Path

## Guideline
**Guideline 5-4 / INPUT-4: Verify API behavior related to input validation**

## Scenario
The `ReportManager` is part of a document management system. It's designed to only allow users to access files within `/app/reports/storage/`. The developer used `Path.resolve()` to combine the base directory with the user-provided filename, assuming this would force the path to stay within the storage folder.

## The Vulnerability
The `java.nio.file.Path.resolve(String)` method has a specific behavior: if the passed string is an **absolute path**, it returns that path directly, ignoring the path it was called on. This allows an attacker to bypass the intended directory restriction entirely.

## Your Task
1.  **Analyze the vulnerability**: Run `ReportApp` to see how the "trusted" path is hijacked.
    ```bash
    javac *.java
    java ReportApp
    ```
2.  **Fix the code**: Create a `SecureReportManager` (or modify `ReportManager`) to properly validate the resulting path.
    -   Normalize the path.
    -   Verify that it actually starts with the intended base directory.
    -   Reject any input that attempts to escape the directory.

## Success Criteria
-   Legitimate filenames like `sales.pdf` should still work.
-   Absolute paths like `/etc/passwd` or traversals like `../../etc/passwd` must be detected and blocked.
