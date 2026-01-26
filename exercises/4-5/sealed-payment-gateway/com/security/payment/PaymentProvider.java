package com.security.payment;

/**
 * Guideline 4-5 / EXTEND-5: Limit the extensibility of classes and methods
 * 
 * This interface represents a payment provider.
 * Currently, it is not sealed, allowing anyone to implement it.
 */
public interface PaymentProvider {
    void processPayment(double amount);

    String getProviderName();
}
