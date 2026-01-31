import java.util.Date;

/**
 * VULNERABLE CLASS: SystemInfo
 * Defines system-level "constants".
 * VIOLATION: RELEASE_DATE is a final field, but it refers to a mutable Date
 * object.
 */
public class SystemInfo {
    // Guideline 6-10 Violation: This "constant" is mutable!
    // java.util.Date has mutator methods like setTime(), setYear(), etc.
    @SuppressWarnings("deprecation")
    public static final Date RELEASE_DATE = new Date(126, 0, 1); // Jan 1, 2026

    public static void display() {
        System.out.println("System Release Date: " + RELEASE_DATE);
    }
}
