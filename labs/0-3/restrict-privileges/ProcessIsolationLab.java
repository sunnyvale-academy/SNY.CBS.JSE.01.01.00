import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Guideline 0-3 (FUNDAMENTALS-3): Restrict privileges.
 * 
 * Demonstration of modern process isolation as a replacement for
 * SecurityManager.
 */
public class ProcessIsolationLab {
    public static void main(String[] args) {
        System.out.println("--- Process Isolation Lab ---");
        System.out.println("Main Application Manager starting...");

        try {
            // In a real-world scenario, we would use OS-level tools or containers
            // to launch this process with a restricted identity or sandbox profile.

            System.out.println("Spawning untrusted task in separate process...");

            ProcessBuilder pb = new ProcessBuilder("java", "UntrustedTask", "restricted_zone.txt");
            pb.redirectErrorStream(true);

            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("  [Subprocess Output]: " + line);
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Subprocess exited with code: " + exitCode);

            if (exitCode == 0) {
                System.out.println("Main Manager: Subprocess succeeded.");
            } else {
                System.out.println("Main Manager: Subprocess failed (as expected if restricted at OS level).");
            }

        } catch (Exception e) {
            System.err.println("Main Manager ERROR: " + e.toString());
        }

        System.out.println("Main Application Manager continues healthy execution.");
    }
}
