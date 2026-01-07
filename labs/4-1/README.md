# Java Secure Coding: Guideline 4-1 - Limit Accessibility

## The Guideline (4-1 / ACCESS-1)

The Java SE Security Coding Guidelines state: **"Limit the accessibility of classes, interfaces, methods, and fields."**

Programs should be well-encapsulated. Exposure of internal state or internal logic can lead to unauthorized modification, bypass of validation rules, and increased attack surface. By default, everything should be as private as possible.

## The Vulnerability (VulnerableBankConfig.java)

In `VulnerableBankConfig.java`, the class exposes a sensitive field and an internal initialization method as `public`.

```java
public class VulnerableBankConfig {
    public double interestRate = 0.05; // VULNERABILITY: Public field
    
    public void resetToDefault() { // VULNERABILITY: Public internal method
        this.interestRate = 0.05;
    }
}
```

This allow any other class in the JVM to modify the interest rate directly, bypassing any potential business logic or security checks.

## The Exploit (AccessibilityExploit.java)

The `AccessibilityExploit.java` demonstrates how easy it is to tamper with the state of a poorly encapsulated class:

```java
vulnerableConfig.interestRate = 0.99; // Direct tampering
vulnerableConfig.resetToDefault();    // Unauthorized method call
```

## The Secure Solution (SecureBankConfig.java)

The solution is to use the principle of least privilege for accessibility. Use `private` for fields and internal methods. Only expose what is absolutely necessary through `public` or `protected` methods, and always include validation logic where appropriate.

```java
public class SecureBankConfig {
    private double interestRate = 0.05; // SECURE: Private access
    
    public double getInterestRate() {    // SECURE: Controlled read access
        return interestRate;
    }

    private void resetToDefault() {     // SECURE: Private internal access
        this.interestRate = 0.05;
    }
}
```

## How to Run the Lab

### 1. Compile
```bash
javac *.java
```

### 2. Run the Exploit
```bash
java AccessibilityExploit
```

Observe how the exploit can freely modify the `Vulnerable Service`, while the `Secure Service` is protected by Java's access control modifiers (demonstrated by the fact that similar code would fail to compile against the secure version).
