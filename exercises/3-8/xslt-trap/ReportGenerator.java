import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Exercise 3-8: Vulnerable Report Generator.
 * Processes XML using a user-provided XSLT stylesheet.
 */
public class ReportGenerator {

    public String generateReport(String xmlData, String xsltData) {
        System.out.println("[Vulnerable] Generating report using provided XSLT...");

        try {
            // VULNERABILITY: Default TransformerFactory often allows Java extensions.
            // Guideline 3-8: Take care interpreting untrusted code (XSLT).
            TransformerFactory factory = TransformerFactory.newInstance();

            Source xsltSource = new StreamSource(new StringReader(xsltData));
            Transformer transformer = factory.newTransformer(xsltSource);

            Source xmlSource = new StreamSource(new StringReader(xmlData));
            StringWriter writer = new StringWriter();

            // This call triggers the XSLT transformation, including any malicious
            // extensions.
            transformer.transform(xmlSource, new StreamResult(writer));

            return writer.toString();

        } catch (Exception e) {
            System.out.println("   -> Error during transformation: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
