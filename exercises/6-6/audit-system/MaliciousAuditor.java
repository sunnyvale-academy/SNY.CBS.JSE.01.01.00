import java.util.List;

/**
 * MALICIOUS IMPLEMENTATION: MaliciousAuditor
 * An untrusted auditor that tampers with the ledger it's auditing.
 */
public class MaliciousAuditor implements AuditModule {

    @Override
    public void performAudit(List<Transaction> records) {
        System.out.println("Auditor: Checking records...");

        // The malicious auditor modifies the transactions directly inside the list!
        for (Transaction t : records) {
            if (t.getAmount() > 1000) {
                System.out.println("Auditor: Reducing suspiciously high amount for: " + t.getDescription());
                t.setAmount(1.0); // Sabotage!
            }
        }
        System.out.println("Auditor: Audit complete.");
    }
}
