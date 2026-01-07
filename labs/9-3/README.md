# Java Secure Coding: Guideline 9-3 - Safely invoke doPrivileged

## The Guideline (9-3 / ACCESS-3)

The Java SE Security Coding Guidelines state: **"Safely invoke java.security.AccessController.doPrivileged."**

The `doPrivileged` method allows a block of code to be executed with the full permissions of the protection domain it belongs to, ignoring the permissions of the calling code. While powerful, this can be extremely dangerous if the privileged block uses inputs provided by an untrusted caller without proper validation.

## The Vulnerability (VulnerablePrivilegedAction.java)

In `VulnerablePrivilegedAction.java`, the `readFile` method uses `doPrivileged` to access the file system based on a `filename` provided by the caller.

```java
public void readFile(final String filename) {
    AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
        // VULNERABILITY: Performing a sensitive action inside doPrivileged
        // using an unvalidated parameter from the caller.
        File file = new File(filename);
        // ...
        return null;
    });
}
```

This allows any caller (even one with very restricted permissions) to read any file that the application itself has permission to access, effectively bypassing the security manager.

## The Exploit (PrivilegedExploit.java)

The `PrivilegedExploit.java` demonstrates how an attacker can hijack the application's privileges:

```java
String sensitiveFile = "/etc/passwd";
vulnerableService.readFile(sensitiveFile);
```

The application will use its elevated permissions to attempt to read `/etc/passwd`, even though the original request came from an untrusted source.

## The Secure Solution (SecurePrivilegedAction.java)

The solution is to **validate all inputs before entering the privileged block**. The security boundary should be crossed only after the application has confirmed that the requested operation is safe and authorized within its own business logic.

```java
public void readFile(final String filename) {
    // SECURE: Validate input BEFORE calling doPrivileged
    if (filename == null || !filename.startsWith("config/")) {
        throw new SecurityException("Access Denied!");
    }

    AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
        // Now it's safe to perform the privileged action
        // ...
        return null;
    });
}
```

## How to Run the Lab

### 1. Compile
```bash
javac *.java
```

### 2. Run the Exploit
```bash
java PrivilegedExploit
```

Observe how the `Vulnerable Service` attempts to access any file provided, while the `Secure Service` enforces a strict trust zone (only files in `config/`) before elevating its privileges.

*Note: AccessController and doPrivileged are deprecated for removal in future Java versions as part of the phase-out of the Security Manager, but they remain critical for understanding historical Java security models and existing codebases.*
