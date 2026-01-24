/**
 * Lab 4-1: Secure Bank Policy.
 * This class uses encapsulation to protect its internal state.
 */
public class BankPolicy {
    // VULNERABILITY: Field is public! This allows direct modification bypassing the
    // setter.
    public double minimumBalance = 500.0;

    // SECURE: Setter method enforces business invariants.
    public void setMinimumBalance(double balance) {
        if (balance < 500.0) {
            System.out
                    .println("   !!! REJECTED: Security policy violation. Minimum balance cannot be set below $500.0.");
            return;
        }
        this.minimumBalance = balance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void showPolicy() {
        System.out.println("[Policy] Current Minimum Balance required: $" + minimumBalance);
    }
}
