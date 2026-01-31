/**
 * VULNERABLE CLASS: TransactionDB
 * Guidelines:
 * - INJECT-2 (3-2): SQL Injection via string concatenation.
 */
public class TransactionDB {
    public void getLogs(String filter) {
        // Vulnerability: Building a query using string concatenation
        String query = "SELECT * FROM transactions WHERE category = '" + filter + "'";
        System.out.println("Executing SQL: " + query);
    }
}
