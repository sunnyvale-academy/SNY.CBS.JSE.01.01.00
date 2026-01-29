import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * Application demonstrating XML Injection vulnerability and its mitigation.
 */
public class XmlApp {

    public static String prettyPrint(String xml) throws Exception {
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(xml)));

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(
                "{http://xml.apache.org/xslt}indent-amount", "2"
        );

        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));

        return writer.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("--- Lab 3-1: Valid Formatting ---");

        // Malicious input designed to inject a 'superadmin' role
        String maliciousUsername = "guest</username><role>superadmin</role></user><user><username>attacker";
        String defaultRole = "guest";

        System.out.println("\n[Input] Attempting to create user with malicious username:");
        System.out.println("        " + maliciousUsername);

        // 1. Vulnerable Generation
        VulnerableXmlGenerator vGen = new VulnerableXmlGenerator();
        System.out.println("\n[VULNERABLE OUTPUT]");
        String vXml = vGen.createUserRecord(maliciousUsername, defaultRole);
        // System.out.println(vXml);
        System.out.println(prettyPrint(vXml));

        if (vXml.contains("<role>superadmin</role>")) {
            System.out.println("!!! EXPLOIT SUCCESSFUL: Malicious role injected!");
        }

        // 2. Secure Generation
        SecureXmlGenerator sGen = new SecureXmlGenerator();
        System.out.println("\n[SECURE OUTPUT]");
        String sXml = sGen.createUserRecord(maliciousUsername, defaultRole);
        System.out.println(prettyPrint(sXml));

        if (!sXml.contains("<role>superadmin</role>")) {
            System.out.println("SUCCESS: Malicious input was safely escaped.");
        }
    }
}
