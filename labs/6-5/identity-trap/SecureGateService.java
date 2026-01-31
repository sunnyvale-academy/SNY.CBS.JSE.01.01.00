/**
 * SECURE SERVICE: SecureGateService
 * Uses enums for access control, which makes identity comparison (==) safe.
 */
public class SecureGateService {

    public void enterVault(SecureAccessLevel level) {
        System.out.println("Checking access for: " + level);

        // SECURE: Identity comparison (==) is safe for enums.
        // It's impossible to create a 'forged' SecureAccessLevel.GUEST object.
        if (level == SecureAccessLevel.GUEST) {
            System.out.println("ACCESS DENIED: Guests are not allowed in the vault.");
        } else {
            System.out.println("ACCESS GRANTED: Welcome to the vault!");
        }
    }
}
