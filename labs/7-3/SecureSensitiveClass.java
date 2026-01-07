/**
 * SecureSensitiveClass.java
 * 
 * This class demonstrates how to defend against partial initialization
 * attacks (Guideline 7-3) by making the class final.
 */
public final class SecureSensitiveClass {
    // SECURE: Making the class 'final' prevents any subclassing.
    // Since there are no subclasses, no one can override finalize() to
    // perform a finalizer attack.

    private boolean initialized = false;

    public SecureSensitiveClass(String secret) {
        if (secret == null || secret.isEmpty()) {
            throw new IllegalArgumentException("Secret is required!");
        }
        this.initialized = true;
        System.out.println("SecureSensitiveClass initialized successfully.");
    }

    public void doSomethingSensitive() {
        if (!initialized) {
            throw new IllegalStateException("Object not initialized!");
        }
        System.out.println("Performing sensitive action securely...");
    }
}
