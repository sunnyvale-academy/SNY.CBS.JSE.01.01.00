package com.security.payment;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise Runner: Simulates a system that processes payments using registered
 * providers.
 */
public class ExerciseRunner {
    private final List<PaymentProvider> providers = new ArrayList<>();

    public void registerProvider(PaymentProvider provider) {
        System.out.println("[SYSTEM] Registering provider: " + provider.getProviderName());
        providers.add(provider);
    }

    public void runPayments() {
        for (PaymentProvider provider : providers) {
            System.out.println("\n--- Processing via: " + provider.getProviderName() + " ---");
            provider.processPayment(100.0);
        }
    }

    public static void main(String[] args) {
        ExerciseRunner runner = new ExerciseRunner();

        // 1. Register legitimate provider
        runner.registerProvider(new AuthorizedCreditCardProvider());

        // 2. Register malicious provider (possible because interface is not sealed)
        runner.registerProvider(new MaliciousProvider());

        // 3. Run all payments
        runner.runPayments();
    }
}
