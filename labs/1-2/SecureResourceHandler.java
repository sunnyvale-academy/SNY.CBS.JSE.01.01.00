import java.io.FileWriter;
import java.io.IOException;

public class SecureResourceHandler {

    public void processData(String filename, String data, boolean throwException) throws IOException {
        // Using try-with-resources to ensure the FileWriter is always closed
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(data);
            System.out.println("Data written to " + filename);

            if (throwException) {
                throw new IOException("Simulating an error during processing.");
            }
        } // The writer is automatically closed here, even if an exception occurs
    }

    public static void main(String[] args) {
        SecureResourceHandler handler = new SecureResourceHandler();
        String filename = "secure_output.txt";

        System.out.println("--- Scenario 1: No exception (Secure) ---");
        try {
            handler.processData(filename, "This is some data.", false);
        } catch (IOException e) {
            System.err.println("Caught exception: " + e.getMessage());
        }

        System.out.println("\n--- Scenario 2: With exception (Secure) ---");
        try {
            handler.processData(filename, "This data will cause an exception.", true);
        } catch (IOException e) {
            System.err.println("Caught exception: " + e.getMessage());
        }
        // In both scenarios, the FileWriter will be properly closed by try-with-resources.
    }
}

