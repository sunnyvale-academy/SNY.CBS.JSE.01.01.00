/**
 * VULNERABLE CLASS: SensitiveManager
 * Manages access to highly sensitive configuration.
 * VIOLATION: Non-final class that does not defend against clone().
 */
public class SensitiveManager {
    private String configData;

    public SensitiveManager(String data) {
        // SECURITY CHECK: Only authorized users can create managers.
        if (!AuthService.isAuthorized()) {
            System.out.println("SensitiveManager: ACCESS DENIED! Constructor blocked.");
            throw new SecurityException("Unauthorized manager creation");
        }
        this.configData = data;
        System.out.println("SensitiveManager: Instance created with data: " + configData);
    }

    public void showConfig() {
        System.out.println("SensitiveManager: Current config: " + configData);
    }
}
