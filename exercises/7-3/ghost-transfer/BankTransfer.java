/**
 * VULNERABLE CLASS: BankTransfer
 * Represents a sensitive monetary transfer.
 * VIOLATION: Non-final class with sensitive constructor susceptible to
 * Finalizer Attack.
 */
public class BankTransfer {
    private final String memo;
    private final int amount;

    public BankTransfer(String memo, int amount) {
        // SECURITY CHECK: Only authorized users can create transfers.
        if (!AuthService.isAuthorized()) {
            System.out.println("BankTransfer: AUTHORIZATION FAILED! Blocking construction...");
            // By throwing an exception here, developers think they've prevented the
            // object's use.
            throw new SecurityException("Unauthorized transfer creation");
        }

        this.memo = memo;
        this.amount = amount;
        System.out.println("BankTransfer: Construction successful.");
    }

    public void process() {
        System.out.println("BankTransfer: Processing $" + amount + " (" + memo + ")");
    }
}
