# Lab 4-2: Use modules to hide internal packages

This lab demonstrates **Guideline 4-2: Use modules to hide internal packages**.

In traditional Java development (Classpath), all public classes in all packages are visible to any other code on the classpath. This makes it difficult to encapsulate implementation details and internal APIs.

The Java Module System (JPMS) introduced in Java 9 allows you to explicitly define which packages are "exported" (publicly visible) and which remain "concealed" (internal to the module).

## Scenario

You have a security library `com.example.crypto` that provides a `VaultService`.
Internally, it uses a `LegacyKeyManager` located in `com.example.crypto.internal`.
This internal key manager contains sensitive methods that should **never** be called by library consumers.

## Directory Structure

- `vulnerable-lib/`: Library code without module descriptors (traditional JAR).
- `secure-lib/`: Library code with `module-info.java` defining encapsulation.
- `app/`: A consumer application that attempts to access the internal `LegacyKeyManager`.

---

## Part 1: The Vulnerability (Classpath)

When running on the classpath, the `app` can easily access the `internal` package of the library.

### 1. Compile the library and the app
```bash
# Clean previous builds
find . -name "*.class" -delete

# Compile vulnerable library (classes go into the same folders)
javac $(find vulnerable-lib -name "*.java")

# Compile app against the library (using classpath)
# Note: we explicitly compile ExploitApp.java to avoid issues with module-info.java on the classpath
javac -cp vulnerable-lib app/com/example/app/ExploitApp.java
```

### 2. Run the exploit
```bash
java -cp vulnerable-lib:app com.example.app.ExploitApp
```

**Observation**: You should see that the application successfully calls `LegacyKeyManager.getMasterKey()` and leaks the secret.

---

## Part 2: The Mitigation (Module Path)

Now we will use the Java Module System to encapsulate the internal package.

### 1. Compile the secure library
```bash
# Clean previous builds
find . -name "*.class" -delete

# Compile secure library (including module-info.java)
javac $(find secure-lib -name "*.java")
```

### 2. Attempt to compile the app against the secure module
```bash
# Attempt to compile the exploit app using the module path
javac --module-path secure-lib app/com/example/app/ExploitApp.java app/module-info.java
```

**Observation**: The compilation **fails**.
The compiler prevents `ExploitApp` from accessing `com.example.crypto.internal` because it is not exported by the `com.example.crypto` module.

```text
error: package com.example.crypto.internal is not visible
(package com.example.crypto.internal is declared in module com.example.crypto, but module com.example.crypto does not export it)
```

---

## Conclusion

By using Java Modules:
1. You explicitly define the public API surface.
2. Internal implementation details (like `LegacyKeyManager`) are strongly encapsulated.
3. Attempting to use internal APIs results in a compile-time error (or a runtime `IllegalAccessError` if attempted via reflection).
