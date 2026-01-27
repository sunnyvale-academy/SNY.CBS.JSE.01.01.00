package com.security.order;

/**
 * Guideline 5-1 / INPUT-1: Validate inputs
 * 
 * Simple data object representing an order item.
 */
public class Order {
    private final String productName;
    private final int quantity;
    private final int pricePerUnit;

    public Order(String productName, int quantity, int pricePerUnit) {
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }
}
