/**
 * VULNERABLE CLASS: BaseService
 * Provides core service functionality.
 * VIOLATION: Calls an overridable method (setup()) from its constructor.
 */
public class BaseService {

    public BaseService() {
        System.out.println("BaseService: Starting constructor...");
        // Guideline 7-4 Violation: Calling an overridable method from the constructor.
        // If a subclass overrides this method, it will be executed before
        // the subclass constructor has finished (or even started) its own
        // initialization.
        setup();
        System.out.println("BaseService: Constructor finished.");
    }

    /**
     * This method is intended to be overridden by subclasses to provide custom
     * setup logic.
     * However, because it's called from the Base constructor, it's dangerous.
     */
    protected void setup() {
        System.out.println("BaseService: Performing default setup.");
    }
}
