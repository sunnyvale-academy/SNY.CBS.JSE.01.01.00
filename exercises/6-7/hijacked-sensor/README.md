# Exercise 6-7: The Hijacked Sensor (MUTABLE-7)

## The Scenario
The "SafeHome" system allows third-party smart sensors to be registered with a central hub. The `SmartHomeHub` collects `TemperatureReading` objects from these sensors and stores them in its history ledger for analysis.

However, the hub trusts the objects returned by the sensors. A malicious sensor can return a reading but keep a reference to it. Later, it can modify that object, changing the "historical" records stored inside the hub without the hub ever knowing.

## Your Task
Secure the `SmartHomeHub` by following **Guideline 6-7 / MUTABLE-7: Treat output from untrusted object as input**.

1.  **Analyze the Vulnerability**:
    Run `HubApp.java`. Why did the reading in the hub's history change from 22.5 to 999.9?

2.  **Fix the Hub**:
    Identify the point in `SmartHomeHub.java` where untrusted output is accepted. Fix it so that the sensor cannot modify its reading once it has been handed over to the hub.

    *Hint: Break the connection. If the returned object is mutable, you should not store it directly.*

## Instructions

1.  **Observe the Sabotage**:
    ```bash
    javac *.java
    java HubApp
    ```
    Note how the sensor modified its 22.5 C reading to 999.9 C *after* the hub had recorded it.

2.  **Apply the Fix**:
    Modify the `collectReading` method in `SmartHomeHub.java` to protect the hub's history.

3.  **Verify**:
    Compile and run again. The malicious sensor's `tamper()` call should no longer affect the hub's recorded readings.
