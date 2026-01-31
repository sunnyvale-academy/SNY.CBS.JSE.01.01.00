# Exercise 6-6: The Sabotaged Auditor (MUTABLE-6)

## The Scenario
"SecureAssets Inc" recently integrated their financial software with an external auditing service. To facilitate audits, the `FinancialSystem` provides its internal ledger of transactions to an `AuditModule`.

However, the internal list is passed directly to the external module. Even worse, the `Transaction` objects themselves are mutable. A malicious or buggy auditing module can change the transaction amounts in the system's internal ledger.

## Your Task
Secure the financial system by following **Guideline 6-6 / MUTABLE-6: Treat passing input to untrusted object as output**.

1.  **Analyze the Vulnerability**:
    Run `AuditApp.java`. Why did the transaction amounts in the internal ledger change after the audit?

2.  **Fix the System**:
    Identify the point in `FinancialSystem.java` where the internal state is leaked. Fix it so that the auditing module cannot modify the ledger's contents or the individual transactions.

    *Hint: You need to protect both the list AND the objects inside it (Deep Copy).*

## Instructions

1.  **Observe the Sabotage**:
    ```bash
    javac *.java
    java AuditApp
    ```
    Note how the high-value transactions were reduced to $1.00.

2.  **Apply the Fix**:
    Modify the `triggerAudit` method in `FinancialSystem.java` to pass a safe version of the ledger.

3.  **Verify**:
    Compile and run again. The malicious auditor's changes should no longer affect the internal ledger.
