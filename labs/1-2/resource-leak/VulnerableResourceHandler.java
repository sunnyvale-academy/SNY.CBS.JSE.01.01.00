import java.io.FileWriter;
import java.io.IOException;

public class VulnerableResourceHandler {

    public void processData(String filename, String data, boolean throwException) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write(data);
            System.out.println("Data written to " + filename);

            if (throwException) {
                throw new IOException("Simulating an error during processing.");
            }
        } finally {
            // CRITICAL VULNERABILITY: The FileWriter is NOT closed here.
            // If an exception occurs (e.g., during writer.write() or if throwException is true),
            // the writer will remain open, leading to a resource leak.
            // writer.close(); // This line is intentionally omitted to simulate the vulnerability.
        }
    }

    public static void main(String[] args) {
        VulnerableResourceHandler handler = new VulnerableResourceHandler();
        String filename = "vulnerable_output.txt";

        System.out.println("--- Scenario: Demonstrating resource leak ---");
        try {
            // This call will open a file and not close it.
            handler.processData(filename, "This data will leak a file handle.", true);
            System.out.println("File handle for " + filename + " is now leaked.");
        } catch (IOException e) {
            System.err.println("Caught exception in main: " + e.getMessage());
        }
    }
}

