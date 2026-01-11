# Exercise 1-3: Vulnerable File Manager

## Guideline
**1-3 (DOS-3): Resource limit checks should not suffer from integer overflow.**

## Description
A system that manages file handles for different users needs to ensure that the total number of open files does not exceed a certain limit to avoid exhausting operating system resources (Denial of Service).

In this exercise, you will examine a `VulnerableFileManager` that attempts to calculate the total requested files by multiple users. However, the calculation is vulnerable to integer overflow, allowing a user to bypass the resource limit.

## Goal
Create a secure implementation of the file manager that prevents the integer overflow bypass.

## Instructions

1.  **Examine the Vulnerability**: Open `VulnerableFileManager.java`. Look at the `requestFiles(int numUsers, int filesPerUser)` method. How can the multiplication be manipulated?
2.  **Compile the Exercise**:
    ```bash
    javac *.java
    ```
3.  **Run the Exploit**:
    ```bash
    java OverflowExploit
    ```
    The output will show that the vulnerable manager allows a request that clearly exceeds the limit.
4.  **Fix the Vulnerability**: Create a new class `SecureFileManager.java` with a secure implementation of `requestFiles`. Ensure that the total number of files Calculation does not suffer from integer overflow. You can use `Math.multiplyExact()` or perform the check using `long` types.
5.  **Test the Fix**: Uncomment the relevant lines in `OverflowExploit.java` to test your `SecureFileManager`.

## Verification
Recompile and run `OverflowExploit` again. The request should now be rejected.
