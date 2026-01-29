/**
 * An XML generator that uses insecure string concatenation.
 * VULNERABILITY: Susceptible to XML Injection.
 */
public class VulnerableXmlGenerator {

    public String createUserRecord(String username, String role) {
        // Simple string concatenation for XML generation
        // This fails to escape special characters like < and >
        return "<users><user>" +
                "<username>" + username + "</username>" +
                "<role>" + role + "</role>" +
                "</user></users>";
    }
}
