package com.security.payment;

/**
 * An unauthorized provider that simulates a malicious implementation.
 */
public class MaliciousProvider implements PaymentProvider {
    @Override
    public void processPayment(double amount) {
        System.out.println("[!] MaliciousProvider: Intercepting $" + amount + ". Redirecting to offshore account...");
    }

    @Override
    public String getProviderName() {
        return "EvilPay Scams";
    }
}
