# Exercise 2-2: Masked Transactions

## Goal
Protect sensitive payment information by preventing full credit card numbers from being written to application logs, adhering to [Guideline 2-2: Do not log sensitive information](https://www.oracle.com/java/technologies/javase/seccodeguide.html#2-2).

## The Vulnerability
In payment processing systems, it's common to log transaction details for auditing and troubleshooting. However, logging the full Primary Account Number (PAN) or credit card number is a severe security violation (and often a PCI-DSS violation). If these logs are compromised, thousands of customer credit cards could be exposed.

## Tasks
1.  **Observe the Leak**: Run the `TransactionApp` and check the console output (simulating an audit log).
2.  **Implement Masking**: Modify `VulnerableTransactionService.java` (or create a `SecureTransactionService.java`) to ensure that credit card numbers are masked before being logged.
3.  **Standard Masking Rule**: A common rule is to show only the last 4 digits and mask the rest (e.g., `****-****-****-1234`).
4.  **Verify**: Ensure the masked output is what appears in the logs.

## Running the Exercise
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java TransactionApp
    ```
    Observe the sensitive credit card numbers appearing in the "Audit Log" messages.
