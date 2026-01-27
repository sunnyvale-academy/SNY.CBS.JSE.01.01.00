# Lab 5-2: Validate Output from Untrusted Objects

## Guideline Reference
**Guideline 5-2 / INPUT-2: Validate output from untrusted objects as input**

## Vulnerability Explanation
Data received from an untrusted object must be validated as if it were untrusted input. An "untrusted object" might be:
- An implementation of an interface provided by an untrusted party (e.g., a plugin or a callback).
- An object that has been serialized and deserialized from an untrusted source.

In this lab, the `TemperatureController` relies on a `Sensor` interface. It incorrectly assumes that any implementation of `Sensor` will return valid, sane numeric values. However, an attacker-controlled `MaliciousSensor` can return `NaN`, `Infinity`, or extreme values that disrupt the controller's logic or cause system-level errors.

## Instructions
1.  **Compile the code**:
    ```bash
    javac com/security/sensor/*.java
    ```

2.  **Run the lab**:
    ```bash
    java com.security.sensor.LabRunner
    ```
    Observe how the `NaN` and `Infinity` values affect the controller's output and logic.

3.  **Fix the vulnerability**:
    Update `TemperatureController.java` to:
    -   Validate the output of `sensor.getTemperatureCelsius()`.
    -   Use `Double.isFinite(temp)` to ensure the value is neither `NaN` nor `Infinity`.
    -   Apply range checks to ensure the temperature is within physically possible limits (e.g., -50 to 150°C).

4.  **Verify the fix**:
    Recompile and run. The controller should now reject or safely handle the malicious readings instead of blindly processing them.

## Key Takeaways
- Output from an external object is just another form of input.
- Always validate the return values of methods called on untrusted implementation of interfaces.
- Use sanity checks like `isFinite()` when dealing with floating-point numbers from untrusted sources.
