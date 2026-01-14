/**
 * A task that deliberately triggers a RuntimeException.
 */
public class CrashingTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Worker: Working on a sensitive task...");

        // Simulate a sudden, unexpected logical error (e.g. malformed data)
        throw new RuntimeException("Unexpected corrupted state in Worker-Thread!");
    }
}
