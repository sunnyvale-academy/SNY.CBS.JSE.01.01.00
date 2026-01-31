/**
 * VULNERABLE CLASS: Vault
 * Guidelines:
 * - DOS-3 (1-3): Integer overflow in balance calculation.
 */
public class Vault {
    private long totalBalance = 0;

    public void deposit(long amount) {
        // Vulnerability: No check for overflow.
        // A very large deposit could cause totalBalance to wrap around.
        totalBalance += amount;
        System.out.println("Vault updated. Total: " + totalBalance);
    }

    public long getTotalBalance() {
        return totalBalance;
    }
}
