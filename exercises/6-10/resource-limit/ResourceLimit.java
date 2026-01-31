/**
 * VULNERABLE CLASS: ResourceLimit
 * Defines system limits.
 * VIOLATION: MAX_REQUESTS is a final field, but it refers to a mutable Limit
 * object.
 */
public class ResourceLimit {

    // CUSTOM MUTABLE CLASS
    public static class Limit {
        private int value;

        public Limit(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // Guideline 6-10 Violation: This "constant" points to a mutable object!
    public static final Limit MAX_REQUESTS = new Limit(5);

    public static boolean isWithinLimit(int count) {
        return count <= MAX_REQUESTS.getValue();
    }
}
