/**
 * Driver app for Lab 3-9: Exceptional Floating Point Values.
 */
public class BillingApp {
    public static void main(String[] args) {
        System.out.println("--- Lab 3-9: Exceptional Floating Point Values ---");

        VulnerableBillingService vulnerable = new VulnerableBillingService();
        SecureBillingService secure = new SecureBillingService();

        double price = 100.0;

        // 1. Normal negative discount (Blocked)
        System.out.println("\n[Test 1] Negative Discount (-50.0)");
        vulnerable.calculateTotal(price, -50.0);

        // 2. NaN discount (Bypassed in vulnerable!)
        // In a real web app, this might come from a JSON field or URL param
        double maliciousNaN = Double.NaN;

        System.out.println("\n[Test 2] Malicious NaN Discount (The Bypass)");
        vulnerable.calculateTotal(price, maliciousNaN);

        // 3. Secure Verification
        System.out.println("\n[Test 3] Malicious NaN Discount with Secure Service");
        secure.calculateTotal(price, maliciousNaN);

        System.out.println("\n[Test 4] Infinity Discount with Secure Service");
        secure.calculateTotal(price, Double.POSITIVE_INFINITY);
    }
}
