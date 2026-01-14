import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Driver for Lab 1-4: Robust Exception Handling.
 */
public class ExceptionLab {

    public static void main(String[] args) {
        // Setup simple console logging for the lab
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.ALL);
        for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
            handler.setLevel(Level.ALL);
        }

        System.out.println("--- Starting Robust Exception Handling Lab ---\n");

        VulnerableExceptionService vulnerable = new VulnerableExceptionService();
        SecureExceptionService secure = new SecureExceptionService();

        System.out.println("Scenario 1: Sensitive Database Error");
        try {
            System.out.println("Vulnerable Response: " + vulnerable.getUserData("invalid"));
        } catch (Exception e) {
            System.out.println("Vulnerable Response (caught by driver): " + e.getMessage());
        }
        System.out.println("Secure Response:     " + secure.getUserData("invalid"));
        System.out.println();

        System.out.println("Scenario 2: Runtime Exception Handling");
        try {
            System.out.println("Vulnerable processing...");
            vulnerable.processTask();
        } catch (Exception e) {
            System.err.println("Vulnerable task crashed main thread: " + e.toString());
        }

        System.out.println("\nSecure processing...");
        secure.processTask();
        System.out.println("Secure service recovered and continued.");

        System.out.println("\n--- Lab Completed ---");
    }
}
