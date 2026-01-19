import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.StringReader;

/**
 * An XML parser utility that uses default settings.
 * VULNERABILITY: XML External Entity (XXE) Injection (Guideline 3-5).
 */
public class VulnerableXmlParser {

    public String getUserName(String xmlContent) {
        try {
            // VULNERABILITY: Default factory settings often allow DTD and External
            // Entities.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));
            return doc.getElementsByTagName("name").item(0).getTextContent();
        } catch (Exception e) {
            return "Error parsing XML: " + e.getMessage();
        }
    }
}
