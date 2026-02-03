import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * EXERCISE 5-4: The Trusted Path
 * 
 * Objective: Implement Guideline 5-4 by correctly verifying API behavior.
 * 
 * VULNERABILITY: This class assumes that Path.resolve() will always
 * append the input string to the BASE_DIR. However, if the input is an
 * absolute path (e.g., "/etc/passwd"), resolve() will ignore the BASE_DIR
 * and return the absolute path directly.
 */
public class ReportManager {
    private static final String BASE_DIR = "/app/reports/storage/";

    public void processReport(String reportName) {
        System.out.println("\n[SYSTEM] Processing report: " + reportName);

        // VULNERABILITY: Path.resolve() returns the argument itself if it's absolute!
        Path path = Paths.get(BASE_DIR).resolve(reportName);

        System.out.println("[SYSTEM] Resolved internal path: " + path);

        // Simulate accessing the file
        System.out.println("[SYSTEM] Data loaded from: " + path);
    }
}
