/**
 * Application demonstrating Contextual HTML Injection.
 */
public class HtmlApp {
    public static void main(String[] args) {
        System.out.println("--- Lab 3-3: The Contextual Leak ---");

        // Malicious input: Uses " to break out of the value attribute
        String maliciousInput = "John\" onmouseover=\"alert('XSS')\" style=\"color:red;";

        VulnerableHtmlGenerator vulnerable = new VulnerableHtmlGenerator();
        SecureHtmlGenerator secure = new SecureHtmlGenerator();

        System.out.println("\n[VULNERABLE GENERATION]");
        System.out.println("Input: " + maliciousInput);
        String vOutput = vulnerable.generateInput(maliciousInput);
        System.out.println("Resulting HTML: " + vOutput);

        if (vOutput.contains("onmouseover=")) {
            System.out.println("!!! EXPLOIT SUCCESSFUL: Malicious attribute injected into the tag!");
        }

        System.out.println("\n[SECURE GENERATION]");
        System.out.println("Input: " + maliciousInput);
        String sOutput = secure.generateInput(maliciousInput);
        System.out.println("Resulting HTML: " + sOutput);

        if (sOutput.contains("&quot;onmouseover=&quot;")) {
            System.out.println("SUCCESS: Malicious characters were safely escaped for the attribute context.");
        }
    }
}
