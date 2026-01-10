# Exercise 1-2: Release resources in all cases (Locks)

## Guideline: 1-2 / DOS-2
> Release resources in all cases.

## The Problem
While "resources" often refers to files or sockets, it also applies to **locks**. If a thread acquires a lock and then terminates abruptly due to an unhandled exception without releasing it, any other threads waiting for that lock will block indefinitely. This is a form of Denial of Service (DoS).

## Task
In this exercise, you are provided with `VulnerableLockHandler.java`.

1.  Examine `VulnerableLockHandler.java`. Notice how the `unlock()` call is positioned.
2.  Compile the code:
    ```bash
    javac *.java
    ```
3.  Run the exploit to see the hang in action:
    ```bash
    java LockExploit
    ```
    The program will demonstrate that `LeakingThread` fails and leaves the lock acquired, causing `HangingThread` to wait forever.

4.  **Fix the vulnerability**: Modify `VulnerableLockHandler.java` to ensure that `lock.unlock()` is **always** called, regardless of whether an exception occurs in the critical section.

## Verification
After your fix, re-compile and run the exploit. The exploit should now report `[FAILURE] Exploit failed` because your "Secure" version allowed `Thread 2` to complete! (In this context, failure of the exploit means success for your security fix).
