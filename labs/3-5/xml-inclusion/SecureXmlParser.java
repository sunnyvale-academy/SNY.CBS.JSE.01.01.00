import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.StringReader;

/**
 * A secure XML parser that restricts XML inclusion.
 * SECURE: Disables DTDs and external entities (Guideline 3-5).
 */
public class SecureXmlParser {

    public String getUserName(String xmlContent) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // SECURE: Explicitly disable DTDs to prevent XXE.
            // This is the most robust way to prevent the vulnerability.
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

            // Optional: Disable external entities if DTDs are still needed for some reason
            factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlContent)));
            return doc.getElementsByTagName("name").item(0).getTextContent();
        } catch (Exception e) {
            return "Security Protected Error: " + e.getMessage();
        }
    }
}
