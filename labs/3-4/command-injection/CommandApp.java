import java.io.File;
import java.io.IOException;

/**
 * Driver application for Lab 3-4: OS Command Injection.
 */
public class CommandApp {
    public static void main(String[] args) throws IOException {
        System.out.println("--- Lab 3-4: The Runaway Command ---");

        // Create a dummy file for the test
        File dummy = new File("test.txt");
        dummy.createNewFile();

        // Malicious input: tries to list a file and then execute 'echo'
        // On many Unix-like systems, Runtime.exec("ls -l test.txt; echo HACKED")
        // might fail because Runtime.exec doesn't spawn a shell by default,
        // but it's a common pattern to demonstrate the risk of dynamic strings.
        String injectionPayload = "test.txt; echo '!!! SYSTEM HACKED !!!'";

        VulnerableCommandExecutor vulnerable = new VulnerableCommandExecutor();
        SecureCommandExecutor secure = new SecureCommandExecutor();

        System.out.println("\n[VULNERABLE EXECUTION]");
        System.out.println("Input Payload: " + injectionPayload);
        String vOutput = vulnerable.getFileMetadata(injectionPayload);
        System.out.println("Output:\n" + vOutput);

        // Note: In some Java environments, Runtime.exec(string) might split on
        // whitespace
        // and fail to execute the 'echo' if it's not run through 'sh -c'.
        // However, the risk remains that dynamic command strings are brittle and
        // dangerous.
        if (vOutput.contains("SYSTEM HACKED")) {
            System.out.println("!!! EXPLOIT SUCCESSFUL: Injected command was executed!");
        } else {
            System.out.println(
                    "(Note: Direct execution might have failed depending on shell/OS, but the vulnerability remains in the code pattern)");
        }

        System.out.println("\n[SECURE EXECUTION]");
        System.out.println("Input Payload: " + injectionPayload);
        String sOutput = secure.getFileMetadata(injectionPayload);
        System.out.println("Output:\n" + sOutput);

        if (!sOutput.contains("SYSTEM HACKED")) {
            System.out.println("SUCCESS: The secure version treated the input as a literal filename.");
        }

        // Cleanup
        dummy.delete();
    }
}
