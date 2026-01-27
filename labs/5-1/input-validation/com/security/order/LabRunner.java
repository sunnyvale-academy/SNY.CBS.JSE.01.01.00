package com.security.order;

/**
 * Lab Runner to demonstrate input validation vulnerabilities.
 */
public class LabRunner {
    public static void main(String[] args) {
        VulnerableOrderService service = new VulnerableOrderService();

        System.out.println("--- Scenario 1: Negative Quantity Exploit ---");
        // An attacker might use a negative quantity to "refund" themselves
        Order negativeOrder = new Order("Gift Card", -5, 100);
        service.processOrder(negativeOrder);

        System.out.println("\n--- Scenario 2: Integer Overflow ---");
        // Large values cause the 'int' total to overflow and wrap around
        // Integer.MAX_VALUE is 2,147,483,647
        Order giantOrder = new Order("Wholesale Widgets", 1000000, 3000);
        service.processOrder(giantOrder);

        System.out.println("\n--- Scenario 3: Extreme Integer Overflow ---");
        // Carefully crafted values can lead to a small positive or negative total
        Order maliciousLargeOrder = new Order("Industrial Steel", 2, 1073741824 + 50);
        service.processOrder(maliciousLargeOrder);
    }
}
