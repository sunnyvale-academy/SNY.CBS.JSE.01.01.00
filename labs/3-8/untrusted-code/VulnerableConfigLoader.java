import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Lab 3-8: Vulnerable Configuration Loader.
 * Uses XMLDecoder to parse "configuration" data.
 */
public class VulnerableConfigLoader {

    public Object loadConfig(String xmlData) {
        System.out.println("[Vulnerable] Loading config using XMLDecoder...");

        // VULNERABILITY: XMLDecoder can be used to execute arbitrary code
        // if the input XML contains malicious <void method="..."> tags.
        try (InputStream is = new ByteArrayInputStream(xmlData.getBytes());
                XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(is))) {

            // This call triggers the interpretation of the XML
            return decoder.readObject();

        } catch (Exception e) {
            System.out.println("   -> Error loading config: " + e.getMessage());
            return null;
        }
    }
}
