import java.util.Date;
import java.util.Arrays;

/**
 * VULNERABLE CLASS: MutableMetadata
 * Demonstrates violations of Guideline 6-3 / MUTABLE-3.
 */
public class MutableMetadata {
    private final Date timestamp;
    private final String[] tags;

    public MutableMetadata(Date timestamp, String[] tags) {
        // VULNERABILITY 1: Direct assignment of mutable array.
        // The caller can modify the 'tags' array after construction.
        this.tags = tags;

        // VULNERABILITY 2: Using clone() on a subclassable type (Date).
        // If 'timestamp' is a malicious subclass of Date, it can
        // override clone() to return a reference to itself.
        this.timestamp = (Date) timestamp.clone();
    }

    public void showMetadata() {
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Tags: " + Arrays.toString(tags));
    }

    // Note: To be fully vulnerable to 6-2 as well, these would have getters
    // that return internal references. But here we focus on construction (6-3).
}
