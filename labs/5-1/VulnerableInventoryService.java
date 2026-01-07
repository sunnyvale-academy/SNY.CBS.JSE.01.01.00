/**
 * VulnerableInventoryService.java
 * 
 * This class demonstrates a failure to validate inputs (Guideline 5-1).
 * Methods that process numeric values should always check for valid ranges.
 */
public class VulnerableInventoryService {

    /**
     * Processes an order for items.
     * 
     * @param quantity The number of items (should be positive).
     * @param price    Per-item price.
     */
    public void processOrder(int quantity, double price) {
        // VULNERABILITY: This method does not validate its inputs.
        // It blindly processes whatever values are passed, which could lead
        // to invalid business states (e.g., negative totals).
        double total = quantity * price;

        System.out.println("--- Vulnerable Service ---");
        System.out.println("Processing order: " + quantity + " units @ $" + price);
        System.out.println("Order Total: $" + total);

        if (total < 0) {
            System.out.println("ALERT: Negative total processed! This might result in an unauthorized credit/refund.");
        }
        System.out.println("--------------------------\n");
    }
}
