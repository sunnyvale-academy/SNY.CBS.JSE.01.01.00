# Lab 2-2: Shadow Logs

## Goal
Understand the risks of logging sensitive information (PII, credentials, etc.) and learn how to implement sanitization to comply with [Guideline 2-2: Do not log sensitive information](https://www.oracle.com/java/technologies/javase/seccodeguide.html#2-2).

## The Guideline (2-2 / CONFIDENTIAL-2)
Logging is essential for monitoring and debugging, but it can also be a source of data leaks. If sensitive information like passwords, Social Security Numbers (SSNs), or session tokens are written to log files, they become accessible to anyone with access to the logs (administrators, monitoring tools, or attackers who gain access to the logging server).

This lab demonstrates how to identify sensitive data and apply masking/sanitization before it reaches the logs.

## The Vulnerability (VulnerableLogger.java)
In common applications, developers might log entire user objects for "debugging" purposes. If the user object contains an SSN, that sensitive data is now stored in plain text in the log file.

```java
public void logUserProfile(String username, String ssn) {
    // VULNERABILITY: Logging sensitive PII directly
    System.out.println("[INFO] Processing profile for user: " + username + " (SSN: " + ssn + ")");
}
```

## The Secure Solution (SecureLogger.java)
A secure implementation should never log raw sensitive data. It should either omit it entirely or use a masking technique.

```java
public void logUserProfile(String username, String ssn) {
    // SECURE: Masking the sensitive SSN before logging
    String maskedSsn = maskSsn(ssn);
    System.out.println("[INFO] Processing profile for user: " + username + " (SSN: " + maskedSsn + ")");
}

private String maskSsn(String ssn) {
    if (ssn == null || ssn.length() < 4) return "XXX-XX-XXXX";
    return "XXX-XX-" + ssn.substring(ssn.length() - 4);
}
```

## How to Run the Lab

### 1. Compile
```bash
javac *.java
```

### 2. Run the Lab
```bash
java LogApp
```

Observe the difference between the **Vulnerable** logs (showing full SSN) and the **Secure** logs (masking the sensitive part).
