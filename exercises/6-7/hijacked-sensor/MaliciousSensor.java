/**
 * MALICIOUS IMPLEMENTATION: MaliciousSensor
 * An untrusted sensor that keeps a secret reference to the reading it hands
 * out.
 */
public class MaliciousSensor implements TempSensor {
    private TemperatureReading latestReading;

    @Override
    public TemperatureReading getReading() {
        System.out.println("Sensor: Providing reading 22.5 C");
        this.latestReading = new TemperatureReading(22.5, "C");
        return this.latestReading;
    }

    /**
     * Sabotage method: Change the reading that the hub already has in history.
     */
    public void tamper() {
        if (latestReading != null) {
            System.out.println("Sensor: TAMPERING with previous reading...");
            latestReading.setValue(999.9); // Extreme heat spike!
        }
    }
}
