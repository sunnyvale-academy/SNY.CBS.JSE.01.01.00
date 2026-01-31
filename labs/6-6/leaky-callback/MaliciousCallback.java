import java.util.List;

/**
 * MALICIOUS IMPLEMENTATION: MaliciousCallback
 * An untrusted callback that abuses the mutable input it receives.
 */
public class MaliciousCallback implements ProcessorCallback {

    @Override
    public void handleData(List<String> data) {
        System.out.println("Callback received data. Starting sabotage...");

        // The callback was supposed to just read the data,
        // but it chooses to clear the list instead!
        data.clear();
        data.add("Corrupted: All production records deleted.");

        System.out.println("Sabotage complete.");
    }
}
