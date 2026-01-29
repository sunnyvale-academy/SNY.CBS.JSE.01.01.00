import java.util.Date;

/**
 * A vulnerable class representing a time period.
 * It uses java.util.Date, which is mutable, making the class mutable even if fields are final.
 */
public final class MutablePeriod {
    private final Date start;
    private final Date end;

    /**
     * @param start the beginning of the period
     * @param end the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException if start or end is null
     */
    public MutablePeriod(Date start, Date end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
        this.start = start;
        this.end = end;
    }

    public Date start() {
        return start;
    }

    public Date end() {
        return end;
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }
}
