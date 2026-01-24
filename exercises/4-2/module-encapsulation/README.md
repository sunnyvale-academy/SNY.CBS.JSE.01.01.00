# Exercise 4-2: Hide Internal Packages with Modules

## Guideline 4-2: Use modules to hide internal packages

In this exercise, you will secure a banking library by using the Java Module System (JPMS) to encapsulate internal implementation details.

### The Situation
The `com.bank.core` library contains a `BankingService` for public use. However, it also has a `com.bank.core.internal` package with a `SecurityUtils` class that holds a sensitive `MASTER_KEY`. 

On the traditional classpath, an application can easily access this internal class and leak the key.

### Your Task
1.  **Observe the Leak**: Compile and run the application using the classpath to see the vulnerability.
2.  **Modularize the Library**: Create a `module-info.java` for the library that only exports the public package (`com.bank.core`).
3.  **Encapsulate the App**: Create a `module-info.java` for the application that requires the library.
4.  **Verify the Protection**: Attempt to compile using the module path. The compilation should fail because the internal package is no longer visible.

---

### Step 1: Observe the Leak (Classpath)

Run the following commands to compile and run using the traditional classpath:

```bash
# Clean up
find . -name "*.class" -delete

# Compile the library
javac $(find lib -name "*.java")

# Compile the app (accessing internal classes is allowed on classpath)
javac -cp lib app/com/bank/app/BankApp.java

# Run the app
java -cp lib:app com.bank.app.BankApp
```

### Step 2: Modularize the Library

Create a file named `lib/module-info.java` with the following content:

```java
module com.bank.core {
    // TODO: Export ONLY the public package
}
```

### Step 3: Modularize the Application

Create a file named `app/module-info.java` with the following content:

```java
module com.bank.app {
    // TODO: Require the banking core module
}
```

### Step 4: Verify the Protection (Module Path)

Try to compile the application using the module path:

```bash
# Clean up
find . -name "*.class" -delete

# Compile the library as a module
javac $(find lib -name "*.java")

# Attempt to compile the app as a module
javac --module-path lib app/com/bank/app/BankApp.java app/module-info.java
```

If you have correctly modularized the library, the second `javac` command should fail with a "package is not visible" error.
