package labs.api_design;

import java.util.HashMap;
import java.util.Map;

/**
 * VULNERABILITY: Positional Parameter Confusion.
 * 
 * The transfer method takes two integer IDs and an amount.
 * It is extremely easy for a developer to swap 'fromId' and 'toId',
 * especially if both are integers and the IDE doesn't provide clear hints.
 */
public class VulnerableBankService {
    private Map<Integer, Double> accounts = new HashMap<>();

    public VulnerableBankService() {
        accounts.put(101, 1000.0); // Alice
        accounts.put(202, 500.0); // Bob
    }

    /**
     * Transfers money between accounts.
     * 
     * @param fromId Account to withdraw from
     * @param toId   Account to deposit to
     * @param amount Amount to transfer
     */
    public void transfer(int fromId, int toId, double amount) {
        if (accounts.get(fromId) >= amount) {
            accounts.put(fromId, accounts.get(fromId) - amount);
            accounts.put(toId, accounts.get(toId) + amount);
            System.out.println("Transferred $" + amount + " from " + fromId + " to " + toId);
        } else {
            System.out.println("Insufficient funds in " + fromId);
        }
    }

    public double getBalance(int id) {
        return accounts.getOrDefault(id, 0.0);
    }
}
