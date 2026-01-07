/**
 * SecureInventoryService.java
 * 
 * This class demonstrates how to correctly validate inputs (Guideline 5-1).
 */
public class SecureInventoryService {

    /**
     * Processes an order securely by validating all input parameters.
     * 
     * @param quantity The number of items.
     * @param price    Per-item price.
     * @throws IllegalArgumentException if inputs are invalid.
     */
    public void processOrder(int quantity, double price) {
        // SECURE: Validate all inputs before any processing occurs.
        // This follows the "Fail Fast" principle.
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer.");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        double total = quantity * price;

        System.out.println("--- Secure Service ---");
        System.out.println("Processing order: " + quantity + " units @ $" + price);
        System.out.println("Order Total: $" + total);
        System.out.println("----------------------\n");
    }
}
