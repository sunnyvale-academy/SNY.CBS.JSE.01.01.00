package com.security.discount;

/**
 * A service that calculates the final order price.
 */
public class CheckoutService {
    private final DiscountProvider discountProvider;

    public CheckoutService(DiscountProvider discountProvider) {
        this.discountProvider = discountProvider;
    }

    public void processCheckout(double originalTotal) {
        System.out.println("[CHECKOUT] Processing order. Original Total: $" + originalTotal);

        // VULNERABILITY: Directly trusting the output of an untrusted object
        double multiplier = discountProvider.getDiscountMultiplier(originalTotal);

        System.out.println("[CHECKOUT] Received discount multiplier: " + multiplier);

        double finalTotal = originalTotal * multiplier;

        System.out.println("[CHECKOUT] Final Total to charge: $" + finalTotal);

        if (finalTotal < originalTotal * 0.5) {
            System.out.println("[ALERT] Unexpectedly large discount detected!");
        }
    }
}
