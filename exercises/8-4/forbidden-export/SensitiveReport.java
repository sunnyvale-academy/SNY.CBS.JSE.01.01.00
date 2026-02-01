import java.io.Serializable;

/**
 * VULNERABLE CLASS: SensitiveReport
 * Represents a report that should only be accessible by analysts.
 * VIOLATION: Enforcement is only in the constructor.
 */
public class SensitiveReport implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String title;
    private final String data;

    public SensitiveReport(String title, String data) {
        // ENFORCED ROLE
        AccessControl.checkRole("ANALYST");

        this.title = title;
        this.data = data;
    }

    @Override
    public String toString() {
        return "SensitiveReport[title=" + title + "]";
    }

    public String getData() {
        return data;
    }
}
