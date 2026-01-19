import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A utility that executes system commands using string concatenation.
 * VULNERABILITY: OS Command Injection (Guideline 3-4).
 */
public class VulnerableCommandExecutor {

    public String getFileMetadata(String filename) {
        StringBuilder output = new StringBuilder();
        try {
            // VULNERABILITY: Even if we use a shell, concatenating untrusted data
            // into the shell command string is extremely dangerous.
            String command = "ls -l " + filename;
            Process process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", command });

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            process.waitFor();
        } catch (Exception e) {
            output.append("Error: ").append(e.getMessage());
        }
        return output.toString();
    }
}
