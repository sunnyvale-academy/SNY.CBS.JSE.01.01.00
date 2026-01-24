import java.util.Properties;

/**
 * Driver app for Lab 3-8: Untrusted Code Interpretation.
 */
public class ConfigApp {
    public static void main(String[] args) {
        System.out.println("--- Lab 3-8: Untrusted Code Interpretation ---");

        VulnerableConfigLoader vulnerable = new VulnerableConfigLoader();
        SecureConfigLoader secure = new SecureConfigLoader();

        // 1. A normal-looking XML config for XMLDecoder
        String normalXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<java version=\"1.8.0\" class=\"java.beans.XMLDecoder\">\n" +
                " <string>AppTitle: SecureGallery</string>\n" +
                "</java>";

        // 2. A MALICIOUS XML payload that achieves RCE
        // It uses <void> tags to call ProcessBuilder(args).start()
        String maliciousXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<java version=\"1.8.0\" class=\"java.beans.XMLDecoder\">\n" +
                "  <object class=\"java.lang.ProcessBuilder\">\n" +
                "    <array class=\"java.lang.String\" length=\"3\">\n" +
                "      <void index=\"0\"><string>echo</string></void>\n" +
                "      <void index=\"1\"><string> !!! EXPLOIT SUCCESSFUL: RCE via XMLDecoder !!! </string></void>\n" +
                "      <void index=\"2\"><string> This command was executed by the JVM interpreter. </string></void>\n"
                +
                "    </array>\n" +
                "    <void method=\"inheritIO\"/>\n" +
                "    <void method=\"start\"/>\n" +
                "  </object>\n" +
                "</java>";

        System.out.println("\n[Action] Processing normal config...");
        vulnerable.loadConfig(normalXml);

        System.out.println("\n[Action] Processing MALICIOUS config (RCE Attempt)...");
        // Watch the console output carefully!
        vulnerable.loadConfig(maliciousXml);

        // 3. Secure approach
        System.out.println("\n[Action] Processing malicious input with Secure loader...");
        String propsData = "AppTitle=SecureGallery\n# The malicious payload below will be ignored/treated as raw text\n"
                + maliciousXml;
        Properties props = secure.loadConfig(propsData);
        if (props != null) {
            System.out.println("   -> Successfully loaded properties. AppTitle: " + props.getProperty("AppTitle"));
        }
    }
}
