public class ReportApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 5-4: The Trusted Path ---");

        ReportManager manager = new ReportManager();

        // 1. Legitimate use case
        manager.processReport("monthly_sales.pdf");

        // 2. Malicious use case: Bypassing the base directory
        // Attacker provides an absolute path. Path.resolve() will "jump" out.
        System.out.println("\n[ATTACK] Attempting to access system files...");
        manager.processReport("/etc/passwd");
    }
}
