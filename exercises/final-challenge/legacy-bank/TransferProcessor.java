/**
 * VULNERABLE CLASS: TransferProcessor
 * Guidelines:
 * - INPUT-1 (5-1): Missing input validation on transfer amount.
 */
public class TransferProcessor {
    public void transfer(UserAccount from, UserAccount to, long amount) {
        // Vulnerability: No check for negative amount.
        // A user could transfer -100 to someone else to increase their own balance.
        from.balance -= amount;
        to.balance += amount;
        System.out.println("Transferred " + amount + " from " + from.username + " to " + to.username);
    }
}
