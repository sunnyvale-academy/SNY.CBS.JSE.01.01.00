import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SECURE CLASS: SecureDataProcessor
 * Protects its internal state before passing it to untrusted code.
 */
public class SecureDataProcessor {
    private final List<String> results = new ArrayList<>();

    public SecureDataProcessor() {
        results.add("Record 1: Valid");
        results.add("Record 2: Valid");
        results.add("Record 3: Valid");
    }

    /**
     * FIX: Treat input to callback as OUTPUT.
     * We wrap the internal list in an unmodifiable view before passing it out.
     */
    public void process(ProcessorCallback callback) {
        System.out.println("Processing data (SECURE) and notifying callback...");

        // GUIDELINE 6-6 / MUTABLE-6:
        // Treat passing 'results' as output. Creating an unmodifiable view
        // ensures the internal list cannot be changed by the callback.
        callback.handleData(Collections.unmodifiableList(results));
    }

    public void printResultsSummary() {
        System.out.println("Internal Results State: " + results);
    }
}
