import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A service that loads application configuration.
 * VULNERABILITY: Propagates FileNotFoundException directly, leaking internal paths.
 */
public class VulnerableFileLoader {

    public void loadConfig(String fileName) throws FileNotFoundException {
        // Simulating a load from an internal system path
        String internalPath = "/opt/apps/internal/config/" + fileName;
        File file = new File(internalPath);
        
        System.out.println("[DEBUG] Attempting to load: " + internalPath);
        
        // This will throw FileNotFoundException if the file doesn't exist.
        // The exception message will contain the full internalPath.
        FileInputStream fis = new FileInputStream(file);
    }
}
