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
        // An attacker can input SQL control characters like ' and OR to change the
        // query's behavior.
        String query = "SELECT * FROM users WHERE username = '" + username + "'";

        System.out.println("--- Vulnerable Service ---");
        System.out.println("Raw Input: " + username);
        System.out.println("Executing Query: " + query);

        // In a real application, this would be executed against a database.
        // For this lab, we just print the resulting query string to show the injection.
        System.out.println("Result: [Query logic potentially compromised if injection characters are present]");
        System.out.println("--------------------------\n");
    }
}
