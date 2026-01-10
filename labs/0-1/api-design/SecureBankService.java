import java.util.HashMap;
import java.util.Map;

/**
 * SECURE: Fluent API to prevent parameter confusion.
 * 
 * By using a builder-like pattern or fluent interface, the intent of each
 * parameter is explicitly stated in the code, making errors obvious.
 */
public class SecureBankService {
    private Map<Integer, Double> accounts = new HashMap<>();

    public SecureBankService() {
        accounts.put(101, 1000.0);
        accounts.put(202, 500.0);
    }

    public TransferInitiator from(int fromId) {
        return new TransferInitiator(fromId);
    }

    public class TransferInitiator {
        private final int fromId;

        private TransferInitiator(int fromId) {
            this.fromId = fromId;
        }

        public TransferFinalizer to(int toId) {
            return new TransferFinalizer(fromId, toId);
        }
    }

    public class TransferFinalizer {
        private final int fromId;
        private final int toId;

        private TransferFinalizer(int fromId, int toId) {
            this.fromId = fromId;
            this.toId = toId;
        }

        public void amount(double amount) {
            if (accounts.get(fromId) >= amount) {
                accounts.put(fromId, accounts.get(fromId) - amount);
                accounts.put(toId, accounts.get(toId) + amount);
                System.out.println("Successfully transferred $" + amount + " from " + fromId + " to " + toId);
            } else {
                throw new IllegalArgumentException("Insufficient funds in " + fromId);
            }
        }
    }

    public double getBalance(int id) {
        return accounts.getOrDefault(id, 0.0);
    }
}
