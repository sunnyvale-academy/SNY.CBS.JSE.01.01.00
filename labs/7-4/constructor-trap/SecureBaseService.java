/**
 * SECURE CLASS: SecureBaseService
 * FIX: Prevents methods from being overridden if they are called in the
 * constructor.
 */
public class SecureBaseService {

    public SecureBaseService() {
        System.out.println("SecureBaseService: Starting constructor...");
        // FIX: calling a final method ensures that it cannot be intercepted by a
        // subclass.
        safeSetup();
        System.out.println("SecureBaseService: Constructor finished.");
    }

    /**
     * FIX: Making the method 'final' or 'private' prevents subclasses from
     * overriding it.
     */
    protected final void safeSetup() {
        System.out.println("SecureBaseService: Performing safe setup (final method).");
    }
}
