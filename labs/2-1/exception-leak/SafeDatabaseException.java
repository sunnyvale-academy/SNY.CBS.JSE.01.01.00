/**
 * SafeDatabaseException.java
 * 
 * This class demonstrates a robust way to implement Guideline 2-1.
 * By extending Exception and overriding getMessage() and toString(),
 * we ensure that even if the exception is accidentally logged or
 * displayed, the sensitive details are hidden from the user.
 */
public class SafeDatabaseException extends Exception {
    private final String sensitiveDetails;

    /**
     * @param safeMessage      The message that is safe to show to users/logs.
     * @param sensitiveDetails The details that should ONLY be used for internal
     *                         logging.
     */
    public SafeDatabaseException(String safeMessage, String sensitiveDetails) {
        super(safeMessage);
        this.sensitiveDetails = sensitiveDetails;
    }

    /**
     * This method overrides the default behavior to ensure that only
     * the safe message is returned to callers.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    /**
     * We also override toString() because some logging frameworks use it,
     * and it often includes the message.
     */
    @Override
    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }

    /**
     * A special method to get the sensitive details for internal system logs.
     * This method should NOT be called by any user-facing code.
     */
    public String getSensitiveDetails() {
        return sensitiveDetails;
    }
}
