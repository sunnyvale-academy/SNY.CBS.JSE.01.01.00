# Exercise 6-4: The Config Experiment (MUTABLE-4)

## The Scenario
"CloudScale Solutions" uses a central `SystemConfig` class to manage its production clusters. A developer, trying to safely test a new "EXTREME" performance mode, attempted to clone the production configuration for a temporary experiment.

Because `SystemConfig` did not provide an explicit way to be copied, the developer made a mistake that caused the experimental settings to leak back into the live production environment.

## Your Task
Modify `SystemConfig.java` to support safe copying by following **Guideline 6-4 / MUTABLE-4: Support copy functionality for a mutable class**.

1.  **Analyze the Failure**: 
    Look at `ConfigExperiment.java`. Why did the "experiment" affect the "production" configuration?

2.  **Add Copy Support**:
    Implement a **Copy Constructor** in `SystemConfig.java`. It should take another `SystemConfig` instance and perform a deep copy of its internal `settings` map.

3.  **Update the Experiment**:
    Once the copy constructor is implemented, update `ConfigExperiment.java` to use it:
    ```java
    // SystemConfig experimentalConfig = productionConfig; // OLD WRONG WAY
    SystemConfig experimentalConfig = new SystemConfig(productionConfig); // NEW SECURE WAY
    ```

## Instructions

1.  **Observe the Vulnerability**:
    ```bash
    javac *.java
    java ConfigExperiment
    ```
    Note the "CRITICAL ALERT" in the output.

2.  **Fix SystemConfig.java**:
    Add a copy constructor: `public SystemConfig(SystemConfig other) { ... }`.

3.  **Update ConfigExperiment.java**:
    Change the line where `experimentalConfig` is initialized to use your new constructor.

4.  **Verify**:
    Compile and run again. The production settings should remain "standard" even after the experiment modifications.
