/**
 * A mutable class representing a salary record.
 */
public class SalaryRecord {
    private String employeeName;
    private double amount;

    public SalaryRecord(String employeeName, double amount) {
        this.employeeName = employeeName;
        this.amount = amount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%s: $%.2f", employeeName, amount);
    }
}
