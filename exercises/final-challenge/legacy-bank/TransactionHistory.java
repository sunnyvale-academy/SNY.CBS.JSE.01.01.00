import java.util.List;
import java.util.ArrayList;

/**
 * VULNERABLE CLASS: TransactionHistory
 * Guidelines:
 * - MUTABLE-3 (6-3): Fails to perform defensive copying of input list.
 */
public class TransactionHistory {
    private List<String> transactions;

    public TransactionHistory(List<String> transactions) {
        // Vulnerability: Storing direct reference to mutable list
        this.transactions = transactions;
    }

    public void showHistory() {
        System.out.println("History: " + transactions);
    }
}
