/**
 * VulnerableSensitiveClass.java
 * 
 * This class is vulnerable to a "finalizer attack" (Guideline 7-3).
 * If a constructor throws an exception, the object is partially initialized
 * but still exists in memory. A malicious subclass can override finalize()
 * to capture a reference to this partially initialized object.
 */
public class VulnerableSensitiveClass {
    private boolean initialized = false;

    public VulnerableSensitiveClass(String secret) {
        // Validation: Security check that can fail
        if (secret == null || secret.isEmpty()) {
            throw new IllegalArgumentException("Secret is required for initialization!");
        }

        // ... imagine some highly sensitive setup happens here ...
        this.initialized = true;
        System.out.println("VulnerableSensitiveClass initialized successfully.");
    }

    public void doSomethingSensitive() {
        if (!initialized) {
            System.err.println("ALERT: Sensitive method called on PARTIALLY INITIALIZED object!");
        }
        System.out.println("Performing sensitive action with internal secrets...");
    }
}
