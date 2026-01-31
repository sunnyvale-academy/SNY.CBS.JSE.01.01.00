/**
 * TemperatureReading class.
 * Represents a temperature reading.
 * This class is mutable (it has setter methods).
 */
public class TemperatureReading {
    private double value;
    private String unit;

    public TemperatureReading(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return String.format("%.1f %s", value, unit);
    }
}
