/**
 * VULNERABLE CLASS: SensitiveOperation
 * Guidelines:
 * - OBJ-3 (7-3): Vulnerable to finalizer attacks because it's not final
 * and does sensitive work in its constructor.
 */
public class SensitiveOperation {
    public SensitiveOperation() {
        if (!SecurityChecker.isAuthorized()) {
            throw new SecurityException("Unauthorized access!");
        }
        // Proceed with sensitive initialization...
    }

    public void execute() {
        System.out.println("Executing sensitive banking operation...");
    }
}

class SecurityChecker {
    public static boolean isAuthorized() {
        return false; // Always unauthorized for this demo
    }
}
