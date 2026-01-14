# Lab 1-4: Robust Exception Handling

## Guideline
**1-4 (DOS-4): Implement Robust Error/Exceptions handling for services.**

## Description
Exceptions can happen at any time due to many factors: invalid user input, system misconfiguration, or network failures. Robust exception handling is essential for service stability and security.

### Key Security Concerns:
1.  **Information Disclosure**: Leaking stack traces, internal database configuration, or file paths through exception messages can help an attacker map your system.
2.  **Service Availability**: Uncaught exceptions can crash the entire application or leave resources (like locks or connections) in an inconsistent state.

## Lab Content
- `VulnerableExceptionService.java`: Demonstrates poor handling where sensitive information is leaked and exceptions aren't properly compartmentalized.
- `SecureExceptionService.java`: Demonstrates best practices: generic error messages, detailed internal logging, and global uncaught exception handling.
- `ExceptionLab.java`: The driver program to trigger different error scenarios.

## Lab Instructions

1.  **Examine the Code**: Open `VulnerableExceptionService.java` and `SecureExceptionService.java`. Compare how they respond to failures.
2.  **Compile the Lab**:
    ```bash
    javac *.java
    ```
3.  **Run the Lab**:
    ```bash
    java ExceptionLab
    ```
4.  **Observe the Output**:
    - Notice how the vulnerable service reveals connection strings and stack traces.
    - Notice how the secure service returns a generic message with a `Correlation ID` that administrators can use to find the detailed error in internal logs.

## Best Practices demonstrated:
- **Generic Error Messages**: "Internal Server Error" instead of "NullPointerException at line 42".
- **Internal Logging**: Detailed errors are logged where attackers cannot see them.
- **Fail Securely**: Use `try-catch-finally` to ensure resources are released.
- **Global Handlers**: Define `Thread.setDefaultUncaughtExceptionHandler` to catch unforeseen issues.
