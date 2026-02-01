/**
 * POC APP: LoggingApp
 * Demonstrates how calling overridable methods in constructors leads to broken
 * logic.
 */
public class LoggingApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 7-4: The Broken Logger ---");

        System.out.println("\n[1] Creating a CustomLogger(\"INFO\")...");
        CustomLogger logger = new CustomLogger("INFO");

        System.out.println("\n[2] Sending a log message...");
        logger.log("This is a test message.");

        System.out.println("\nALERT: Subclass initialization was bypassed during base construction! (Vulnerable)");
    }
}
