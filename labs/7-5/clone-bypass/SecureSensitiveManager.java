/**
 * SECURE CLASS: SecureSensitiveManager
 * FIX: Defends against cloning by defining a final clone method that throws an
 * exception.
 */
public class SecureSensitiveManager {
    private String configData;

    public SecureSensitiveManager(String data) {
        if (!AuthService.isAuthorized()) {
            throw new SecurityException("Unauthorized manager creation");
        }
        this.configData = data;
    }

    /**
     * FIX: To prevent cloning of non-final classes, define a final clone method
     * that throws CloneNotSupportedException.
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning is intentionally disabled for security");
    }

    public void showConfig() {
        System.out.println("SecureSensitiveManager: Config: " + configData);
    }
}
