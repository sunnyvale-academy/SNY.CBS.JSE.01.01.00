package com.security.discount;

/**
 * Interface for providing discounts.
 * Implementations might be provided by third-party plugins.
 */
public interface DiscountProvider {
    /**
     * @param orderTotal The current total of the order.
     * @return A multiplier to apply to the total (e.g., 0.9 for 10% off).
     */
    double getDiscountMultiplier(double orderTotal);
}
