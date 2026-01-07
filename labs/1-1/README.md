# Java Secure Coding: Guideline 1-1 - Denial of Service (Zip Bomb)

## The Guideline (1-1 / DOS-1)

The Java SE Security Coding Guidelines state: **"Do not expose methods that use a large amount of resources."**

When an application processes untrusted input, it must ensure that the input does not lead to excessive consumption of CPU, memory, disk space, or network bandwidth. A classic example is the "Zip Bomb".

## The Vulnerability (VulnerableZipService.java)

In `VulnerableZipService.java`, the `unzip` method decompresses a zip file without checking the size of the uncompressed data.

```java
while ((entry = zis.getNextEntry()) != null) {
    // ...
    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
        byte[] buffer = new byte[8192];
        int len;
        // VULNERABILITY: No check for size. Fills disk.
        while ((len = zis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
    }
}
```

An attacker can create a small zip file (e.g., 42KB) that expands to petabytes of data, effectively crashing the server or filling its storage.

## The Exploit (ZipBombExploit.java)

The `ZipBombExploit.java` creates a "Zip Bomb" containing highly repetitive data (100MB of zeros) that compresses into just a few kilobytes. It then demonstrates how the `SecureZipService` blocks this attack while the `VulnerableZipService` would blindly process it.

## The Secure Solution (SecureZipService.java)

To prevent this, you must implement resource limits:
1. **Max Entries**: Limit the number of files inside the zip.
2. **Max Total Size**: Limit the total uncompressed size.
3. **Max Compression Ratio**: Detect files that are suspicious by comparing compressed vs. uncompressed size.

```java
// SECURE: Check total uncompressed size
if (totalSize > MAX_SIZE) {
    throw new IOException("Zip file is too large when decompressed.");
}

// SECURE: Check compression ratio
if (compressedSize > 0) {
    double ratio = (double) totalSize / compressedSize;
    if (ratio > MAX_RATIO) {
        throw new IOException("Compression ratio too high (potential zip bomb).");
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
java ZipBombExploit
```

Observe how the `Secure Service` detects the excessively high compression ratio and stops extraction before exhausting resources.
