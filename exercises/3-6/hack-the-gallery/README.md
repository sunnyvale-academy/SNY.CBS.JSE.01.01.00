# Exercise 3-6: Hack the Gallery

## Scenario
You are a security researcher auditing a high-end photography gallery application, "The Ghost Gallery". The application allows photographers to upload their JPEGs. To ensure color consistency across different screens, the application attempts to load and apply the color profile (ICC) associated with the image.

The developers implemented a custom metadata processor that looks for a special comment in the JPEG file called `Color-Profile-Reference`. If found, it uses that path to load the profile.

## The Goal
The application is running on a server where a sensitive file `/etc/passwd` exists. Your goal is to prove that an attacker can upload a specially crafted JPEG to confirm the existence of this file (and potentially others) via an information leak.

## Vulnerability (Guideline 3-6)
According to [Guideline 3-6: Care with BMP files](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-6), image files that reference external resources can be dangerous. While the guideline focuses on BMP, the same risk applies to any custom processor that takes a file path from image metadata and passes it to the `java.awt.color.ICC_Profile.getInstance(path)` API.

## Your Task: Hack the Gallery
1.  **Analyze**: Open `VulnerableGallery.java` and identify how it processes the `Color-Profile-Reference`.
2.  **Exploit**: Run `GalleryApp.java`. It simulates an attacker uploading a JPEG that references `/etc/passwd`.
3.  **Confirm**: Observe the error messages. Can you distinguish between a file that exists and one that doesn't?
4.  **Fix**: Implement a secure version of the Gallery that prevents this path injection and test it.

## Compilation and Execution
```bash
javac *.java
java GalleryApp
```
