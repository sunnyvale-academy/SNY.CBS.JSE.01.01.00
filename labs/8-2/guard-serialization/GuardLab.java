public class GuardLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 8-2: The Visible Secret ---");

        try {
            String myCVV = "987";
            CreditCard card = new CreditCard("1234-5678-9012-3456", myCVV);

            System.out.println("[1] Created card: " + card);

            // Check if the CVV is in the serialized stream
            System.out.println("\n[2] Checking if CVV '" + myCVV + "' is leaked during serialization...");
            boolean leaked = StreamInspector.isSecretInStream(card, myCVV);

            if (leaked) {
                System.out.println("RESULT: Leaked! The CVV was found in the byte stream.");
                System.out.println("\nALERT: Sensitive data leaked via serialization! (Vulnerable)");
            } else {
                System.out.println("RESULT: Secure. No secret found in stream.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
