# Exercise 4-5: Limit Extensibility with Sealed Classes

## Guideline Reference
**Guideline 4-5 / EXTEND-5: Limit the extensibility of classes and methods**

## Vulnerability Explanation
In many systems, you want to allow a specific set of implementations for a security-critical interface or class, but prevent arbitrary third parties from creating their own. If an interface like `PaymentProvider` is open, an attacker could introduce a `MaliciousProvider` that steals sensitive data or interferes with transactions.

While making classes `final` is one way to limit extensibility, sometimes you *do* need a hierarchy. **Sealed Classes and Interfaces** (introduced in Java 17) allow you to specify exactly which classes are permitted to extend or implement them.

## Your Task
Currently, the `PaymentProvider` interface is open to any implementation. This allows the `MaliciousProvider` to be registered and used by the system.

Your goal is to restrict the `PaymentProvider` interface so that only `AuthorizedCreditCardProvider` (and any other trusted providers you choose) can implement it.

1.  **Compile and Run the vulnerable version**:
    ```bash
    javac com/security/payment/*.java
    ```
    ```bash
    java com.security.payment.ExerciseRunner
    ```
    Observe that both the trusted and the malicious providers are executed.

2.  **Apply the fix**:
    -   Modify `com/security/payment/PaymentProvider.java` to be a `sealed interface`. Use the `permits` keyword to only allow `AuthorizedCreditCardProvider`.
    -   Modify `com/security/payment/AuthorizedCreditCardProvider.java` to be either `final`, `sealed`, or `non-sealed` (as required for any implementation of a sealed type). In this case, `final` is the most secure choice.

3.  **Verify the fix**:
    Attempt to compile the code again:
    ```bash
    javac com/security/payment/*.java
    ```
    The compilation should now **fail**. The compiler should report that `MaliciousProvider` is not allowed to implement the sealed interface `PaymentProvider`.

## Key Takeaways
- Use `sealed` classes and interfaces to define a closed and controlled inheritance hierarchy.
- This provides a stronger guarantee than just "not final" while still allowing polymorphism.
- It prevents unauthorized "logical" extensions of your security model.
