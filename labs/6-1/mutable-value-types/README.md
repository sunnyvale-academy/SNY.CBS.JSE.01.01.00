# Lab 6-1: Prefer Immutability for Value Types (MUTABLE-1)

## Guideline Reference
**Guideline 6-1 / MUTABLE-1**: Prefer immutability for value types.

## Explanation
Value types (like dates, colors, coordinates) should ideally be immutable. If a value type is mutable, it can lead to several security and consistency issues:

1.  **TOCTOU (Time-of-Check to Time-of-Use)**: An object might pass a security check, but its internal state could be changed before it is actually used.
2.  **Internal Invariants Violation**: Even if a constructor validates inputs, a caller holding a reference to a mutable internal component can change it later, potentially violating object invariants (e.g., `start` being after `end`).
3.  **Unintended Side Effects**: Sharing a mutable object across different parts of an application can lead to unexpected changes if one part modifies it.

In Java, `java.util.Date` is a classic example of a mutable class that is often used as a value type. The modern `java.time` API (e.g., `Instant`, `LocalDate`, `ZonedDateTime`) provides immutable alternatives.

## The Vulnerability
In this lab, `MutablePeriod.java` uses `java.util.Date`. Even though the fields are marked `final`, the `Date` objects themselves are mutable. A malicious user can modify the contents of the `Date` objects after they have been passed to the constructor.

## Instructions

1.  **Examine the code**:
    *   `MutablePeriod.java`: Notice how it uses `java.util.Date`.
    *   `AccessController.java`: A service that checks if a period is currently valid.
    *   `MaliciousUser.java`: A PoC showing how to exploit the mutability.

2.  **Compile the lab**:
    ```bash
    javac *.java
    ```

3.  **Run the PoC**:
    ```bash
    java MaliciousUser
    ```

4.  **Observe the output**:
    *   Scenario 1 shows how the internal state is modified.
    *   Scenario 2 shows how a period can be extended after validation.
    *   Scenario 3 shows how object invariants can be broken.

5.  **Compare with the secure version**:
    *   Look at `SecurePeriod.java`. It uses `java.time.Instant`, which is immutable.
    *   Try to modify `SecurePeriod` in a similar way (you'll find that `Instant` has no setter-like methods that modify the instance; it returns new instances instead).

## Secure Alternative
Always prefer immutable classes for value types. If you must use a mutable class (like `Date`) for legacy reasons, perform **defensive copying** in both the constructor and the accessor methods.

```java
// Defensive copying in constructor
public MutablePeriod(Date start, Date end) {
    this.start = new Date(start.getTime());
    this.end = new Date(end.getTime());
    if (this.start.compareTo(this.end) > 0) {
        throw new IllegalArgumentException();
    }
}

// Defensive copying in accessor
public Date start() {
    return new Date(start.getTime());
}
```
Better yet, use the `java.time` API.
