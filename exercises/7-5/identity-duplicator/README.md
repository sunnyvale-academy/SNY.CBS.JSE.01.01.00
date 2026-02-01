# Exercise 7-5: The Identity Duplicator (OBJECT-5)

## The Scenario
The "IDenity" system uses a `UserProfile` class that performs detailed authentication and validation in its constructor. The developers assumed that because the login validation is hardcoded in the constructor, no unauthorized profiles could ever be created.

However, an attacker has discovered that they can create a subclass `ProfileCloner` that implements the `Cloneable` interface. By calling `clone()` on an existing admin profile, they can create a perfect copy of the identity **without ever triggering the constructor's validation logic**.

## Your Task
Secure the `UserProfile` class by following **Guideline 7-5 / OBJECT-5: Defend against cloning of non-final classes**.

1.  **Observe the Forgery**:
    Run `ProfileApp.java`. Notice how the "Admin" profile is duplicated and used without any "Validating login" message appearing for the second instance.

2.  **Fix the User Profile**:
    Modify `UserProfile.java` to prevent cloning.

    *Option A (Legacy/Common): Define a final `clone()` method that throws `CloneNotSupportedException`.*
    *Option B: If the class does not need to be subclassed, make the entire class `final`.*

## Instructions

1.  **Observe the Vulnerability**:
    ```bash
    javac *.java
    java ProfileApp
    ```
    Note that the forged profile manages to access the resource with administrative privileges.

2.  **Apply the Fix**:
    Update `UserProfile.java` to use the defensive pattern.

3.  **Verify**:
    Compile and run again. If you implemented the suggested fix, the attack should fail with an exception or a compilation error.
