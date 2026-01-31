import java.util.Date;

/**
 * A malicious subclass of Date that sabotages the clone() method.
 * This demonstrates why clone() is unsafe for defensive copying of
 * subclassable types from untrusted sources.
 */
public class MaliciousDate extends Date {

    public MaliciousDate(long time) {
        super(time);
    }

    @Override
    public Object clone() {
        // Instead of returning a copy, it returns 'this'.
        // This bypasses the caller's attempt at defensive copying.
        return this;
    }

    // An attacker might also override other methods to track usage
    // or change behavior at runtime.
}
