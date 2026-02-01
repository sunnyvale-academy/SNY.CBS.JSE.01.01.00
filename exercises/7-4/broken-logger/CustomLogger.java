/**
 * BROKEN SUBCLASS: CustomLogger
 * A logger that uses a specific log level.
 */
public class CustomLogger extends Logger {
    private final String logLevel;

    public CustomLogger(String level) {
        // Step 1: Base class constructor (Logger()) is called first.
        // Step 2: This field is initialized.
        this.logLevel = level;
        System.out.println("CustomLogger: Constructor set logLevel to: " + logLevel);
    }

    /**
     * Overrides the initialize() method called by the base class constructor.
     * CRITICAL BUG: This method runs BEFORE the CustomLogger constructor
     * initializes logLevel.
     */
    @Override
    protected void initialize() {
        System.out.println("CustomLogger: initialize() called by Base class...");

        // This will print "null" because the logLevel field hasn't been set yet!
        System.out.println("CustomLogger: Attempting to use logLevel: " + logLevel);

        if (logLevel == null) {
            System.out.println("ERROR: logLevel is NULL during initialization! Subclass state is missing.");
        }
    }

    public void log(String message) {
        System.out.println("[" + logLevel + "] " + message);
    }
}
