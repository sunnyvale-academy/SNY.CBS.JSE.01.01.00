package com.security.sensor;

/**
 * A controller that maintains temperature.
 */
public class TemperatureController {
    private final Sensor sensor;

    public TemperatureController(Sensor sensor) {
        this.sensor = sensor;
    }

    public void update() {
        // VULNERABILITY: Directly trusting the output from an untrusted object (the
        // sensor)
        double temp = sensor.getTemperatureCelsius();

        System.out.println("[CONTROLLER] Current reading: " + temp + "°C");

        if (temp > 100) {
            System.out.println("[CONTROLLER] CRITICAL: Overheating! Triggering Emergency Cooling.");
        } else if (temp < 0) {
            System.out.println("[CONTROLLER] WARNING: Freezing! Activating Heater.");
        } else {
            System.out.println("[CONTROLLER] Status: Temperature within normal operating range.");
        }

        // Potential for technical errors if temp is NaN or Infinity
        if (Double.isNaN(temp)) {
            System.out.println("[SYSTEM ERROR] Controller logic compromised by NaN value!");
        }
    }
}
