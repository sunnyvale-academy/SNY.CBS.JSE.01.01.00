/**
 * SecureSearchService.java
 * 
 * This class demonstrates the secure alternative to dynamic SQL (Guideline
 * 3-2).
 * Using parameterized queries (PreparedStatements) ensures that input is
 * treated
 * as data, not as executable code.
 */
public class SecureSearchService {

    /**
     * Searches for a user securely using a parameterized query.
     * 
     * @param username The username to search for.
     */
    public void searchUser(String username) {
        // SECURE: Use a placeholder (?) for the input.
        // This query structure is constant and cannot be modified by the input.
        String sql = "SELECT * FROM users WHERE username = ?";

        System.out.println("--- Secure Service ---");
        System.out.println("SQL Template: " + sql);
        System.out.println("Parameter set to: '" + username + "'");

        // In a real application, you would use:
        // PreparedStatement pstmt = connection.prepareStatement(sql);
        // pstmt.setString(1, username);
        // This ensures the database driver treats the input as a literal value,
        // preventing any embedded SQL commands from being executed.

        System.out.println("Result: [Input is safely treated as a literal string value]");
        System.out.println("----------------------\n");
    }
}
