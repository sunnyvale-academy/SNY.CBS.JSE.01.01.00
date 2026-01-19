import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.StringReader;

/**
 * A document manager that parses system reports in XML format.
 * VULNERABILITY: XML External Entity (XXE) Injection (Guideline 3-5).
 */
public class VulnerableDocManager {

    public String parseReport(String xmlContent) {
        try {
            // VULNERABILITY: Default settings allow DTDs and External Entities.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));

            // Extract a field that might contain the injected data
            return "Report Status: " + doc.getElementsByTagName("status").item(0).getTextContent();
        } catch (Exception e) {
            return "Error parsing report: " + e.getMessage();
        }
    }
}
