# Java Deserialization Vulnerability: Guideline 8-3 / SERIAL-3

## The Vulnerability (VulnerableUser.java)

Java's deserialization mechanism can be a source of significant security vulnerabilities if not handled carefully. Guideline 8-3 / SERIAL-3 from the Java Secure Coding Guidelines states: "View deserialization the same as object construction." This means that any validation or security checks performed during object construction (e.g., in constructors) must also be applied during deserialization.

In `VulnerableUser.java`, we have a class representing a user with a `username` and an `isAdmin` flag. The constructor correctly enforces a rule: only a user with the username "admin" can have `isAdmin` set to `true`.

```java
public VulnerableUser(String username, boolean isAdmin) {
    if (isAdmin && !"admin".equals(username)) {
        throw new IllegalArgumentException("Only 'admin' user can be an administrator.");
    }
    this.username = username;
    this.isAdmin = isAdmin;
}
```

The vulnerability arises because the `readObject` method, which is implicitly called during deserialization, does not re-apply this validation. An attacker can craft a serialized `VulnerableUser` object where `isAdmin` is `true` for a `username` that is not "admin". When this malicious object is deserialized, the `readObject` method simply restores the fields without invoking the constructor's validation logic, thus creating an unauthorized administrator.

```java
private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject(); // Deserializes fields directly, bypassing constructor validation
    // NO VALIDATION HERE
}
```

## The Exploit (Exploit.java)

The `Exploit.java` demonstrates how an attacker can leverage this vulnerability. It first creates a legitimate `VulnerableUser` object. Then, it simulates an attacker crafting a byte stream that represents a `VulnerableUser` object with `username = "attacker"` and `isAdmin = true`. When this crafted byte stream is deserialized, the `readObject` method in `VulnerableUser` is called, which does not validate the `isAdmin` flag against the `username`. As a result, the `deserializedUser` object will have `isAdmin = true` even though its `username` is "attacker", effectively elevating privileges.

## The Secure Solution (SecureUser.java)

To mitigate this vulnerability, the `readObject` method must perform the same validation checks as the constructor. In `SecureUser.java`, a `validateUser` helper method is introduced and called by both the constructor and the `readObject` method.

```java
public SecureUser(String username, boolean isAdmin) {
    validateUser(username, isAdmin);
    this.username = username;
    this.isAdmin = isAdmin;
}

private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject(); // Deserialize fields
    // Re-apply the same validation as the constructor
    validateUser(this.username, this.isAdmin);
}

private void validateUser(String username, boolean isAdmin) {
    if (isAdmin && !"admin".equals(username)) {
        throw new IllegalArgumentException("Only 'admin' user can be an administrator.");
    }
}
```

By ensuring that the `readObject` method explicitly calls the validation logic, any attempt to deserialize a malicious object that violates the class's invariants will be caught, preventing unauthorized privilege escalation. This adheres to Guideline 8-3 / SERIAL-3 by treating deserialization as equivalent to object construction in terms of security validation.

