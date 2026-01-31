import java.util.List;

/**
 * AuditModule interface.
 * Defines a method for external auditing modules to perform an audit on a list
 * of transactions.
 */
public interface AuditModule {
    void performAudit(List<Transaction> records);
}
