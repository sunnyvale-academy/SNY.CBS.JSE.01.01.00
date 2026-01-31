# Lab 6-8: The Secure Inventory (MUTABLE-8)

## Guideline Reference
**Guideline 6-8 / MUTABLE-8**: Define wrapper methods around modifiable internal state.

## Overview
A class that manages a collection or other modifiable internal state should not expose its internal representation directly. If a class returns a reference to its internal mutable collection, external code can modify that collection without the class's knowledge or consent, bypassing any validation or business logic.

Guideline 6-8 recommends defining **wrapper methods** (like `addItem`, `removeItem`) that encapsulate the logic for modifying the state, and returning **unmodifiable views** or **defensive copies** for read access.

## Lab Materials
- `Item.java`: A simple data class representing inventory items.
- `InventoryManager.java`: A class that leaks its internal `List`.
- `InventoryExploit.java`: A utility that sabotages the leaked list.
- `InventoryLab.java`: PoC demonstrating the inventory corruption.

## Instructions

1.  **Observe the Vulnerability**:
    ```bash
    javac *.java
    java InventoryLab
    ```
    Note how the `InventoryManager` has its contents cleared and replaced with a malicious item by the exploit.

2.  **Analyze the Failure**:
    In `InventoryManager.java`, the `getItems` method provides direct access to the private field:
    ```java
    public List<Item> getItems() {
        return items; // LEAK!
    }
    ```

3.  **Inspect the Fix**:
    Check `SecureInventoryManager.java`. It uses `Collections.unmodifiableList` to prevent external modification:
    ```java
    public List<Item> getItems() {
        return Collections.unmodifiableList(items); // SECURE
    }
    ```

## Key Takeaway
**Never expose internal mutable collections directly.** Always provide specific mutator methods that enforce invariants and return unmodifiable views or copies for observation.
