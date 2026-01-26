package com.security.logger;

/**
 * Subclass that attempts to redact sensitive information (passwords).
 * 
 * It overrides log(String) but fails to account for logMultiple()
 * which was added to the superclass later.
 */
public class SecureRedactingLogger extends BaseLogger {

    @Override
    public void log(String message) {
        // Redact "password=..." if present
        String redacted = redact(message);
        super.log(redacted);
    }

    private String redact(String message) {
        if (message.contains("password=")) {
            return message.replaceAll("password=[^\\s]+", "password=********");
        }
        return message;
    }

    // VULNERABILITY: logMultiple() is NOT overridden,
    // allowing unredacted logs to bypass this policy!
}
