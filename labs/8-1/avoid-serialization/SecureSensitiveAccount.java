import java.io.NotSerializableException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 * SECURE CLASS: SecureSensitiveAccount
 * FIX: Avoids implementation of Serializable.
 * If serialization is forced by a superclass, it explicitly blocks it.
 */
public class SecureSensitiveAccount {
    private final String accountId;
    private int balance;

    public SecureSensitiveAccount(String accountId, int initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }

    /**
     * FIX: To proactively block serialization (even if a parent class
     * implements it), override writeObject and readObject to throw exceptions.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException("Serialization is restricted for sensitive classes");
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        throw new NotSerializableException("Serialization is restricted for sensitive classes");
    }

    @Override
    public String toString() {
        return "SecureSensitiveAccount[id=" + accountId + ", balance=" + balance + "]";
    }
}
