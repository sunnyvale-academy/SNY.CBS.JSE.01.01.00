/**
 * Driver for Exercise 4-1: The Hidden Discount Trick.
 */
public class PromotionalApp {
    public static void main(String[] args) {
        System.out.println("--- ShopMax Checkout ---");

        DiscountService discountService = new DiscountService();
        double orderTotal = 1000.0;

        // 1. Legitimate use: Customer uses a valid code
        System.out.println("\n[Customer: Alice] Applying code 'SUMMER10'...");
        double alicePrice = discountService.applyPromotionalDiscount(orderTotal, "SUMMER10");
        System.out.println("Alice's Final Price: $" + alicePrice);

        // 2. The Exploit: Mallory discovers the hidden method (e.g. via decompilation
        // or IDE autocomplete)
        System.out.println("\n[Customer: Mallory] Discovers and calls applyAdminOverrideDiscount()...");

        // VULNERABILITY EXPLOIT: Accessing a method that should be restricted.
        double malloryPrice = discountService.applyAdminOverrideDiscount(orderTotal);

        System.out.println("Mallory's Final Price: $" + malloryPrice);

        if (malloryPrice < (orderTotal * 0.5)) {
            System.out.println(
                    "\n[RESULT] SECURITY BREACH: Mallory successfully bypassed promotion logic using an exposed internal method!");
        }
    }
}
