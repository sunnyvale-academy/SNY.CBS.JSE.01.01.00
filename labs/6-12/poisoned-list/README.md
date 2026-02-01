# Lab 6-12: The Poisoned Access List (MUTABLE-12)

## Guideline Reference
**Guideline 6-12 / MUTABLE-12**: Do not expose modifiable collections.

## Overview
Collections like `List`, `Set`, and `Map` are mutable objects. If a collection is exposed as a `public static` field, any part of the program can add, remove, or clear its elements. Even if the field is marked `final`, this only prevents the variable from being reassigned to a *new* collection; it does nothing to protect the *contents* of the existing collection.

In security-critical contexts, such as a whitelist for administrators, this allows an attacker to "poison" the list by injecting their own unauthorized entries.

## Lab Materials
- `AccessList.java`: Contains a modifiable public static List.
- `ListExploit.java`: Maliciously adds a user to the list.
- `CollectionLab.java`: PoC demonstrating the whitelist poisoning.

## Instructions

1.  **Observe the Poisoning**:
    ```bash
    javac *.java
    java CollectionLab
    ```
    Notice how the `hacker` user is successfully added to the `ADMINS` list.

2.  **Analyze the Failure**:
    In `AccessList.java`, the `ADMINS` list is `public static final`. While `final` prevents reassignment, the `ArrayList` object itself allows calls to `.add()`.

3.  **Inspect the Fix**:
    Check `SecureAccessList.java`. It makes the list `private` and provides access via a method that returns an unmodifiable view (`Collections.unmodifiableList()`). Alternatively, using `List.of()` (for static fixed lists) also provides immutability.

## Key Takeaway
**Never expose a modifiable collection directly.** Always use encapsulation (private fields) and return unmodifiable views or immutable copies to prevent unauthorized modification.
