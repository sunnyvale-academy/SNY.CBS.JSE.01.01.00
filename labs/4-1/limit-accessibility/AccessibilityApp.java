/**
 * Driver app for Lab 4-1: Limit Accessibility.
 */
public class AccessibilityApp {
    public static void main(String[] args) {
        System.out.println("--- Lab 4-1: Limit Accessibility ---");

        BankPolicy bankPolicy = new BankPolicy();
        ProtectedService bankApp = new ProtectedService(bankPolicy);

        double myBalance = 600.0;
        double withdrawalAmount = 200.0;

        // 1. Initial State: Valid policy, withdrawal should fail (600 - 200 = 400 <
        // 500)
        System.out.println("\n[Scenario 1] Attempting withdrawal with default policy ($500 min)...");
        System.out.println("   Starting Balance: $" + myBalance);
        bankPolicy.showPolicy();
        bankApp.processWithdrawal(myBalance, withdrawalAmount);

        // 2. The Exploit Attempt: A component tries to bypass the policy using the
        // setter
        System.out.println("\n[Scenario 2] A component tries to bypass the policy using the setter...");

        // This will be BLOCKED by the setter logic in BankPolicy
        bankPolicy.setMinimumBalance(0.0);

        // 3. The Exploit: Bypassing the setter via direct field access
        System.out.println("\n[Scenario 3] An attacker bypasses the setter by modifying the PUBLIC field directly...");

        // This is possible because minimumBalance is public!
        bankPolicy.minimumBalance = 0.0;
        System.out.println("   !!! EXPLOIT SUCCESSFUL: Minimum balance changed to $0.0 bypassing the setter's logic!");

        // 4. Re-run: Withdrawal now succeeds
        System.out.println("\n[Scenario 4] Retrying withdrawal with compromised policy...");
        bankPolicy.showPolicy();
        boolean success = bankApp.processWithdrawal(myBalance, withdrawalAmount);

        if (success) {
            System.out.println(
                    "\n[RESULT] SECURITY BREACH: The withdrawal was approved because accessibility was not limited, allowing direct field tampering!");
        }
    }
}
