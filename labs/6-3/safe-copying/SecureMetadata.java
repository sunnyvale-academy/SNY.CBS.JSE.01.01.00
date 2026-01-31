import java.util.Date;
import java.util.Arrays;

/**
 * SECURE CLASS: SecureMetadata
 * Fixes:
 * - Performs defensive copying of the array.
 * - Performs safe copying of the Date (converting to trusted type).
 */
public final class SecureMetadata {
    private final Date timestamp;
    private final String[] tags;

    public SecureMetadata(Date timestamp, String[] tags) {
        // FIX 1: Defensive copy of the array using a trusted method.
        this.tags = tags.clone(); // For arrays, clone() is safe because they can't be subclassed.
        // Better yet: this.tags = Arrays.copyOf(tags, tags.length);

        // FIX 2: Defensive copy of subclassable Date.
        // Instead of calling timestamp.clone(), we create a NEW instance
        // of the TRUSTED java.util.Date class using data from the input.
        this.timestamp = new Date(timestamp.getTime());
    }

    public void showMetadata() {
        System.out.println("Timestamp: " + timestamp);
        System.out.println("Tags: " + Arrays.toString(tags));
    }
}
