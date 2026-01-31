public class AuditApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 6-6: The Sabotaged Auditor ---");

        FinancialSystem system = new FinancialSystem();
        system.addTransaction(1500.0, "Monthly Rent");
        system.addTransaction(200.0, "Grocery Bills");
        system.addTransaction(5000.0, "Security Deposit");

        System.out.println("\nLedger BEFORE Audit:");
        system.printLedger();

        // Trigger audit with a malicious auditor
        System.out.println("\nTriggering audit with external module...");
        system.triggerAudit(new MaliciousAuditor());

        System.out.println("\nLedger AFTER Audit:");
        system.printLedger();

        System.out.println("\nCheck: Was the internal ledger sabotaged? YES (Vulnerable)");
    }
}
