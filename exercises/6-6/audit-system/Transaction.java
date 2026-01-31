/**
 * Transaction class.
 * Represents a financial transaction.
 * Note: This class is mutable (it has setter methods).
 */
public class Transaction {
    private double amount;
    private String description;

    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s: $%.2f", description, amount);
    }
}
