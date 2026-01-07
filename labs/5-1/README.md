# Java Secure Coding: Guideline 5-1 - Validate Inputs

## The Guideline (5-1 / INPUT-1)

The Java SE Security Coding Guidelines state: **"Validate inputs."**

All data received from an external or untrusted source must be validated before use. This includes checking for expected ranges, types, formats, and potential malicious characters. Validation should happen as early as possible ("Fail Fast").

## The Vulnerability (VulnerableInventoryService.java)

In `VulnerableInventoryService.java`, the `processOrder` method accepts `quantity` and `price` without any checks.

```java
public void processOrder(int quantity, double price) {
    // VULNERABILITY: No validation
    double total = quantity * price;
    // ...
}
```

If an attacker provides a negative quantity, the resulting total will be negative. In a financial system, this could lead to unauthorized refunds, price manipulation, or negative account balances.

## The Exploit (InputValidationExploit.java)

The `InputValidationExploit.java` demonstrates how missing validation can lead to incorrect logic execution:

```java
vulnerableService.processOrder(-50, 100.0);
```

The output shows a negative total of `$-5000.0`, which the system treats as a valid (though logically impossible) order.

## The Secure Solution (SecureInventoryService.java)

The solution is to explicitly check all input parameters against their expected bounds at the beginning of the method.

```java
public void processOrder(int quantity, double price) {
    // SECURE: Boundary checks
    if (quantity <= 0) {
        throw new IllegalArgumentException("Quantity must be a positive integer.");
    }
    if (price < 0) {
        throw new IllegalArgumentException("Price cannot be negative.");
    }
    // ...
}
```

## How to Run the Lab

### 1. Compile
```bash
javac *.java
```

### 2. Run the Exploit
```bash
java InputValidationExploit
```

Observe how the `Vulnerable Service` blindly processes negative quantities, while the `Secure Service` raises an `IllegalArgumentException` and stops execution.
