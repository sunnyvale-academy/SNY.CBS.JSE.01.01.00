package com.security.order;

/**
 * Service that processes orders without validating inputs.
 */
public class VulnerableOrderService {

    /**
     * Processes an order and calculates the total cost.
     * 
     * VULNERABILITY:
     * 1. No range checking for quantity or price (allows negative values).
     * 2. Integer overflow when calculating total cost.
     */
    public void processOrder(Order order) {
        System.out.println("[SYSTEM] Processing order for: " + order.getProductName());

        // Risky calculation: potential overflow and negative values
        int totalCost = order.getQuantity() * order.getPricePerUnit();

        System.out.println("[SYSTEM] Quantity: " + order.getQuantity());
        System.out.println("[SYSTEM] Price per unit: $" + order.getPricePerUnit());
        System.out.println("[SYSTEM] Total Cost: $" + totalCost);

        if (totalCost < 0) {
            System.out.println("[ALERT] Total cost is negative! This indicates an exploit or overflow.");
        } else if (totalCost > 1000000) {
            System.out.println("[SYSTEM] Large order confirmed.");
        }
    }
}
