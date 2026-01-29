import java.time.Instant;

/**
 * A secure, immutable version of the period class.
 * It uses java.time.Instant, which is immutable.
 */
public final class SecurePeriod {
    private final Instant start;
    private final Instant end;

    /**
     * @param start the beginning of the period
     * @param end   the end of the period; must not precede start
     * @throws IllegalArgumentException if start is after end
     * @throws NullPointerException     if start or end is null
     */
    public SecurePeriod(Instant start, Instant end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException(start + " after " + end);
        }
        this.start = start;
        this.end = end;
    }

    public Instant start() {
        return start;
    }

    public Instant end() {
        return end;
    }

    @Override
    public String toString() {
        return start + " - " + end;
    }
}
