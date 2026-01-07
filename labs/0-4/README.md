# Java Secure Coding: Guideline 0-4 - Establish Trust Boundaries

## The Guideline (0-4 / FUNDAMENTALS-4)

The Java SE Security Coding Guidelines state: **"Establish trust boundaries."**

A system should be designed with clearly defined trust boundaries. Data crossing a trust boundary must be validated. Components that are on different sides of a trust boundary should be isolated.

## The Vulnerability (VulnerableTrustBoundary.java)

In `VulnerableTrustBoundary.java`, the system fails to establish a clear trust boundary between the input provided by a potentially untrusted caller and the sensitive file system operations.

```java
public void processDataFile(String filename) {
    // VULNERABILITY: This method assumes the 'filename' is safe.
    // It fails to establish its own trust boundary check.
    File file = new File("uploads/" + filename);
    // ...
}
```

By blindly prepending `"uploads/"` to the user-provided `filename`, the application allows an attacker to use "Path Traversal" characters (like `../`) to escape the intended directory. This is a classic example of trusting data that crosses a boundary (from the caller to the service) without validation.

## The Exploit (TrustBoundaryExploit.java)

The `TrustBoundaryExploit.java` demonstrates how an attacker can bypass the intended directory restriction:

```java
String maliciousFile = "../../etc/passwd";
vulnerableService.processDataFile(maliciousFile);
```

When this is executed, the `VulnerableTrustBoundary` resolves the path to `uploads/../../etc/passwd`, which the operating system interprets as `/etc/passwd`. The trust boundary has been breached.

## The Secure Solution (SecureTrustBoundary.java)

To fix this, the service must explicitly define and enforce its trust boundary. This is done by:
1. **Canonicalizing** the paths to resolve any `..` or symbolic links.
2. **Validating** that the resulting canonical path still starts with the trusted base directory.

```java
public void processDataFile(String filename) throws IOException {
    File baseDir = new File("uploads").getCanonicalFile();
    File file = new File(baseDir, filename).getCanonicalFile();

    // SECURE: Explicitly enforce the trust boundary
    if (!file.getPath().startsWith(baseDir.getPath())) {
        throw new SecurityException("Insecure file path detected: Access denied.");
    }
    // ...
}
```

## How to Run the Lab

### 1. Setup
Create an `uploads` directory in the current folder so the legitimate requests can find a base:
```bash
mkdir uploads
touch uploads/user_data.txt
```

### 2. Compile
```bash
javac *.java
```

### 3. Run the Exploit
```bash
java TrustBoundaryExploit
```

Observe how the `Vulnerable Service` allows the path traversal, while the `Secure Service` detects the boundary violation and throws a `SecurityException`.
