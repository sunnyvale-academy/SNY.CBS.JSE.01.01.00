/**
 * Application demonstrating XML Injection vulnerability and its mitigation.
 */
public class XmlApp {
    public static void main(String[] args) throws Exception {
        System.out.println("--- Lab 3-1: Valid Formatting ---");

        // Malicious input designed to inject a 'superadmin' role
        String maliciousUsername = "guest</username><role>superadmin</role><username>attacker";
        String defaultRole = "guest";

        System.out.println("\n[Input] Attempting to create user with malicious username:");
        System.out.println("        " + maliciousUsername);

        // 1. Vulnerable Generation
        VulnerableXmlGenerator vGen = new VulnerableXmlGenerator();
        System.out.println("\n[VULNERABLE OUTPUT]");
        String vXml = vGen.createUserRecord(maliciousUsername, defaultRole);
        System.out.println(vXml);

        if (vXml.contains("<role>superadmin</role>")) {
            System.out.println("!!! EXPLOIT SUCCESSFUL: Malicious role injected!");
        }

        // 2. Secure Generation
        SecureXmlGenerator sGen = new SecureXmlGenerator();
        System.out.println("\n[SECURE OUTPUT]");
        String sXml = sGen.createUserRecord(maliciousUsername, defaultRole);
        System.out.println(sXml);

        if (!sXml.contains("<role>superadmin</role>")) {
            System.out.println("SUCCESS: Malicious input was safely escaped.");
        }
    }
}
