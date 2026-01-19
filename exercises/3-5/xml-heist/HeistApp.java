import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Driver app for Exercise 3-5: The XML Heist.
 */
public class HeistApp {
    public static void main(String[] args) throws IOException {
        System.out.println("=== Exercise 3-5: The XML Heist ===");

        // Setup: Create a sensitive "secret" file
        // File passwordFile = new File("db_passwords.txt");
        // try (FileWriter writer = new FileWriter(passwordFile)) {
        // writer.write("DB_USER=admin\nDB_PASS=S3cur3P@ssw0rd!_2024");
        // }

        VulnerableDocManager manager = new VulnerableDocManager();

        // 1. Normal Usage
        String normalXml = "<report><status>All systems operational</status></report>";
        System.out.println("\n[Normal] Processing standard report...");
        System.out.println(manager.parseReport(normalXml));

        // 2. The Heist (Placeholder for students to test their payload)
        // Students should replace this with a malicious XML payload.
        String heistPayload = "<!-- CRAFT YOUR MALICIOUS XML HERE -->";

        System.out.println("\n[Action] Attempting heist...");
        // In the exercise, we show the result of a potential exploit
        String result = manager.parseReport(heistPayload);

        System.out.println("\n[RESULTING OUTPUT]");
        System.out.println(result);

        if (result.contains("DB_PASS")) {
            System.out.println("\n!!! HEIST SUCCESSFUL: You leaked the database passwords!");
        }

        // Cleanup
        // passwordFile.delete();

    }
}
