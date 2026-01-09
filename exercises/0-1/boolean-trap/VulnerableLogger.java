/**
 * VULNERABILITY: The Boolean Parameter Trap.
 * 
 * This logger has a single log method that uses a boolean to decide if
 * the information is public or sensitive. If a developer forgets the flag
 * or gets it reversed, sensitive information can be logged to public channels.
 */
public class VulnerableLogger {

    /**
     * Logs a message.
     * 
     * @param msg      The message to log
     * @param isPublic True if the message can be shown publicly, false if it's
     *                 sensitive
     */
    public void log(String msg, boolean isPublic) {
        if (isPublic) {
            System.out.println("[PUBLIC LOG]: " + msg);
        } else {
            System.out.println("[SENSITIVE INTERNAL LOG]: " + msg);
        }
    }
}
