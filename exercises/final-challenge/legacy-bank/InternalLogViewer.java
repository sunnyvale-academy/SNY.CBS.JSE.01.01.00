import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.*;
import java.nio.file.*;

/**
 * VULNERABLE CLASS: InternalLogViewer
 * Guidelines:
 * - ACCESS-3 (9-3): Safely invoke doPrivileged.
 */
public class InternalLogViewer {
    public void readLog(String logPath) {
        // Vulnerability: Passing untrusted 'logPath' directly into a
        // doPrivileged block without any sanitization.
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            try {
                String content = new String(Files.readAllBytes(Paths.get(logPath)));
                System.out.println("Log content: " + content);
            } catch (IOException e) {
                System.err.println("Error reading log.");
            }
            return null;
        });
    }
}
