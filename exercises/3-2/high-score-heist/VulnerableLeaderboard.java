import java.util.*;

/**
 * A mock leaderboard service for the Retro Arcade.
 * VULNERABILITY: Uses dynamic SQL (string concatenation).
 */
public class VulnerableLeaderboard {

    // Simple mock database
    private Map<String, Integer> scores = new HashMap<>();

    public VulnerableLeaderboard() {
        scores.put("PLAYER_1", 100);
        scores.put("PLAYER_2", 250);
        scores.put("LEGEND", 9999);
    }

    public void saveScore(String playerTag, int newScore) {
        // VULNERABILITY: Dynamic SQL construction
        String query = "UPDATE scores SET score = " + newScore + " WHERE tag = '" + playerTag + "'";

        System.out.println("\n[DB] Processing Query: " + query);

        // Simulated SQL parsing and execution
        if (playerTag.contains("' OR '1'='1")) {
            System.out.println("[DB] ALERT: Injection detected in logic!");
            System.out.println("[DB] ACTION: Effectively updating ALL records due to '1'='1' condition.");
            for (String tag : scores.keySet()) {
                scores.put(tag, newScore);
            }
        } else {
            if (scores.containsKey(playerTag)) {
                scores.put(playerTag, newScore);
                System.out.println("[DB] Success: Updated score for " + playerTag);
            } else {
                System.out.println("[DB] Error: Player tag '" + playerTag + "' not found.");
            }
        }
    }

    public void displayLeaderboard() {
        System.out.println("\n--- CURRENT LEADERBOARD ---");
        scores.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
        System.out.println("---------------------------\n");
    }
}
