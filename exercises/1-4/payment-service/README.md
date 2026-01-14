# Exercise 1-4: Robust Payment Service

## Goal
Implement robust exception handling for a payment processing service to prevent information disclosure and ensure system stability.

## Vulnerability
The current `VulnerablePaymentService` has several security issues:
1.  **Technical Info Leaks**: It exposes internal database connection strings and API keys when a transaction fails.
2.  **Silent Thread Crashes**: It spawns background worker threads that crash silently when they encounter a `RuntimeException`, leaving the system in an inconsistent state.

## Tasks
1.  **Refactor the Service**: Modify the payment service (or create a new `SecurePaymentService.java`) to:
    -   Catch low-level technical exceptions (like `SQLException`).
    -   Log detailed error information internally for administrators.
    -   Return generic, safe error messages to the user/caller, including a unique Correlation ID.
2.  **Implement a Safety Net**: In `PaymentApp.java`, set a global `Thread.UncaughtExceptionHandler` to catch and log any unforeseen crashes in background threads.
3.  **Verify**: Test your implementation using `PaymentApp.java` (uncomment the secure testing section) to ensure no sensitive info is leaked and crashes are properly identified.

## Running the Exercise
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run (Vulnerable)**:
    ```bash
    java PaymentApp
    ```
    Observe the sensitive information in the "FAILED" messages and the silent failure of the background worker.
