import java.util.ArrayList;
import java.util.List;

/**
 * VULNERABLE CLASS: FinancialSystem
 * Manages an internal ledger of transactions.
 * VIOLATION: Passes its internal mutable ledger directly to an untrusted
 * AuditModule.
 */
public class FinancialSystem {
    private final List<Transaction> ledger = new ArrayList<>();

    public void addTransaction(double amount, String description) {
        ledger.add(new Transaction(amount, description));
    }

    /**
     * Triggers an external audit.
     * VULNERABILITY: The internal 'ledger' list (containing mutable Transaction
     * objects)
     * is leaked to the untrusted AuditModule.
     */
    public void triggerAudit(AuditModule auditor) {
        System.out.println("Starting external audit...");

        // VULNERABILITY: Guideline 6-6 / MUTABLE-6 Violation.
        // Even though this is an 'input' to the performAudit method,
        // it is an 'output' from this class's perspective.
        auditor.performAudit(ledger);
    }

    public void printLedger() {
        System.out.println("--- Current Ledger ---");
        for (Transaction t : ledger) {
            System.out.println(t);
        }
    }
}
