import java.io.FileWriter;
import java.io.IOException;

public class VulnerableResourceHandler {

    public void processData(String filename, String data) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write(data);
            System.out.println("Data written to " + filename);
        } finally {
            // CRITICAL VULNERABILITY: The FileWriter is NOT closed here.
            // This will lead to a resource leak.
            // A proper implementation would have writer.close() here.
            // For demonstration, we explicitly leave it open.
        }
    }

    public static void main(String[] args) {
        VulnerableResourceHandler handler = new VulnerableResourceHandler();
        String filename = "vulnerable_output.txt";

        System.out.println("--- Scenario: Demonstrating resource leak ---");
        try {
            // This call will open a file and not close it.
            handler.processData(filename, "This data will leak a file handle.");
            System.out.println("File handle for " + filename + " is now leaked.");
        } catch (IOException e) {
            System.err.println("Caught exception in main: " + e.getMessage());
        }
    }
}

