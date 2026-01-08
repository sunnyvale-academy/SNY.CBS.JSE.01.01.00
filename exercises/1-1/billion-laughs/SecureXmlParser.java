import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.InputStream;
import javax.xml.XMLConstants;

public class SecureXmlParser {
    /**
     * Parses an XML input stream into a Document securely.
     * SECURE: Disables DTDs to prevent entity expansion and external entity attacks
     * (XXE).
     */
    public Document parse(InputStream xmlStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // SECURE: Disable DTDs to prevent the "Billion Laughs" attack
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

        // SECURE: Also disable external entities as a best practice
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        // SECURE: Enable secure processing to limit entities and total attributes
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(xmlStream);
    }
}
