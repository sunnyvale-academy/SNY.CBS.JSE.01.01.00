package com.security.sensor;

/**
 * Guideline 5-2 / INPUT-2: Validate output from untrusted objects as input
 * 
 * Interface for a temperature sensor.
 */
public interface Sensor {
    double getTemperatureCelsius();
}
