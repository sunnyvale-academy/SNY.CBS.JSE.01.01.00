import java.util.ArrayList;
import java.util.List;

/**
 * VULNERABLE CLASS: DataProcessor
 * Manages an internal list of processed results.
 * VIOLATION: Passes its internal mutable list directly to untrusted callbacks.
 */
public class DataProcessor {
    private final List<String> results = new ArrayList<>();

    public DataProcessor() {
        results.add("Record 1: Valid");
        results.add("Record 2: Valid");
        results.add("Record 3: Valid");
    }

    /**
     * Processes data and reports results to a callback.
     * VULNERABILITY: The internal 'results' list is leaked to the callback.
     */
    public void process(ProcessorCallback callback) {
        System.out.println("Processing data and notifying callback...");

        // Passing the internal mutable list directly!
        // The callback is treated as an input consumer, but Guideline 6-6
        // warns that this must be treated as OUTPUT.
        callback.handleData(results);
    }

    public void printResultsSummary() {
        System.out.println("Internal Results State: " + results);
        if (results.isEmpty()) {
            System.out.println("ALERT: Internal state has been CLEARED!");
        } else if (results.size() != 3) {
            System.out.println("ALERT: Internal state has been TAMPERED with!");
        }
    }
}
