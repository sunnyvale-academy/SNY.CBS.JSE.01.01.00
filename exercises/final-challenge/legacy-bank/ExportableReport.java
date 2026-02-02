import java.io.*;

/**
 * VULNERABLE CLASS: ExportableReport
 * Guidelines:
 * - SERIAL-4 (8-4): Fails to duplicate security checks during deserialization.
 */
public class ExportableReport implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String content;

    public ExportableReport(String title, String content) {
        // Security check in constructor
        if (content.contains("CONFIDENTIAL") && !isAuthorized()) {
            throw new SecurityException("Unauthorized to create confidential report");
        }
        this.title = title;
        this.content = content;
    }

    private boolean isAuthorized() {
        // Simulate a check that might be true now but false later
        return true;
    }

    @Override
    public String toString() {
        return "ExportableReport[title=" + title + ", content=" + content + "]";
    }

    // Vulnerability: Missing readObject() that duplicates the security check.
}
