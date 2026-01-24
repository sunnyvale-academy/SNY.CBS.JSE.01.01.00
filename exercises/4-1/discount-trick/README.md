# Exercise 4-1: The Hidden Discount Trick

## Scenario
"ShopMax" is having a summer sale. Customers can apply standard promotional codes (like `SUMMER10`) for small discounts.

However, the internal audit team has noticed several orders with a massive **90% discount** applied. There is no such promotional code available to the public. You have been tasked with investigating the `DiscountService` to find out how these "VIP" discounts are being triggered.

## Your Goal
1.  **Analyze**: Open `DiscountService.java`. Review the visibility of the methods. Is there a method that shouldn't be accessible to everyone?
2.  **Exploit**: Run `PromotionalApp.java`. It simulates a regular customer who discovers an "internal-only" method.
3.  **Confirm**: Observe the console output. Does the customer successfully apply an unauthorized administrative discount?
4.  **Fix**: Implement a secure version `SecureDiscountService.java` that restricts access to sensitive methods following [Guideline 4-1](https://www.oracle.com/java/technologies/javase/seccodeguide.html#4-1).

## Instructions
1.  Compile the exercise:
    ```bash
    javac *.java
    ```
2.  Run the application:
    ```bash
    java PromotionalApp
    ```

Observe how "Mallory" applies a 90% discount by calling a method that was mistakenly left `public`.
