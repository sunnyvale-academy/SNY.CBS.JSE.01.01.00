# Exercise 2-1: Ghost Paths

## Goal
Protect sensitive system information by preventing internal file paths from leaking through `FileNotFoundException` messages, adhering to [Guideline 2-1: Purge sensitive information from exceptions](https://www.oracle.com/java/technologies/javase/seccodeguide.html#2-1).

## The Vulnerability
In many Java environments, a `FileNotFoundException` automatically includes the absolute path of the missing file in its message. If your application attempts to load a file using an internal path (e.g., `/etc/shadow`, `/usr/local/apps/config/secrets.properties`) and fails, propagating this exception to the end-user or a public log reveals the internal directory structure and existence of sensitive files to potential attackers.

## Tasks
1.  **Observe the Leak**: Run the `FileApp` to see how the absolute path is exposed when a file is missing.
2.  **Sanitize the Exception**: Modify `VulnerableFileLoader.java` (or create a `SecureFileLoader.java`) to catch `FileNotFoundException`.
3.  **Implement a Safe Message**: Instead of propagating the raw exception, throw a new exception with a generic, safe message (e.g., "Configuration file is missing") that does not include the system path.
4.  **Optional - Safe Exception Pattern**: Implement a custom exception class that stores the sensitive path internally for secure logging but hides it from `getMessage()`.

## Running the Exercise
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run**:
    ```bash
    java FileApp
    ```
    Observe the "Ghost Path" appearing in the error message.
