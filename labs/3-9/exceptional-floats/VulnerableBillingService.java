/**
 * Lab 3-9: Vulnerable Billing Service.
 * Demonstrates logic bypass using NaN.
 */
public class VulnerableBillingService {

    public double calculateTotal(double basePrice, double userDiscount) {
        System.out.println("[Vulnerable] Calculating total for Price: " + basePrice + ", Discount: " + userDiscount);

        // VULNERABILITY: This check is intended to prevent negative discounts
        // (surcharges).
        // However, if userDiscount is NaN, (userDiscount < 0) is FALSE.
        // The check is bypassed!
        if (userDiscount < 0) {
            System.out.println("   !!! ERROR: Negative discounts are not allowed!");
            return basePrice;
        }

        double total = basePrice - userDiscount;
        System.out.println("   -> Resulting Total: " + total);
        return total;
    }
}
