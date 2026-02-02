/**
 * VULNERABLE CLASS: BaseGateway
 * Guidelines:
 * - OBJ-4 (7-4): Constructor calls an overridable method.
 */
abstract class BaseGateway {
    public BaseGateway() {
        // Vulnerability: Calling overridable method in constructor
        init();
        System.out.println("Gateway initialized.");
    }

    public abstract void init();
}

/**
 * Malicious subclass exploiting the constructor trap.
 */
class InsecureGateway extends BaseGateway {
    private String secretToken;

    public InsecureGateway() {
        super();
        this.secretToken = "PROD_TOKEN_12345";
    }

    @Override
    public void init() {
        // In a real attack, this could access fields of the subclass
        // before they are initialized, or leak 'this' to an external object.
        System.out.println("InsecureGateway: init() called. Token is: " + secretToken);
    }
}
