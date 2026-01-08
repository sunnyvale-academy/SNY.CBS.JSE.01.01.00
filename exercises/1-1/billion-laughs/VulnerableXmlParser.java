import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.InputStream;
import javax.xml.XMLConstants;

public class VulnerableXmlParser {
    /**
     * Parses an XML input stream into a Document.
     * VULNERABILITY: Uses default DocumentBuilderFactory settings,
     * which allow Document Type Definitions (DTDs) and recursive entity expansion.
     * 
     * NOTE: Modern JDKs include a safety net (e.g., entityExpansionLimit =
     * 100,000).
     * This parser is "vulnerable" because it doesn't establish a trust boundary
     * by disabling DTDs, relying instead on generic JVM-level resource limits.
     */
    public Document parse(InputStream xmlStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // VULNERABILITY: Even when enabling "secure processing", relaxing limits
        // to very high values makes the parser vulnerable to Billion Laughs.
        try {
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setAttribute("http://www.oracle.com/xml/jaxp/properties/entityExpansionLimit", 10000000);
            factory.setAttribute("http://www.oracle.com/xml/jaxp/properties/totalEntitySizeLimit", 10000000);
        } catch (Exception e) {
            // Fallback for older JDKs
            System.setProperty("jdk.xml.entityExpansionLimit", "0");
        }

        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(xmlStream);
    }
}
