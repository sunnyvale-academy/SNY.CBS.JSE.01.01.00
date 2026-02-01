import java.io.Serializable;

/**
 * SECURE CLASS: SecureCreditCard
 * FIX: Marks sensitive fields as 'transient' to exclude them from
 * serialization.
 */
public class SecureCreditCard implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String cardNumber;

    /**
     * FIX: The 'transient' keyword prevents this field from being
     * written to the serial stream.
     */
    private transient final String cvv;

    public SecureCreditCard(String cardNumber, String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return "SecureCreditCard[number=" + cardNumber + ", cvv=***]";
    }
}
