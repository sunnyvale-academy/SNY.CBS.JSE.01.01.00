/**
 * VulnerableSearchService.java
 * 
 * This class demonstrates an injection vulnerability (Guideline 3-2).
 * Building SQL queries through string concatenation with untrusted input
 * allows an attacker to manipulate the query logic.
 */
public class VulnerableSearchService {

    /**
     * Searches for a user by their username.
     * 
     * @param username The username to search for (untrusted input).
     */
    public void searchUser(String username) {
        // VULNERABILITY: The query is built by concatenating the raw input string.
        String query = "SELECT * FROM users WHERE username = '" + username + "'";

        System.out.println("--- Vulnerable Service ---");
        System.out.println("Raw Input: " + username);
        System.out.println("Generated Query: " + query);

        // REALISTIC VALIDATION: Proving the SQL statement is syntactically valid
        SqlParser parser = new SqlParser();
        try {
            String structure = parser.validate(query);
            System.out.println("[SYNTAX CHECK] STATUS: VALID");
            System.out.println("[SYNTAX CHECK] STRUCTURE: " + structure);
        } catch (SqlParser.SyntaxException e) {
            System.out.println("[SYNTAX CHECK] STATUS: INVALID");
            System.out.println("[SYNTAX CHECK] ERROR: " + e.getMessage());
        }

        System.out.println("Result: [Query logic potentially compromised]");
        System.out.println("--------------------------\n");
    }
}
