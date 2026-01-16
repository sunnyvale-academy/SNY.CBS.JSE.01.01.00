import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.StringWriter;

/**
 * A secure XML generator using DOM libraries.
 * SECURE: Handles escaping of special characters automatically.
 */
public class SecureXmlGenerator {

    public String createUserRecord(String username, String role) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // Root element
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("user");
        doc.appendChild(rootElement);

        // Username element
        Element userElement = doc.createElement("username");
        userElement.appendChild(doc.createTextNode(username));
        rootElement.appendChild(userElement);

        // Role element
        Element roleElement = doc.createElement("role");
        roleElement.appendChild(doc.createTextNode(role));
        rootElement.appendChild(roleElement);

        // Convert the document to a string
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));

        return writer.getBuffer().toString();
    }
}
