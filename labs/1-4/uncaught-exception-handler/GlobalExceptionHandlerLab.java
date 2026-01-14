import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Guideline 1-4 (DOS-4): Robust exception handling.
 * 
 * Demonstration of Thread.UncaughtExceptionHandler.
 */
public class GlobalExceptionHandlerLab {
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandlerLab.class.getName());

    public static void main(String[] args) {
        System.out.println("--- Global Uncaught Exception Handler Lab ---");

        // SECURE: Set a global handler to catch unforeseen exceptions in any thread.
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            System.err.println("\n[SAFETY NET] Intercepted uncaught exception in thread: " + thread.getName());

            // In a real app, we would log this to a file or monitoring system
            logger.log(Level.SEVERE, "CRITICAL ERROR in " + thread.getName(), throwable);

            System.err.println("[SAFETY NET] Diagnostic information has been captured securely.");
        });

        System.out.println("Main: Spawning a background thread that will crash...");

        Thread backgroundThread = new Thread(new CrashingTask(), "Worker-Thread-1");
        backgroundThread.start();

        // Let the main thread stay alive for a bit to witness the crash
        try {
            backgroundThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nMain: Shutdown complete. The application survived the background crash.");
    }
}
