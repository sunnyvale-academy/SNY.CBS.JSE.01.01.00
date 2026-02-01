/**
 * MALICIOUS/VULNERABLE SUBCLASS: MaliciousService
 * Demonstrates the danger of overridable methods in constructors.
 */
public class MaliciousService extends BaseService {
    private final String sensitiveToken;

    public MaliciousService(String token) {
        // The BaseService constructor is called implicitly here (super())
        this.sensitiveToken = token;
        System.out.println("MaliciousService: Constructor finished. Token set to: " + sensitiveToken);
    }

    /**
     * Overrides the method called by BaseService constructor.
     * At the time this is called, this.sensitiveToken has NOT been initialized yet!
     */
    @Override
    protected void setup() {
        System.out.println("MaliciousService: setup() intercepted!");
        System.out.println("MaliciousService: Attempting to use sensitiveToken...");

        if (sensitiveToken == null) {
            System.out.println("OBSERVATION: sensitiveToken is NULL! (Uninitialized state accessed)");
        } else {
            System.out.println("OBSERVATION: sensitiveToken is: " + sensitiveToken);
        }
    }

    public void doWork() {
        System.out.println("MaliciousService: Doing work with token: " + sensitiveToken);
    }
}
