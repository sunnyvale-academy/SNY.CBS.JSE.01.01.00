/**
 * VULNERABLE CLASS: Logger
 * A base class for logging utilities.
 * VIOLATION: Calls an overridable method (initialize()) from its constructor.
 */
public class Logger {

    public Logger() {
        System.out.println("Logger: Starting base class construction...");
        // Guideline 7-4 Violation: Calling an overridable method from the constructor.
        initialize();
        System.out.println("Logger: Base class construction finished.");
    }

    /**
     * Subclasses are expected to override this to perform custom initialization.
     */
    protected void initialize() {
        System.out.println("Logger: Performing default initialization.");
    }
}
