import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility that executes system commands safely using ProcessBuilder and
 * argument separation.
 * SECURE: Prevents OS Command Injection (Guideline 3-4).
 */
public class SecureCommandExecutor {

    public String getFileMetadata(String filename) {
        StringBuilder output = new StringBuilder();
        try {
            // SECURE: Use ProcessBuilder with a list of arguments.
            // This ensures each argument is passed as a literal string to the OS,
            // bypassing shell interpretation.
            ProcessBuilder pb = new ProcessBuilder("ls", "-l", filename);
            pb.redirectErrorStream(true); // Combine stdout and stderr

            Process process = pb.start();

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
