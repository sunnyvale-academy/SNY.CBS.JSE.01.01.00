import java.util.concurrent.locks.ReentrantLock;

public class VulnerableLockHandler {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    public void performAction(boolean shouldFail) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");

            // Critical section
            counter++;

            if (shouldFail) {
                System.out.println(Thread.currentThread().getName() + " is about to throw an exception...");
                throw new RuntimeException("Simulated failure while holding lock");
            }

            System.out.println(Thread.currentThread().getName() + " completed successfully.");
        } finally {
            // VULNERABILITY: In a real-world mistake, one might forget to unlock
            // OR incorrectly place the unlock call.
            // For this exercise, we simulate the mistake of NOT having this in a finally
            // block
            // that ALWAYS executes.

            // To make it an exercise, we'll intentionally leave the unlock()
            // vulnerable to being skipped if an exception occurs above if handled poorly.
            // lock.unlock();
        }

        // If we put unlock here, outside the finally, it's skipped on exception.
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " released the lock.");
    }

    public int getCounter() {
        return counter;
    }
}
