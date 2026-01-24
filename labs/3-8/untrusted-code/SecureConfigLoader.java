import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Lab 3-8: Secure Configuration Loader.
 * Uses java.util.Properties for safe key-value pairs.
 */
public class SecureConfigLoader {

    public Properties loadConfig(String configData) {
        System.out.println("[Secure] Loading config using java.util.Properties...");

        Properties props = new Properties();
        try (InputStream is = new ByteArrayInputStream(configData.getBytes())) {
            // SECURE: Properties.load() only parses key=value pairs.
            // It does NOT interpret tokens as code or method calls.
            props.load(is);
            return props;
        } catch (Exception e) {
            System.out.println("   -> Error loading config: " + e.getMessage());
            return null;
        }
    }
}
