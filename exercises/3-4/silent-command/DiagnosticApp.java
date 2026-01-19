import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Driver app for the Silent Command exercise.
 */
public class DiagnosticApp {
    public static void main(String[] args) throws IOException {
        System.out.println("=== Exercise 3-4: The Silent Command ===");

        // Setup: Create a sensitive file representing a "Flag" or secret
        // File secretFile = new File("server_secrets.txt");
        // try (FileWriter writer = new FileWriter(secretFile)) {
        // writer.write("CONFIDENTIAL_FLAG{COMMAND_INJECTION_SUCCESS}");
        // }

        VulnerableDiagnosticTool tool = new VulnerableDiagnosticTool();
        // SecureDiagnosticTool tool = new SecureDiagnosticTool();

        // 1. Normal Usage
        System.out.println("\n[Normal] Testing connectivity to 127.0.0.1...");
        System.out.println(tool.testConnectivity("127.0.0.1"));

        // 2. The Hack (Exploit)
        // Payload closes ping and runs cat
        String hackPayload = "127.0.0.1; cat server_secrets.txt";

        System.out.println("\n[Action] Attempting hack with payload: " + hackPayload);
        String result = tool.testConnectivity(hackPayload);

        System.out.println("\n[RESULTING OUTPUT]");
        System.out.println(result);

        if (result.contains("CONFIDENTIAL_FLAG")) {
            System.out.println("\n!!! HACK SUCCESSFUL: You captured the secret flag!");
        } else {
            System.out.println("\n!!! HACK FAILED: The secret flag is secure.");
        }

        // Cleanup
        // secretFile.delete();

        System.out.println("\nMISSION: Implement SecureDiagnosticTool to neutralize this attack!");
    }
}
