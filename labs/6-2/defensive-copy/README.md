# Java Secure Coding: Guideline 6-2 - Defensive Copying

## The Guideline (6-2 / MUTABLE-2)

The Java SE Security Coding Guidelines state: **"Create copies of mutable output values."**

If a method returns a reference to an internal mutable object (like an `ArrayList`, a `Date`, or a custom mutable class), the caller can modify that object's state directly, effectively tampering with the internal state of the providing class. To prevent this, always return a defensive copy or an unmodifiable view.

## The Vulnerability (VulnerableUserRegistry.java)

In `VulnerableUserRegistry.java`, the `getUsers()` method returns the raw reference to the internal `users` list.

```java
public List<String> getUsers() {
    // VULNERABILITY: Returning the actual internal reference
    return users;
}
```

This breaks encapsulation and allows any caller to add, remove, or clear users from the registry without authorization.

## The Exploit (MutabilityExploit.java)

The `MutabilityExploit.java` demonstrates how an attacker can escalate their privileges by adding themselves to an "authorized users" list:

```java
List<String> userList = registry.getUsers();
userList.add("attacker_user"); // Tampering with internal state!
```

## The Secure Solution (SecureUserRegistry.java)

The solution is to ensure that the returned object cannot be used to modify the internal state. This is typically done by creating a **defensive copy** and/or wrapping it in an **unmodifiable view**.

```java
public List<String> getUsers() {
    // SECURE: Return an unmodifiable view of a defensive copy
    return Collections.unmodifiableList(new ArrayList<>(users));
}
```

## How to Run the Lab

### 1. Compile
```bash
javac *.java
```

### 2. Run the Exploit
```bash
java MutabilityExploit
```

Observe how the `Vulnerable Service` allows the caller to modify the internal list, while the `Secure Service` prevents modification by throwing an `UnsupportedOperationException`.
