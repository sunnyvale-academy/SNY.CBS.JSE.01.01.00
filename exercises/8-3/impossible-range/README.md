# Exercise 8-3: The Impossible Range (SERIAL-3)

## The Scenario
The `DateRange` class is a core business object that represents a time period. It has a strict invariant: the `start` timestamp must always be less than or equal to the `end` timestamp. This is enforced in the constructor.

However, the class is `Serializable`. An attacker has discovered that they can bypass the constructor logic by creating a custom serialized byte stream. When the system deserializes this stream, it creates a `DateRange` object where `start > end`, violating all business assumptions and potentially causing crashes or logic errors elsewhere.

## Your Task
Fix the `DateRange` class by following **Guideline 8-3 / SERIAL-3: View deserialization the same as object construction**.

1.  **Observe the Bypass**:
    ```bash
    javac *.java
    java RangeApp
    ```
    Notice how an object is created with `start=500` and `end=200`. The constructor validation was skipped entirely.

2.  **Fix the Class**:
    Modify `DateRange.java`. You need to ensure that invariants are re-checked during deserialization.

    *Hint: Implement the `private void readObject(ObjectInputStream in)` method. Don't forget to call `in.defaultReadObject()` first.*

## Instructions

1.  **Run the Vulnerable App**:
    Confirm the "Impossible Range" is created.

2.  **Implement readObject**:
    Add validation logic to `DateRange.java`. If the state is invalid after `defaultReadObject()`, throw a `java.io.InvalidObjectException`.

3.  **Verify**:
    Compile and run `RangeApp` again. It should now fail with a `Check: Deserialization failed...` message. peace
