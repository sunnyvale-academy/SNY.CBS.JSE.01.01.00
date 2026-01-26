package com.security.payment;

/**
 * A legitimate, trusted payment provider.
 */
public class AuthorizedCreditCardProvider implements PaymentProvider {
    @Override
    public void processPayment(double amount) {
        System.out.println("[+] Processing credit card payment of $" + amount + " via TrustedGate.");
    }

    @Override
    public String getProviderName() {
        return "TrustedGate Credit Card";
    }
}
