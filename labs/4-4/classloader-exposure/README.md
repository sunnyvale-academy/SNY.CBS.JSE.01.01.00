# Lab 4-4: Limit exposure of ClassLoader instances

This lab demonstrates **Guideline 4-4: Limit exposure of ClassLoader instances**.

Exposing `ClassLoader` instances to untrusted code is dangerous because class loaders can be used to:
1.  Load and access classes that should be internal or restricted.
2.  Gain information from resource URLs.
3.  Manipulate the assertion status of classes.
4.  Subclass and invoke non-standard methods if the loader is a custom implementation.

## Scenario
A `PluginManager` manages internal and external components. It inadvertently provides a `getLoader()` method that returns its internal `ClassLoader`. A "malicious" application uses this loader to reach into the `com.plugin.internal` package and leak sensitive configuration data.

## Instructions

### 1. Compile the lab
```bash
# Assumptions: You are in the lab directory
javac $(find . -name "*.java")
```

### 2. Run the application
```bash
java com.app.MaliciousApp
```

**Observation**: Notice how the `MaliciousApp` is able to obtain the ClassLoader and use reflection to call methods on the `ProtectedResource` class, which is not intended for public access.

## Secure Coding Fix

To fix this vulnerability, we must follow Guideline 4-4 and **not expose ClassLoader instances**.

1.  Open `com/plugin/PluginManager.java`.
2.  Remove the `getLoader()` method.
3.  Attempt to recompile the `MaliciousApp.java`. It will now fail because the method is gone.

In a real-world scenario where you *must* provide class loading capabilities, use a more restricted API or verify the caller's permissions before returning a loader.
