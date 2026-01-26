package com.security.logger;

/**
 * Superclass providing basic logging functionality.
 */
public class BaseLogger {
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }

    /**
     * New method added in a library update (Performance optimization).
     * It iterates over messages and logs them.
     */
    public void logMultiple(String... messages) {
        for (String msg : messages) {
            // Note: This implementation does NOT call log(String)
            System.out.println("[LOG-M] " + msg);
        }
    }
}
