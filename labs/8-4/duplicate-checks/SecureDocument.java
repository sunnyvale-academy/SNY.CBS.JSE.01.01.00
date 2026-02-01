import java.io.Serializable;

/**
 * VULNERABLE CLASS: SecureDocument
 * Represents a document that should only be accessible with specific
 * permissions.
 * VIOLATION: The security check is ONLY in the constructor.
 * This check is bypassed during deserialization.
 */
public class SecureDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String title;
    private final String content;

    public SecureDocument(String title, String content) {
        // SECURITY CHECK
        SecurityContext.checkPermission();

        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "SecureDocument[title=" + title + "]";
    }

    public String getContent() {
        return content;
    }
}
