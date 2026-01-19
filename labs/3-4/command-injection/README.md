# Lab 3-4: The Runaway Command

## Goal
Understand the risks of OS Command Injection and learn the secure way to execute external processes in Java, following [Guideline 3-4: Avoid any untrusted data on the command line](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-4).

## The Guideline (3-4 / INJECT-4)
When executing external system commands, providing a single string for the command and its arguments (e.g., `Runtime.getRuntime().exec(commandString)`) is dangerous. If any part of that string contains untrusted data, an attacker can use shell metacharacters (like `;`, `&`, `|`, `` ` ``) to execute arbitrary commands.

## The Vulnerability (VulnerableCommandExecutor.java)
The `VulnerableCommandExecutor` attempts to get file metadata by running the `ls -l` command. It concatenates the user-provided filename directly into the command string.

```java
// VULNERABLE: String concatenation allows shell injection
String command = "ls -l " + filename;
Runtime.getRuntime().exec(command);
```

An attacker can provide a filename like `myfile.txt; echo 'HACKED'` to execute additional commands.

## The Solution (SecureCommandExecutor.java)
The `SecureCommandExecutor` uses `ProcessBuilder` with a list of arguments. This prevents the operating system from interpreting any part of the arguments as a command or shell control character.

```java
// SECURE: Argument separation prevents injection
ProcessBuilder pb = new ProcessBuilder("ls", "-l", filename);
pb.start();
```

## Running the Lab
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java CommandApp
    ```
    Observe how the vulnerable version executes the injected `echo` command, while the secure version treats the injection as a literal (and likely non-existent) filename.
