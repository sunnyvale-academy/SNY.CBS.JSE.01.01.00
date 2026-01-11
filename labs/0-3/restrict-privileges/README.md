# Lab 0-3: Restricting Privileges

## Guideline
**0-3 (FUNDAMENTALS-3): Restrict privileges.**

## Description
The principle of least privilege states that a program or user should only have the minimum set of permissions necessary to perform its task. In Java, this was historically managed by the `SecurityManager` and a policy file.

However, the `SecurityManager` has been deprecated for removal (JEP 411) due to its complexity and the shift towards modern containerization and operating system-level sandboxing.

### Modern Recommendations
Instead of relying on a Java-level `SecurityManager`, Oracle recommends:
1.  **OS-level Sandboxing**: Use operating system features like Linux cgroups, namespaces, or seccomp.
2.  **Containerization**: Run untrusted components in separate containers (Docker, Podman) with restricted privileges.
3.  **Process Isolation**: Decomposition of the application into separate processes, each granted only the necessary OS permissions.

## Lab Content
This lab consists of two parts:
1.  **Legacy Approach**: `LegacySecurityManagerLab.java` shows how the `SecurityManager` was used to block file writes.
2.  **Modern Approach (Simulation)**: `ProcessIsolationLab.java` simulates how to run an untrusted task in a separate process, where the main application survives even if the sub-process fails or is compromised.

## Lab Instructions

1.  **Compile the Lab**:
    ```bash
    javac *.java
    ```
2.  **Run the Legacy Lab**:
    ```bash
    java LegacySecurityManagerLab
    ```
    Observe how the `SecurityManager` throws a `AccessControlException` (or similar depending on your JDK version). Note the deprecation warning.
3.  **Run the Modern Lab**:
    ```bash
    java ProcessIsolationLab
    ```
    Observe how the main application spawns a separate process for the untrusted task. Even if the untrusted task fails to perform a privileged action (or crashes), the main manager process remains healthy.

## Key Takeaway
Restricting privileges should be shifted as much as possible to the infrastructure layer (OS, Containers) rather than complex, application-specific security managers.
