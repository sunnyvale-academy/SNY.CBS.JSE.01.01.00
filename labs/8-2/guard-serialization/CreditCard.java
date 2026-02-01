import java.io.Serializable;

/**
 * VULNERABLE CLASS: CreditCard
 * Represents a payment card.
 * VIOLATION: The sensitive CVV field is not marked as transient,
 * so it will be leaked during serialization.
 */
public class CreditCard implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String cardNumber;
    private final String cvv; // SENSITIVE DATA

    public CreditCard(String cardNumber, String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    // No getter for CVV to keep it "private"...
    // but serialization explores all non-transient fields!

    @Override
    public String toString() {
        return "CreditCard[number=" + cardNumber + ", cvv=***]";
    }
}
