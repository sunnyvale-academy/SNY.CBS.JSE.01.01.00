import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Application demonstrating XML External Entity (XXE) Injection.
 */
public class XmlInclusionApp {
    public static void main(String[] args) throws IOException {
        System.out.println("--- Lab 3-5: The XML Gateway ---");

        // Create a dummy secret file on the server
        File secretFile = new File("server_secret.txt");
        try (FileWriter writer = new FileWriter(secretFile)) {
            writer.write("SECRET_ACCESS_KEY_12345");
        }

        // Malicious XML: Uses a SYSTEM entity to read the local file
        String maliciousXml = "<?xml version=\"1.0\"?>" +
                "<!DOCTYPE data [" +
                "  <!ENTITY xxe SYSTEM \"file://" + secretFile.getAbsolutePath() + "\">" +
                "]>" +
                "<user>" +
                "  <name>&xxe;</name>" +
                "</user>";

        VulnerableXmlParser vulnerable = new VulnerableXmlParser();
        SecureXmlParser secure = new SecureXmlParser();

        System.out.println("\n[VULNERABLE PARSING]");
        String vOutput = vulnerable.getUserName(maliciousXml);
        System.out.println("Extracted Name: " + vOutput);

        if (vOutput.contains("SECRET_ACCESS_KEY")) {
            System.out.println("!!! EXPLOIT SUCCESSFUL: The parser leaked the secret file content!");
        }

        System.out.println("\n[SECURE PARSING]");
        String sOutput = secure.getUserName(maliciousXml);
        System.out.println("Extracted Name: " + sOutput);

        if (!sOutput.contains("SECRET_ACCESS_KEY")) {
            System.out.println("SUCCESS: The parser protected the system from external entity inclusion.");
        }

        // Cleanup
        secretFile.delete();
    }
}
