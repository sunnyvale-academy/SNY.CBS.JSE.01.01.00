# Java Secure Coding: Guideline 2-1 - Purge Sensitive Information from Exceptions

## The Guideline (2-1 / CONFIDENTIAL-1)

The Java SE Security Coding Guidelines state: **"Purge sensitive information from exceptions."**

Exception messages are often logged to files, displayed in user interfaces, or transmitted over the network. If these messages contain sensitive data (like passwords, encryption keys, internal file paths, or session IDs), they can be intercepted by an attacker.

## The Vulnerability (VulnerableDatabaseService.java)

In `VulnerableDatabaseService.java`, the `connect` method includes the user's password in the exception message when a connection fails.

```java
public void connect(String url, String username, String password) throws Exception {
    // ...
    if (connectionFailed) {
        // VULNERABILITY: Putting sensitive information in the exception message
        throw new Exception("Connection failed for '" + url + 
                            "' using credentials [" + username + ":" + password + "]");
    }
}
```

This violates Guideline 2-1 by exposing credentials to anyone who can see the exception message.

## The Exploit (ExceptionLeakExploit.java)

The `ExceptionLeakExploit.java` demonstrates how an unauthorized observer (or a malicious application catching the exception) can extract the secret password:

```java
try {
    vulnerableService.connect(url, user, secret);
} catch (Exception e) {
    System.err.println("CAUGHT EXCEPTION: " + e.getMessage());
    // The password is now visible in the error output
}
```

## The Secure Solution (SecureDatabaseService.java)

To fix this, ensure that exception messages provided to consumers do not contain sensitive details. Detailed error information should be kept in internal system logs, while the exception message should remain generic.

```java
public void connect(String url, String username, String password) throws Exception {
    if (connectionFailed) {
        // SECURE: Throw a generic message.
        throw new Exception("Database connection failed. Please contact your system administrator.");
    }
}
```

### The "Safe Exception" Pattern (SafeDatabaseException.java)

A more robust way to implement this guideline is to create a custom exception class that overrides `getMessage()` and `toString()`. This ensures that even if developers accidentally log the exception or display it in a UI, the sensitive information is never leaked.

```java
public class SafeDatabaseException extends Exception {
    private final String sensitiveDetails;

    public SafeDatabaseException(String safeMessage, String sensitiveDetails) {
        super(safeMessage);
        this.sensitiveDetails = sensitiveDetails;
    }

    @Override
    public String getMessage() {
        return super.getMessage(); // Returns only the safeMessage
    }
}
```

In `SecureDatabaseService.java`, we now use this custom exception. The "secret" details (like the password) are passed into the exception object but are only accessible via a special internal method (`getSensitiveDetails()`) intended only for secure internal logging.

## How to Run the Lab

### 1. Compile
```bash
javac *.java
```

### 2. Run the Exploit
```bash
java ExceptionLeakExploit
```

Observe how the `Vulnerable Service` displays the plaintext password in its error message, while the `Secure Service` provides a safe, generic error.
