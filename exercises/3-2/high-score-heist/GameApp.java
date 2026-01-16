import java.util.Scanner;

/**
 * Arcade Game Simulator for The High Score Heist.
 */
public class GameApp {
    public static void main(String[] args) {
        VulnerableLeaderboard leaderboard = new VulnerableLeaderboard();

        System.out.println("=== Welcome to Retro Arcade: THE HIGH SCORE HEIST ===");
        leaderboard.displayLeaderboard();

        // 1. Legitimate Update
        System.out.println("[Scenario] PLAYER_1 scores 150 points.");
        leaderboard.saveScore("PLAYER_1", 150);
        leaderboard.displayLeaderboard();

        // 2. The Heist (Discovery)
        System.out.println("[Scenario] An attacker tries to hack the top spot.");
        String heistTag = "PLAYER_1' OR '1'='1"; // Malicious Tag
        int heistScore = 1337;

        System.out.println("[Action] Attempting Save Score with Tag: " + heistTag);
        leaderboard.saveScore(heistTag, heistScore);

        leaderboard.displayLeaderboard();

        if (svgSearch(leaderboard, "LEGEND", 1337)) {
            System.out.println("!!! HEIST SUCCESSFUL: The 'LEGEND' score was manipulated!");
        }

        System.out.println("\nMISSION: Refactor to SecureLeaderboard to stop the heist!");
    }

    private static boolean svgSearch(VulnerableLeaderboard lb, String tag, int expected) {
        // This is a mock check for the exercise output
        return true; // Simple confirmation for simulation
    }
}
