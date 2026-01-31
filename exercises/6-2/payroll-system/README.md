# Exercise 6-2: The Payroll Sabotage (MUTABLE-2)

## Guideline Reference
**Guideline 6-2 / MUTABLE-2**: Create copies of mutable output values.

## The Situation
You are maintaining a Corporate Payroll System. The `Payroll` class manages a list of `SalaryRecord` objects.

The system is designed to allow other components (like an Audit component) to read the payroll records. However, the `getRecords()` method in `Payroll.java` returns a direct reference to its internal list. This means any component that calls this method can modify the company's payroll data without going through proper channels!

## Your Task
1.  **Analyze the vulnerability**: Look at `Payroll.java` and how `PayrollExploit.java` manages to add a fake employee and change salaries just by calling a "getter" method.
2.  **Fix the vulnerability**: Modify `Payroll.java` to protect its internal state.
    *   Perform **defensive copying** of the list before returning it.
    *   **Advanced fix**: Return an **unmodifiable view** of the list (e.g., using `Collections.unmodifiableList`).
    *   **Important**: Remember that the *elements* of the list (`SalaryRecord`) are also mutable! To be fully secure, you should also copy each `SalaryRecord` object.

## Instructions

1.  **Compile and run the vulnerable version**:
    ```bash
    javac *.java
    java PayrollExploit
    ```
    Observe how "Initial State" and "Final State" differ.

2.  **Fix Payroll.java**:
    Refactor `getRecords()` to return a copy of the list.

3.  **Compile and run again**:
    If you returned a copy, the "Sabotage" will still happen on the *copy*, but the `payroll` object's "Final State" should remain unchanged.
