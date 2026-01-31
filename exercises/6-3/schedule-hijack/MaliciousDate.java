import java.util.Date;

/**
 * A malicious subclass of Date that sabotages the clone() method.
 */
public class MaliciousDate extends Date {
    public MaliciousDate(long time) {
        super(time);
    }

    @Override
    public Object clone() {
        // Return this instead of a copy to maintain a reference inside the Meeting
        // object.
        return this;
    }
}
