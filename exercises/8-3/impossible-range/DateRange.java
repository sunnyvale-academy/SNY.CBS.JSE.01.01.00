import java.io.Serializable;

/**
 * VULNERABLE CLASS: DateRange
 * Represents a duration between two points in time.
 * VIOLATION: Does not implement readObject to re-validate invariants
 * after deserialization.
 */
public class DateRange implements Serializable {
    private static final long serialVersionUID = 1L;

    private final long start;
    private final long end;

    public DateRange(long start, long end) {
        // CONSTRUCTOR VALIDATION
        if (start > end) {
            throw new IllegalArgumentException("Start must be before end");
        }
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "DateRange [start=" + start + ", end=" + end + "]";
    }
}
