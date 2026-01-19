import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A diagnostic tool that executes 'ping' via a shell.
 * VULNERABILITY: OS Command Injection (Guideline 3-4).
 */
public class VulnerableDiagnosticTool {

    public String testConnectivity(String target) {
        StringBuilder output = new StringBuilder();
        try {
            // VULNERABILITY: Constructing a shell command with untrusted data.
            // Using /bin/sh -c makes metacharacters like ; and & active.
            String command = "ping -c 1 " + target;
            Process process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", command });

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            process.waitFor();
        } catch (Exception e) {
            output.append("System Error: ").append(e.getMessage());
        }
        return output.toString();
    }
}
