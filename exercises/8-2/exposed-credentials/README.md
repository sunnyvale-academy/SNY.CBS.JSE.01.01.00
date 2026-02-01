# Exercise 8-2: The Exposed Credentials (SERIAL-2)

## The Scenario
Your application's `UserCredentials` class is used to store login information across different modules. Because the application uses a distributed session manager, the class was made `Serializable`. 

To protect the user's password, the field was made `private` and no getter was provided. Unfortunately, this isn't enough to prevent the password from being leaked whenever the object is serialized for the session manager.

## Your Task
Fix the `UserCredentials` class to prevent the password from being leaked during serialization, following **Guideline 8-2 / SERIAL-2: Guard sensitive data during serialization**.

1.  **Run the Leak Demo**:
    ```bash
    javac *.java
    java CredentialApp
    ```
    Confirm that the "private" password is found by the sniffer in the serialized byte stream.

2.  **Apply the Fix**:
    Modify `UserCredentials.java` to ensure the password field is excluded from serialization.

3.  **Verify the Fix**:
    Compile and run the app again. The "Found password" result should be "Not found".

## Instructions
*   Use the `transient` keyword on the sensitive field. peace
