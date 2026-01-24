# Lab 4-1: Limit Accessibility

## Goal
Understand the importance of the principle of least privilege as applied to Java access modifiers, following [Guideline 4-1: Limit the accessibility of classes, interfaces, methods, and fields](https://www.oracle.com/java/technologies/javase/seccodeguide.html#4-1).

## The Guideline (4-1 / EXTEND-1)
Always declare classes, methods, and fields with the most restrictive access modifier possible. In Java, this means:
1.  **private**: Only visible within the same class.
2.  **package-private** (default): Only visible within the same package.
3.  **protected**: Visible to the same package and subclasses.
4.  **public**: Visible to everyone.

### The Risk of Excessive Visibility
If a sensitive field is `public`, any code with a reference to the object can modify it. This allows "Internal State Tampering", where an unauthorized component can bypass business rules or security checks by directly reaching into the object's internals.

## Lab Files
- `BankPolicy.java`: Contains a `public` field representing a security-sensitive policy.
- `ProtectedService.java`: A service that performs transactions and relies on the `BankPolicy`.
- `AccessibilityApp.java`: Driver app that demonstrates how easy it is to "hack" the system by exploiting visibility.

## Instructions
1.  **Compile the apps**:
    ```bash
    javac *.java
    ```
2.  **Run the application**:
    ```bash
    java AccessibilityApp
    ```
3.  **Observe the Exploit**:
    Notice how the `MaliciousActor` component can directly change the `minimumBalance` requirement to `$0.0`, allowing a withdrawal that should have been blocked by the bank's intended policy.

## Secure Coding Fix
To fix this vulnerability:
1.  Change the access modifier of the sensitive field from `public` to `private`.
2.  Provide only a `getter` method, making the field read-only to external callers.
3.  Better yet, make the entire policy class **immutable** (use `final` fields and no setters).
