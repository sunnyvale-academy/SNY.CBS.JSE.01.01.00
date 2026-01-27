# Exercise 5-1: The Magic Withdrawal

## Guideline Reference
**Guideline 5-1 / INPUT-1: Validate inputs**

## Vulnerability Explanation
Input validation is crucial when data crosses trust boundaries. In this banking scenario, the `VulnerableBankService` assumes the `amount` in a `TransferRequest` is always a positive number. By providing a negative amount, an attacker can trick the system into adding money to their own account and subtracting it from another (like a central vault).

## Instructions
1.  **Compile and run the exercise**:
    ```bash
    javac com/security/bank/*.java
    ```
    ```bash
    java com.security.bank.BankApp
    ```
    Notice how the "ATTACK" scenario results in the user's balance increasing by $10,000 instead of decreasing.

2.  **Identify the vulnerability**:
    Look at `processTransfer` in `VulnerableBankService.java`. How is the amount used? Why does a negative value break the logic?

3.  **Fix the vulnerability**:
    Update the transfer logic to:
    -   Ensure the `amount` is strictly greater than zero.
    -   Consider what else might need validation (e.g., does the sender have enough balance? Are the account IDs valid?).

## Key Takeaways
- Never trust numeric inputs without range validation.
- Business logic often relies on implicit assumptions (like "amounts are positive") that attackers will try to violate.
