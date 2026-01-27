package com.security.discount;

/**
 * Runner for Guideline 5-2 exercise.
 */
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println("--- Scenario: Malicious Discount Provider ---");

        // Simulating a provider that tries to "cheat" the system
        DiscountProvider rogueProvider = new DiscountProvider() {
            @Override
            public double getDiscountMultiplier(double orderTotal) {
                // Returns a value that makes the price negative (to get a refund)
                // or extremely large (to charge the user more)
                return -1.0;
            }
        };

        CheckoutService service = new CheckoutService(rogueProvider);
        service.processCheckout(100.0);

        System.out.println("\n--- Scenario: NaN Discount Provider ---");
        DiscountProvider nanProvider = new DiscountProvider() {
            @Override
            public double getDiscountMultiplier(double orderTotal) {
                return Double.NaN;
            }
        };

        CheckoutService nanService = new CheckoutService(nanProvider);
        nanService.processCheckout(100.0);
    }
}
