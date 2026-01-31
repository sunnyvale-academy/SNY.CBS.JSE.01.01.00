import java.util.Collections;
import java.util.List;

/**
 * SECURE CLASS: SecureSystemConfig
 * Protects global state by making fields final and using immutable collections.
 */
public class SecureSystemConfig {
    // FIX: Public static fields must be final
    public static final String ENVIRONMENT = "PRODUCTION";

    // FIX: Use an unmodifiable collection instead of an array
    private static final List<String> ADMIN_IPS_INTERNAL = List.of("127.0.0.1");
    public static final List<String> ADMIN_IPS = Collections.unmodifiableList(ADMIN_IPS_INTERNAL);

    public static void display() {
        System.out.println("--- Secure System Configuration ---");
        System.out.println("Environment: " + ENVIRONMENT);
        System.out.println("Admin IPs: " + ADMIN_IPS);
    }
}
