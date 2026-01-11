/**
 * Guideline 1-3 (DOS-3): Resource limit checks should not suffer from integer
 * overflow.
 * 
 * This class manages a pool of file handles with a global limit.
 */
public class VulnerableFileManager {
    private static final int MAX_TOTAL_FILES = 5000;

    /**
     * Requests a batch of files for a group of users.
     * VULNERABLE: The total files calculation can overflow.
     */
    public boolean requestFiles(int numUsers, int filesPerUser) {
        System.out.println("Processing request for " + numUsers + " users, each needing " + filesPerUser + " files.");

        // VULNERABLE: numUsers * filesPerUser can overflow to a small/negative number
        if (numUsers * filesPerUser > MAX_TOTAL_FILES) {
            System.err.println("[REJECTED] Total request exceeds system limit of " + MAX_TOTAL_FILES);
            return false;
        }

        System.out.println("[APPROVED] " + (numUsers * filesPerUser) + " files allocated.");
        return true;
    }
}
