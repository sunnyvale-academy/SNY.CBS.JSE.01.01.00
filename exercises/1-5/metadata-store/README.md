# Exercise 1-5: Metadata Store Protection

## Goal
Secure a metadata storage service against Hash Collision Denial of Service (DoS) and general resource exhaustion.

## Vulnerability
The `VulnerableMetadataStore` class allows users to store arbitrary key-value pairs in a `HashMap`. An attacker can exploit this by:
1.  **Hash Collision**: Providing many keys that collide, causing CPU exhaustion.
2.  **Resource Exhaustion**: Providing an unlimited number of keys, causing memory exhaustion.

## Tasks
1.  **Refactor the Store**: Modify `VulnerableMetadataStore.java` (or create `SecureMetadataStore.java`) to:
    -   Validate the length and format of keys.
    -   Impose a maximum limit on the number of metadata entries per user/object.
    -   Ensure that the application remains responsive even under heavy load.
2.  **Implementation Tips**:
    -   Use a hard limit on the map size.
    -   Consider using a more robust keying strategy if the keys must be user-provided.
3.  **Verify**: Use `CollisionTest.java` to test both the vulnerable and your secure implementation.

## Running the Exercise
1.  **Compile**:
    ```bash
    javac *.java
    ```
2.  **Run (Vulnerable)**:
    ```bash
    java CollisionTest
    ```
    Observe how the vulnerable store accepts an unlimited number of entries and might slow down with many collisions.
