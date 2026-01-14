# Lab 1-4: Thread Uncaught Exception Handler

## Guideline
**1-4 (DOS-4): Implement Robust Error/Exceptions handling for services.**

## Description
In multi-threaded applications, exceptions that occur in background threads often go unnoticed if they are not explicitly caught. If an exception bubbles up to the top of a thread's call stack, the thread dies, and by default, the JVM prints the stack trace to standard error.

The `Thread.UncaughtExceptionHandler` provides a mechanism to intercept these "terminal" exceptions. This allows the application to:
1.  **Log the error** properly in the system logs.
2.  **Take corrective action** (e.g., restart the thread or signal a shutdown).
3.  **Prevent sensitive info leakage** to console/stderr in production.

## Lab Content
- `GlobalExceptionHandlerLab.java`: Sets up the global handler and demonstrates the crash recovery.
- `CrashingTask.java`: A simple task designed to fail unexpectedly.

## Lab Instructions

1.  **Compile the Lab**:
    ```bash
    javac *.java
    ```
2.  **Run the Lab**:
    ```bash
    java GlobalExceptionHandlerLab
    ```
3.  **Observe the Output**:
    - Notice that even though `CrashingTask` throws a `RuntimeException`, the error is intercepted by our custom handler.
    - The main thread continues to run, proving that a crash in a background thread doesn't necessarily take down the whole VM if handled.

## Key Takeaway
Always define a `Thread.setDefaultUncaughtExceptionHandler` in your application's entry point to ensure that no crash goes unlogged and to provide a consistent safety net for all threads.
