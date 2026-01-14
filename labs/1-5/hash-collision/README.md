# Lab 1-5: Hash Collision Denial of Service

## Guideline
**1-5 (DOS-5): Avoid using user input as hash keys.**

## Description
This lab demonstrates how an attacker can exploit the predictable nature of Java's `String.hashCode()` to cause a Denial of Service (DoS) attack. 

When a hash collision occurs, multiple keys end up in the same bucket of a `HashMap`. Historically, these buckets were implemented as linked lists, making insertion and lookup O(n) instead of O(1). By forcing thousands of collisions, an attacker can make an application consume excessive CPU time.

### Vulnerability Mechanics
- **Predictable Hash**: `String.hashCode()` follows a well-known formula.
- **Complexity Degradation**: Constant time operations become linear, leading to quadratic overall processing time for a batch of requests.

## Lab Content
- `VulnerableSessionManager.java`: A service that stores user sessions using strings as keys.
- `HashCollisionExploit.java`: A tool that generates colliding strings and measures the time it takes to populate the session manager.

## Lab Instructions

1.  **Compile the Lab**:
    ```bash
    javac *.java
    ```
2.  **Run the Exploit**:
    ```bash
    java HashCollisionExploit
    ```
3.  **Analyze Results**:
    Notice the difference in time between inserting 10,000 "random" keys vs. 10,000 "colliding" keys. The colliding keys will take significantly longer to process.

## Key Takeaway
Never use raw, untrusted user input as keys in hash-based collections without validation or limits. Modern Java (8+) mitigates this using "treeification" (converting buckets to trees), but the principle of avoiding predictable hash keys remains a core security fundamental.
