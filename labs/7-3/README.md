# Java Secure Coding: Guideline 7-3 - Partial Initialization

## The Guideline (7-3 / OBJ-3)

The Java SE Security Coding Guidelines state: **"Defend against partially initialized instances of non-final classes."**

When a constructor throws an exception, the object creation fails from the caller's perspective. However, the object itself may have already been allocated in memory. If the class is not `final`, a malicious subclass can override the `finalize()` method to capture a reference to this partially initialized object, bypassing any security checks that were supposed to happen in the constructor.

## The Vulnerability (VulnerableSensitiveClass.java)

In `VulnerableSensitiveClass.java`, the constructor validates its input and throws an exception if it's invalid. 

```java
public VulnerableSensitiveClass(String secret) {
    if (secret == null || secret.isEmpty()) {
        throw new IllegalArgumentException("Secret is required!");
    }
    this.initialized = true;
}
```

Because this class is not `final` and has a `public` or `protected` constructor, it can be subclassed by an attacker.

## The Exploit (FinalizerAttackExploit.java)

The `FinalizerAttackExploit.java` demonstrates the "Finalizer Attack":
1. The attacker creates a subclass that overrides `finalize()`.
2. The subclass constructor calls the super constructor with invalid data, causing it to fail.
3. The JVM eventually runs the garbage collector, which calls the `finalize()` method on the "failed" object.
4. The `finalize()` method saves a reference to the object (`stolenReference = this`).
5. The attacker can now call methods on the `stolenReference`, even though it was never properly initialized.

## The Secure Solution (SecureSensitiveClass.java)

The most effective way to prevent this attack is to make the class `final`. If a class cannot be subclassed, its `finalize()` method cannot be overridden.

```java
public final class SecureSensitiveClass {
    // SECURE: 'final' keyword prevents subclasses and finalizer attacks.
    // ...
}
```

Other defenses include:
- Using a `static` factory method with a `private` constructor.
- Checking an `initialized` flag in every sensitive method (defense-in-depth).
- Overriding `finalize()` in your own class and making it `final`.

## How to Run the Lab

### 1. Compile
```bash
javac *.java
```

### 2. Run the Exploit
```bash
java FinalizerAttackExploit
```

*Note: The exploit relies on non-deterministic Garbage Collection behavior. If it fails, try running it again. It uses `System.gc()` and `System.runFinalization()` to encourage the JVM to run the finalizer.*
