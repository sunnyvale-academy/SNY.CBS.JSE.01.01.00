# Exercise 6-1: The GPS Tamperer (MUTABLE-1)

## Guideline Reference
**Guideline 6-1 / MUTABLE-1**: Prefer immutability for value types.

## The Situation
You are developing a secure GPS tracking system. The system uses a `Location` class to represent coordinates and a `GeofenceService` to restrict access to sensitive resources based on the user's location.

However, the current implementation of the `Location` class is mutable. This allows a malicious user or a compromised component to change the coordinates after they have been validated by the `GeofenceService`.

## Your Task
1.  **Analyze the vulnerability**: Look at `Location.java` and `GeofenceService.java`. See how the `TrackerApp.java` demonstrates the tampering.
2.  **Fix the vulnerability**: Modify `Location.java` to make it **immutable**.
    *   Make the fields `private` and `final`.
    *   Remove any setters (if any were added, though currently they are just public fields).
    *   Ensure the class itself is `final` to prevent subclassing.
3.  **Verify the fix**: Try to re-compile `TrackerApp.java`. It should now fail to compile because it tries to modify the `latitude` and `longitude` fields, which are now final/private. This confirms that the tampering is no longer possible.

## Instructions

1.  **Compile and run the vulnerable version**:
    ```bash
    javac *.java
    java TrackerApp
    ```
    Observe how the user managed to "access secure resource" even after changing their coordinates to (90, 0).

2.  **Make Location immutable**:
    Update `Location.java` to be a proper immutable value type.

3.  **Compile again**:
    ```bash
    javac *.java
    ```
    The compilation should fail on `TrackerApp.java` at the lines where it attempts to modify the location. This is the desired outcome! It shows that the API now enforces immutability.

4.  **Fix TrackerApp**:
    To make `TrackerApp` work again (honestly), you would need to create a *new* `Location` object instead of modifying the existing one.
