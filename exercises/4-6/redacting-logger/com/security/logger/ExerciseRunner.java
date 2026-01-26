package com.security.logger;

/**
 * Exercise Runner: Demonstrates the bypass of the redacting logger.
 */
public class ExerciseRunner {
    public static void main(String[] args) {
        SecureRedactingLogger logger = new SecureRedactingLogger();

        System.out.println("--- Scenario 1: Using log(String) ---");
        logger.log("User logged in with password=secret123");

        System.out.println("\n--- Scenario 2: Using logMultiple(String...) (The Bypass) ---");
        // An unsuspecting developer uses the new, efficient logMultiple method
        logger.logMultiple(
                "Connection established",
                "Attempting login with password=vulnerable_password");
    }
}
