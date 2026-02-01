# Exercise 7-4: The Broken Logger (OBJECT-4)

## The Scenario
The "LogCenter" library provides a base `Logger` class that handles generic setup. To allow users to customize their loggers, the base constructor calls an `initialize()` method that subclasses can override.

However, a developer noticed that when they create a `CustomLogger("INFO")`, the `initialize()` method prints that the log level is `null`. Even though they set the field in their constructor and marked it as `final`, the base class seems to ignore it!

## Your Task
Fix the `Logger` base class by following **Guideline 7-4 / OBJECT-4: Prevent constructors from calling methods that can be overridden**.

1.  **Analyze the Failure**:
    Run `LoggingApp.java`. Observe the order of execution in the console and how the `CustomLogger` sees its own `logLevel` as `null` during the "Base class construction" phase.

2.  **Fix the Logger**:
    Modify `Logger.java` to ensure that it doesn't call an overridable method.

    *Hint: You can make the method `private` or `final`. If subclasses need to perform their own initialization, they should do it in their own constructors after the `super()` call has finished.*

## Instructions

1.  **Observe the Broken State**:
    ```bash
    javac *.java
    java LoggingApp
    ```
    Analyze the output. Why is `logLevel` null inside `initialize()`?

2.  **Apply the Fix**:
    Update `Logger.java` to prevent the "Constructor Trap".

3.  **Verify**:
    If you made `initialize()` private in the base class, the `CustomLogger` will no longer be overriding it. You will need to move the subclass setup logic directly into the `CustomLogger` constructor. Compile and run again to ensure everything works as intended.
