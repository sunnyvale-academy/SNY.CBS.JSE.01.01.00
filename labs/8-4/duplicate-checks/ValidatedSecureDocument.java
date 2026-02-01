import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.IOException;

/**
 * SECURE CLASS: ValidatedSecureDocument
 * FIX: Duplicate the security check in readObject to prevent
 * unauthorized instantiation via deserialization.
 */
public class ValidatedSecureDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String title;
    private final String content;

    public ValidatedSecureDocument(String title, String content) {
        SecurityContext.checkPermission();
        this.title = title;
        this.content = content;
    }

    /**
     * FIX: Guideline 8-4 / SERIAL-4
     * Security checks must be duplicated here to prevent
     * unauthorized serialization.
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        System.out.println("writeObject: Duplicating security check...");
        SecurityContext.checkPermission();
        out.defaultWriteObject();
    }

    /**
     * FIX: Guideline 8-4 / SERIAL-4
     * Security checks must be duplicated here to prevent
     * unauthorized deserialization.
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        // RE-VALIDATE PERMISSIONS
        System.out.println("readObject: Duplicating security check...");
        SecurityContext.checkPermission();
    }

    @Override
    public String toString() {
        return "ValidatedSecureDocument[title=" + title + "]";
    }

    public String getContent() {
        return content;
    }
}
