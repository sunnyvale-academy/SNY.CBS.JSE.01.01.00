import java.util.ArrayList;
import java.util.List;

/**
 * VULNERABLE CLASS: SmartHomeHub
 * Collects and stores temperature readings from various sensors.
 * VIOLATION: Trusts the mutable objects returned by untrusted sensors.
 */
public class SmartHomeHub {
    private final List<TemperatureReading> history = new ArrayList<>();

    /**
     * Collects a reading from the sensor.
     * VULNERABILITY: Does not perform a defensive copy of the output from an
     * untrusted sensor.
     */
    public void collectReading(TempSensor sensor) {
        System.out.println("Hub: Collecting reading from sensor...");

        // VULNERABILITY: Guideline 6-7 / MUTABLE-7 Violation.
        // We are treating the output of an untrusted object as trusted input.
        TemperatureReading reading = sensor.getReading();
        history.add(reading);
    }

    public void printHistory() {
        System.out.println("--- Reading History ---");
        for (TemperatureReading r : history) {
            System.out.println(r);
        }
    }
}
