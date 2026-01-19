# Exercise 3-4: The Silent Command

## Goal
Discover and neutralize an OS Command Injection vulnerability in a diagnostic tool, adhering to [Guideline 3-4: Avoid any untrusted data on the command line](https://www.oracle.com/java/technologies/javase/seccodeguide.html#3-4).

## The Scenario
"CyberShield Cloud" provides a dashboard for network administrators. One of the included utilities is a "Connectivity Tester" that uses the system `ping` command to check if remote servers are reachable.

## The Problem
The `VulnerableDiagnosticTool` takes a `target` hostname or IP and builds a shell command via concatenation. While it's meant to only run `ping`, the use of a shell (`sh -c`) makes it susceptible to command injection via shell metacharacters.

```java
String command = "ping -c 1 " + target;
Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
```

## Tasks

### 1. Try to Hack (The Data Breach)
A sensitive file named `server_secrets.txt` exists in the current directory. Your goal is to use the Connectivity Tester to read its contents.

- **The Goal**: Capture the "Flag" hidden in `server_secrets.txt`.
- **Payload Inspiration**: Think about how to terminate the `ping` command and start a new one (like `cat` or `printenv`).
- **Hint**: Shell metacharacters like `;`, `&`, or `|` can be used to chain commands.

### 2. The Patch (The Fix)
Refactor the tool into `SecureDiagnosticTool.java`.
- **Requirement**: Use `ProcessBuilder` with an argument list instead of `Runtime.exec` with a shell.
- **Requirement**: Implement **Input Validation**. Only allow inputs that match a valid IP address or a simple alphanumeric hostname.

### 3. Verification
Update `DiagnosticApp` (if necessary) to use your secure system and confirm that the malicious payload no longer executes.

## Running the Exercise
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java DiagnosticApp
    ```
