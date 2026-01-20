# Lab 3-6: The Color Profile Leak

## Goal
Understand the privacy and security risks associated with untrusted BMP files and learn how to implement restricted image processing policies, following [Guideline 3-6: Care with BMP files](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-6).

## The Guideline (3-6 / INJECT-6)
BMP (Bitmap) image files can contain references to local ICC (International Color Consortium) files in their headers. When a Java application uses standard APIs like `ImageIO` to read a BMP file, the underlying parser might attempt to read these referenced ICC profile files from the local file system.

### What is Injected?
The "injection" is not code, but a **local file path** placed inside the BMP's DIB header (specifically the `BITMAPV5HEADER`). 
- When the `bV5CSType` field is set to `LCS_PROFILE` (0x50524F46), the parser looks at `bV5ProfileData` and `bV5ProfileSize` to find the path to the color profile.
- If an attacker sets this path to `/etc/passwd` or `C:\windows\win.ini`, the Java application will attempt to open and read that file when `ImageIO.read()` is called.

### Why is this a risk?
Even if the parser doesn't "output" the content of the `/etc/passwd` file, the **information leak** happens through:
1. **The Act of Reading**: Merely opening a file can trigger side effects (updating access times, triggering UNC requests).
2. **Error Messages (Confirmation)**: If the file exists but isn't a valid ICC profile, the system throws an error like `Invalid ICC Profile: /etc/passwd`. This **confirms the file exists**.
3. **SSRF**: Triggering outbound requests via UNC paths (`\\attacker\share`).

## The Direct API Proof
While different JDK versions might have different levels of "silence" in their BMP readers, the root cause is the `java.awt.color.ICC_Profile.getInstance(path)` API.
- This lab directly demonstrates that calling this API with an arbitrary path (like `/etc/passwd`) results in a file read.
- A vulnerable parser is simply one that takes a path from a file header and passes it to this risky API.

The guideline recommends **avoiding BMP files from untrusted sources** or ensuring the parser is configured to avoid reading external files.

## The Vulnerability (VulnerableImageProcessor.java)
The `VulnerableImageProcessor` accepts any image type and processes it using `ImageIO.read()`. This default behavior is vulnerable because it implicitly trusts the metadata in a BMP header.

```java
// VULNERABLE: Implicitly trusts BMP metadata and ICC references
BufferedImage image = ImageIO.read(imageStream);
```

## The Solution (SecureImageProcessor.java)
The `SecureImageProcessor` implements a strict format policy. It inspects the image format before processing and rejects BMP files, adhering to the guideline's advice for untrusted input.

```java
// SECURE: Validate image format and reject risky types like BMP
if ("bmp".equalsIgnoreCase(formatName)) {
    throw new SecurityException("BMP format is not allowed for untrusted uploads.");
}
```

## Running the Lab
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java BmpApp
    ```
    Observe how the vulnerable processor attempts to handle the BMP, while the secure processor identifies and blocks it as a security precaution.
