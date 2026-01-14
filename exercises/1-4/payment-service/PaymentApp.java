/**
 * PaymentApp.java
 * 
 * Demonstrates the issues with the vulnerable service and provides a
 * playground for testing the secure implementation.
 */
public class PaymentApp {

    public static void main(String[] args) {
        System.out.println("=== Java Secure Coding Exercise 1-4 ===\n");

        runVulnerableDemo();

        // TODO: Uncomment the following section after implementing your fix
        /*
         * System.out.println("\n-------------------------------------------");
         * runSecureDemo();
         */
    }

    private static void runVulnerableDemo() {
        System.out.println("--- Testing Vulnerable Payment Service ---");
        VulnerablePaymentService service = new VulnerablePaymentService();

        try {
            // Case 1: Information Leak
            System.out.println("Processing large payment...");
            String result = service.processPayment("card_123", 5000.0);
            System.out.println("Result: " + result);

            // Case 2: Silent Background Crash
            System.out.println("\nStarting background worker...");
            service.startAuditWorker();

            // Wait a bit to see if we see any output from the worker
            Thread.sleep(1000);
            System.out.println("\nManager: Background thread should have finished, but was it successful?");

        } catch (Exception e) {
            System.err.println("Main App Crash: " + e.toString());
        }
    }

    /*
     * private static void runSecureDemo() {
     * System.out.println("--- Testing Secure Payment Service ---");
     * 
     * // 1. SET GLOBAL HANDLER
     * Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
     * System.err.println("\n[SECURE AUDIT] Caught unexpected crash in: " +
     * t.getName());
     * System.err.println("[SECURE AUDIT] Error details logged internally.");
     * });
     * 
     * SecurePaymentService service = new SecurePaymentService();
     * 
     * // Test information leak prevention
     * System.out.println("Processing large payment...");
     * String result = service.processPayment("card_123", 5000.0);
     * System.out.println("Result: " + result);
     * 
     * // Test background crash visibility
     * System.out.println("\nStarting background worker...");
     * service.startAuditWorker();
     * 
     * try { Thread.sleep(1000); } catch (Exception e) {}
     * }
     */
}
