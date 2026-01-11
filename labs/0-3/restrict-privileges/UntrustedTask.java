import java.io.File;
import java.io.FileOutputStream;

/**
 * A simple task that attempts to perform a privileged action.
 */
public class UntrustedTask {
    public static void main(String[] args) {
        String fileName = args.length > 0 ? args[0] : "subprocess_result.txt";

        System.out.println("[UntrustedTask] Starting task...");
        try {
            System.out.println("[UntrustedTask] Attempting to write to " + fileName);
            File file = new File(fileName);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write("Content from untrusted subprocess".getBytes());
            }
            System.out.println("[UntrustedTask] Successfully performed privileged action.");
        } catch (Exception e) {
            System.err.println("[UntrustedTask] FAILED: " + e.toString());
            System.exit(1);
        }
    }
}
