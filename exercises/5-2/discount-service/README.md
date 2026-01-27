# Exercise 5-2: The Discount Deceit

## Guideline Reference
**Guideline 5-2 / INPUT-2: Validate output from untrusted objects as input**

## Vulnerability Explanation
Even if data is coming from a Java object within the same JVM, it must be validated if that object is "untrusted" (e.g., it implements an interface and was provided by an external source). In this e-commerce scenario, the `CheckoutService` accepts a `DiscountProvider`. It assumes the provider will return a sane multiplier between 0.0 and 1.0. A malicious provider can return negative values, `NaN`, or extremely large numbers to disrupt financial calculations.

## Instructions
1.  **Compile and run the exercise**:
    ```bash
    javac com/security/discount/*.java
    ```
    ```bash
    java com.security.discount.ExerciseRunner
    ```
    Notice how the negative multiplier results in a negative final total (charging the store instead of the user) and how `NaN` results in a `NaN` total.

2.  **Identify the flaw**:
    Find where `getDiscountMultiplier` is called in `CheckoutService.java`. Why is it dangerous to use this value directly?

3.  **Fix the vulnerability**:
    Update the checkout logic to:
    -   Validate that the multiplier is finite (`Double.isFinite()`).
    -   Ensure the multiplier is within an acceptable range (e.g., between 0.5 and 1.0).
    -   Handle invalid multipliers by either throwing an exception or defaulting to no discount (1.0).

## Key Takeaways
- Treat method return values from untrusted interfaces as untrusted input.
- Be particularly careful with floating-point values from external sources.
