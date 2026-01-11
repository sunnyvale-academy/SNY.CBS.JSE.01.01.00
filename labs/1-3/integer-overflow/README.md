# Lab 1-3: Integer Overflow in Resource Limit Checks

## Guideline
**1-3 (DOS-3): Resource limit checks should not suffer from integer overflow.**

## Description
When checking if a request for resources (like memory, threads, or file handles) is within acceptable limits, it is crucial to ensure that the calculation used for the check does not overflow. If an overflow occurs, a very large request might appear small to the application, bypassing the limit check and leading to resources exhaustion or even crashes.

In Java, integer overflow occurs silently. For example, `Integer.MAX_VALUE + 1` results in `Integer.MIN_VALUE`.

## Vulnerability Example
Consider a method that allocates an array based on the number of elements and the size per element. If both values are provided by a user, their product could overflow:

```java
public void allocate(int numElements, int elementSize) {
    if (numElements * elementSize > MAX_ALLOWED_BYTES) {
        throw new IllegalArgumentException("Request too large");
    }
    // Allocation happens here...
}
```

If `numElements` and `elementSize` are large enough, `numElements * elementSize` can wrap around to a small or even negative value, bypassing the check.

## Lab Instructions

1.  **Examine the Vulnerability**: Open `VulnerableResourceAllocator.java` and notice how the limit check is implemented.
2.  **Compile the Lab**:
    ```bash
    javac *.java
    ```
3.  **Run the Exploit**:
    ```bash
    java OverflowExploit
    ```
    Observe how the vulnerable allocator fails to catch the overflow, while the secure one (once implemented) would.
4.  **Fixing the Vulnerability**: The secure check in `SecureResourceAllocator.java` checks for bounds before allocating.