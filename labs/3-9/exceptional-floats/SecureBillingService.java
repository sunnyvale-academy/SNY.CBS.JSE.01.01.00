/**
 * Lab 3-9: Secure Billing Service.
 * Demonstrates proper validation of exceptional floating point values.
 */
public class SecureBillingService {

    public double calculateTotal(double basePrice, double userDiscount) {
        System.out.println("[Secure] Calculating total for Price: " + basePrice + ", Discount: " + userDiscount);

        // SECURE: Validate that the input is a finite number.
        // Guideline 3-9: Prevent injection of exceptional floating point values.
        if (!Double.isFinite(userDiscount)) {
            System.out.println("   !!! SECURITY BLOCK: Invalid discount value (NaN or Infinity) detected!");
            return basePrice;
        }

        if (userDiscount < 0) {
            System.out.println("   !!! ERROR: Negative discounts are not allowed!");
            return basePrice;
        }

        double total = basePrice - userDiscount;
        System.out.println("   -> Resulting Total: " + total);
        return total;
    }
}
