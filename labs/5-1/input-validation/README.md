# Lab 5-1: Validate Inputs

## Guideline Reference
**Guideline 5-1 / INPUT-1: Validate inputs**

## Vulnerability Explanation
Input from untrusted sources must be validated before use. Untrusted input includes not just external network data, but also internal method arguments when crossing trust boundaries. Failing to validate inputs can lead to:
- **Business Logic Errors**: e.g., allowing negative quantities in an order, which might result in unauthorized refunds.
- **Technical Vulnerabilities**: e.g., integer overflows that cause calculations to wrap around, potentially bypassing price checks or leading to buffer overflows in other languages.

In this lab:
1. `VulnerableOrderService` takes an `Order` object but doesn't check if `quantity` or `price` are positive.
2. It calculates the total using simple multiplication (`quantity * price`), which can overflow the 32-bit `int` range.

## Instructions
1.  **Compile the code**:
    ```bash
    javac com/security/order/*.java
    ```

2.  **Run the lab**:
    ```bash
    java com.security.order.LabRunner
    ```
    Observe how negative quantities result in negative totals, and large quantities result in overflowed values.

3.  **Fix the vulnerability**:
    Update `VulnerableOrderService.java` to:
    -   Validate that `quantity` and `pricePerUnit` are strictly positive (using `if` statements).
    -   Use `Math.multiplyExact(int, int)` for the calculation. This method throws an `ArithmeticException` if the result overflows, preventing silent wrap-around errors.

4.  **Verify the fix**:
    Recompile and run. The service should now reject invalid input or crash safely with an exception instead of producing incorrect financial results.

## Key Takeaways
- Always validate numeric inputs for range and validity.
- Use overflow-aware methods like `Math.addExact`, `Math.multiplyExact`, etc., for sensitive calculations.
- Validation should happen as early as possible after receiving input.
