/**
 * Exercise 4-1: Vulnerable Discount Service.
 * Exposes internal administrative methods as public.
 */
public class DiscountService {

    /**
     * Public API for standard promotional codes.
     */
    public double applyPromotionalDiscount(double price, String code) {
        if ("SUMMER10".equals(code)) {
            return price * 0.90; // 10% off
        }
        return price;
    }

    /**
     * VULNERABILITY: Internal administrative method intended for support agents.
     * Guideline 4-1: Limit accessibility. This should NOT be public.
     */
    public double applyAdminOverrideDiscount(double price) {
        System.out.println("[CRITICAL] Admin Override Discount Applied: 90% OFF");
        return price * 0.10;
    }
}
