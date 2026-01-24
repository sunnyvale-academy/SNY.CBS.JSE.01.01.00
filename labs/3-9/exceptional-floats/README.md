# Lab 3-9: Exceptional Floating Point Values (NaN and Infinity)

## Goal
Understand the security and logic risks of exceptional floating-point values like `NaN` (Not-a-Number) and `Infinity`, and learn how to validate them following [Guideline 3-9: Prevent injection of exceptional floating point values](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-9).

## The Guideline (3-9 / INJECT-9)
In Java, floating-point types (`float` and `double`) follow the IEEE 754 standard. This standard includes special values:
- `Double.NaN`: Result of an undefined operation (e.g., `0.0 / 0.0`).
- `Double.POSITIVE_INFINITY`: Result of dividing a positive number by zero.
- `Double.NEGATIVE_INFINITY`: Result of dividing a negative number by zero.

### Why is this a risk?
`NaN` has unique comparison properties:
- `NaN == NaN` is **false**.
- `NaN < x` is **false**.
- `NaN > x` is **false**.

If an application uses comparisons to validate a range (e.g., `if (discount < 0) throw error`), a user-provided `NaN` value will **bypass** the check because it is neither less than, greater than, nor equal to 0.

## Lab Files
- `VulnerableBillingService.java`: Fails to check for `NaN`, allowing logic bypass.
- `SecureBillingService.java`: Uses `Double.isFinite()` to ensure inputs are valid numbers.
- `BillingApp.java`: Driver app demonstrating the "Price Hijack" exploit.

## Instructions
1.  **Compile the apps**:
    ```bash
    javac *.java
    ```
2.  **Run the application**:
    ```bash
    java BillingApp
    ```
3.  **Observe the Exploit**:
    Notice how the `NaN` discount bypasses the `discount < 0` check. The total price becomes `NaN`, which can corrupt accounting databases or lead to free items in downstream systems.

## Secure Coding Fix
Always validate that untrusted floating-point input is finite before using it in logic:
```java
if (!Double.isFinite(value)) {
    throw new IllegalArgumentException("Invalid number");
}
```
Alternatively, check for `NaN` and `Infinity` explicitly:
```java
if (Double.isNaN(value) || Double.isInfinite(value)) {
    // block
}
```
