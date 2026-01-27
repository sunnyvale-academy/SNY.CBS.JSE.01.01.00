package com.security.sensor;

/**
 * Lab Runner for Guideline 5-2.
 */
public class LabRunner {
    public static void main(String[] args) {
        System.out.println("--- Scenario: Using a Malicious Sensor ---");
        Sensor rogueSensor = new MaliciousSensor();
        TemperatureController controller = new TemperatureController(rogueSensor);

        System.out.println("\n[RUN] Reading 1 (NaN):");
        controller.update();

        System.out.println("\n[RUN] Reading 2 (Infinity):");
        controller.update();

        System.out.println("\n[RUN] Reading 3 (Extreme Value):");
        controller.update();
    }
}
