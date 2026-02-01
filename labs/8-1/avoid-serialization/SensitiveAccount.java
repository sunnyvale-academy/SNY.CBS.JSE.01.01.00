import java.io.Serializable;

/**
 * VULNERABLE CLASS: SensitiveAccount
 * Represents a bank account with a private balance.
 * VIOLATION: Implements Serializable, allowing sensitive internal state
 * to be extracted and tampered with in a byte stream.
 */
public class SensitiveAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String accountId;
    private int balance; // Int for easier byte-stream tampering demo

    public SensitiveAccount(String accountId, int initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
        System.out.println("SensitiveAccount: Created account " + accountId + " with balance " + balance);
    }

    public String getAccountId() {
        return accountId;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "SensitiveAccount[id=" + accountId + ", balance=" + balance + "]";
    }
}
