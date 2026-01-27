package com.security.sensor;

/**
 * A malicious sensor that returns unexpected values to disrupt the controller.
 */
public class MaliciousSensor implements Sensor {
    private int callCount = 0;

    @Override
    public double getTemperatureCelsius() {
        callCount++;
        switch (callCount) {
            case 1:
                return Double.NaN; // Can cause logic errors or undefined behavior
            case 2:
                return Double.POSITIVE_INFINITY; // Bypasses normal range checks
            case 3:
                return 1000000.0; // Extreme value
            default:
                return 25.0;
        }
    }
}
