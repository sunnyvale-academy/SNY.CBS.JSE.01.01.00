/**
 * VULNERABLE SERVICE: GateService
 * Controls access to a secure vault.
 */
public class GateService {

    public void enterVault(AccessLevel level) {
        System.out.println("Checking access for: " + level.getName());

        // VIOLATION: Trusting identity equality (==) for a security check.
        // It assumes that any "GUEST" level MUST BE exactly the object constant defined
        // in AccessLevel.
        if (level == AccessLevel.GUEST) {
            System.out.println("ACCESS DENIED: Guests are not allowed in the vault.");
        } else {
            System.out.println("ACCESS GRANTED: Welcome to the vault!");
        }
    }
}
