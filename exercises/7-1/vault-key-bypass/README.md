# Exercise 7-1: The Unauthorized Vault Key (OBJECT-1)

## The Scenario
The "SafeGuard" storage system uses a `VaultKey` class to represent authorization. Only high-ranking officials should possess a `MASTER` key, while regular employees are issued `GUEST` keys.

The developers implemented a strict distribution system, but they forgot one thing: the `VaultKey` constructor is `public`. This means anyone can skip the paperwork and just create a new `VaultKey("MASTER")` in their code.

## Your Task
Secure the `VaultKey` by following **Guideline 7-1 / OBJECT-1: Avoid exposing constructors of sensitive classes**.

1.  **Analyze the Vulnerability**:
    Run `VaultApp.java`. Observe how the `KeyExploit` successfully forges a `MASTER` key and unlocks the vault.

2.  **Fix the Vault Key**:
    Modify `VaultKey.java` to ensure that it cannot be instantiated directly from outside code.

    *Hint: Use a private constructor and provide a secure factory method. In a real-world scenario, this factory method would require an administrative password or a cryptographic token.*

## Instructions

1.  **Observe the Forge**:
    ```bash
    javac *.java
    java VaultApp
    ```
    Note how the "VAULT STATUS" becomes "UNLOCKED" even though no authorized key distribution took place.

2.  **Apply the Fix**:
    Update `VaultKey.java` to hide its constructor.

3.  **Verify**:
    Try to compile the project again. The `KeyExploit.java` should now fail to compile with an error like:
    `VaultKey(String) has private access in VaultKey`.
