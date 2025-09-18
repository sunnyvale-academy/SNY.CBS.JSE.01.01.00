# Java Resource Leak Vulnerability: Guideline 1-2 / DOS-2

## The Vulnerability (VulnerableResourceHandler.java)

Java Secure Coding Guideline 1-2 / DOS-2 states: "Release resources in all cases." This guideline emphasizes the critical importance of ensuring that system resources (such as file handles, network connections, database connections, etc.) are always released when they are no longer needed, regardless of whether an error occurred during their use.

In `VulnerableResourceHandler.java`, the `processData` method attempts to write data to a file using a `FileWriter`. The vulnerability lies in the `finally` block (or lack thereof for closing the resource). While a `try-finally` block is used, the `writer.close()` call is missing within the `finally` block.

```java
public void processData(String filename, String data, boolean throwException) throws IOException {
    FileWriter writer = null;
    try {
        writer = new FileWriter(filename);
        writer.write(data);
        System.out.println("Data written to " + filename);

        if (throwException) {
            throw new IOException("Simulating an error during processing.");
        }
    } finally {
        // Resource is NOT closed here. This is the vulnerability.
        // If an exception occurs (e.g., during writer.write() or if throwException is true),
        // the writer will remain open, leading to a resource leak.
    }
}
```

If an `IOException` occurs (either during the `writer.write()` call or explicitly thrown by `throwException`), the `finally` block is executed. However, because `writer.close()` is absent, the `FileWriter` object (and thus the underlying file handle) is never released. Repeated calls to this method, especially under error conditions, will exhaust the available file handles on the system, leading to a Denial of Service (DoS) for the application or even the entire system.

## The Exploit (ResourceLeakExploit.java)

The `ResourceLeakExploit.java` demonstrates how this resource leak can be exploited to cause a Denial of Service. The exploit repeatedly calls the `processData` method of `VulnerableResourceHandler`, intentionally triggering an exception (`throwException = true`). Each time an exception occurs, a new `FileWriter` is opened, but its handle is never closed.

```java
for (int i = 0; i < maxFiles; i++) {
    String filename = baseFilename + i + ".txt";
    try {
        // Calling processData with throwException=true will prevent the FileWriter from being closed
        // in the vulnerable implementation.
        handler.processData(filename, "Leaked data for file " + i, true);
        openedFiles++;
    } catch (IOException e) {
        System.err.println("Caught exception after opening " + openedFiles + " files: " + e.getMessage());
        System.err.println("Exploit successful: Resource exhaustion (e.g., Too many open files) likely occurred.");
        break; // Stop if an exception occurs, indicating resource exhaustion
    } // ...
}
```

Eventually, the operating system will run out of available file handles, causing subsequent `FileWriter` creations to fail with an `IOException` (e.g., "Too many open files"). This demonstrates a Denial of Service condition, where legitimate operations can no longer proceed due to resource exhaustion caused by the unreleased resources.

## The Secure Solution (SecureResourceHandler.java)

To prevent resource leaks, resources must be explicitly closed in a `finally` block or, preferably, by using Java 7's try-with-resources statement. The `SecureResourceHandler.java` demonstrates the recommended approach using try-with-resources.

```java
public void processData(String filename, String data, boolean throwException) throws IOException {
    // Using try-with-resources to ensure the FileWriter is always closed
    try (FileWriter writer = new FileWriter(filename)) {
        writer.write(data);
        System.out.println("Data written to " + filename);

        if (throwException) {
            throw new IOException("Simulating an error during processing.");
        }
    } // The writer is automatically closed here, even if an exception occurs
}
```

The try-with-resources statement ensures that any resource that implements `java.lang.AutoCloseable` (like `FileWriter`) is automatically closed at the end of the `try` block, regardless of whether the `try` block completes normally or abruptly due to an exception. This guarantees that file handles are always released, preventing resource exhaustion and Denial of Service attacks related to unreleased resources. This adheres to Guideline 1-2 / DOS-2 by ensuring resources are released in all cases.

