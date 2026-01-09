# Exercise 0-1: The boolean trap

## The Guideline (0-1 / FUNDAMENTALS-1)

The Java SE Security Coding Guidelines state: **"Design APIs to avoid security concerns."**

Security should be a primary consideration in API design. Retrofitting security into an existing API is often difficult and error-prone. One common pitfall in API design that leads to security issues is the "Boolean Parameter Trap."

## The Vulnerability: The Boolean Parameter Trap

The "Boolean Parameter Trap" occurs when a method uses a boolean flag to control security-critical behavior. This is dangerous because:
1.  **Ambiguity**: It's easy to forget what `true` or `false` means without looking at the method signature.
2.  **Accidental Reversal**: A developer might accidentally swap the values, leading to disastrous consequences (e.g., logging sensitive data to a public channel).
3.  **Default Behavior**: If the method is overloaded or if the language supports default parameters, the "wrong" security setting might be applied by default.

## The Exercise

In this folder, you will find:
1.  `VulnerableLogger.java`: A logger that uses a boolean flag `isPublic` to decide where to send logs.
2.  `LoggerExploit.java`: A demonstration of how easily a developer can make a mistake and leak sensitive information.

### What you have to do:
1.  Analyze `VulnerableLogger.java` and `LoggerExploit.java` to understand the risks of the "Boolean Parameter Trap".
2.  **Create a new class `SecureLogger.java`** with an improved API design that avoids boolean flags for security decisions. Think about using Enums or separate methods to make the intent clear and the API self-documenting.
3.  **Test your solution** by uncommenting and completing the placeholder block in `LoggerExploit.java`.

### Observe the Results

Run the exploit to see the leak in action:
```bash
javac *.java
java LoggerExploit
```

You will see that a simple mistake in the boolean flag results in:
`[PUBLIC LOG]: Database password is 'S3cr3tP@ss!'`

## Secure Solution

The most robust way to prevent the "Boolean Parameter Trap" is to use **Java Enums**. This makes the security intent explicit and the code self-documenting.

### Option A: Using Enums (Recommended)

Using an Enum forces the developer to choose a named constant, making the code much easier to read and harder to break.

```java
public class SecureLogger {
    public enum LogType {
        PUBLIC, SENSITIVE
    }

    public void log(String msg, LogType type) {
        if (type == LogType.PUBLIC) {
            System.out.println("[PUBLIC LOG]: " + msg);
        } else {
            System.out.println("[SENSITIVE INTERNAL LOG]: " + msg);
        }
    }
}
```

### Option B: Separate Methods

If an Enum is not suitable, providing separate, clearly named methods is a good alternative.

```java
public void logPublic(String msg) { ... }
public void logSensitive(String msg) { ... }
```

Using these patterns ensures that the security level is never a "mystery" boolean value.

