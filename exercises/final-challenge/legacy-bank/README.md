# Final Challenge: The Legacy Bank Heist

## Scenario
You have been hired as a security auditor for "Legacy Bank", a financial institution using an aging Java codebase. The bank has been suffering from "mysterious" balance discrepancies and unauthorized data access.

Your mission is to perform a thorough security audit of the provided codebase, identify all vulnerabilities that violate the Oracle Secure Coding Guidelines, and fix them.

## The Challenge
The application contains at least 10 major vulnerabilities spanning from section 0 to 9 of the guidelines.

### Your Tasks:
1.  **Audit the Code**: Read through all the `.java` files in this directory.
2.  **Identify Vulnerabilities**: For each class, find the security flaw(s) and map them to the corresponding Oracle Guideline.
3.  **Exploit (Optional)**: Run `BankApp` to see the vulnerabilities in action.
    ```bash
    javac *.java
    java BankApp
    ```
4.  **Fix the Code**: Refactor the classes to follow secure coding best practices.
5.  **Verify**: Re-run your tests to ensure the vulnerabilities are mitigated and the app remains functional.

### Vulnerability Checklist:
- [ ] **Section 0**: Trust boundary violation.
- [ ] **Section 1**: Denial of Service (Integer Overflow).
- [ ] **Section 2**: Information disclosure in exceptions.
- [ ] **Section 3**: Injection (SQL Injection).
- [ ] **Section 4/6**: Accessibility of fields and mutable value types.
- [ ] **Section 5**: Missing input validation.
- [ ] **Section 7**: Object creation (finalizer attack).
- [ ] **Section 8**: Unsafe deserialization.
- [ ] **Section 9**: Unsafe use of `doPrivileged`.

## Useful Guidelines to Review:
- **0-4**: Establish trust boundaries.
- **1-3**: Resource limit checks should not suffer from integer overflow.
- **2-1**: Purge sensitive information from exceptions.
- **3-2**: Avoid dynamic SQL.
- **4-1**: Limit the accessibility of fields.
- **5-1**: Validate inputs.
- **6-1**: Prefer immutability for value types.
- **7-3**: Defend against partially initialized instances.
- **8-3**: View deserialization the same as object construction.
- **9-3**: Safely invoke `doPrivileged`.
