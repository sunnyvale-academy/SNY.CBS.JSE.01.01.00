/**
 * Lab 4-1: A service that performs security-sensitive operations.
 * It depends on a BankPolicy.
 */
public class ProtectedService {
    private final BankPolicy policy;

    public ProtectedService(BankPolicy policy) {
        this.policy = policy;
    }

    public boolean processWithdrawal(double currentBalance, double amount) {
        System.out.println("[Bank] Processing withdrawal request for $" + amount);

        double resultingBalance = currentBalance - amount;

        // Security Check: Ensure balance doesn't drop below the minimum
        // Uses the getter to access the protected state.
        if (resultingBalance < policy.getMinimumBalance()) {
            System.out.println("   !!! REJECTED: Resulting balance ($" + resultingBalance
                    + ") would be below the required minimum ($" + policy.getMinimumBalance() + ").");
            return false;
        }

        System.out.println("   -> APPROVED: New balance will be $" + resultingBalance);
        return true;
    }
}
