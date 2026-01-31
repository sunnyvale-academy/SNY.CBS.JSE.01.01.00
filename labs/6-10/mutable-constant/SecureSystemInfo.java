import java.time.LocalDate;

/**
 * SECURE CLASS: SecureSystemInfo
 * FIX: Replaces mutable Date with immutable LocalDate.
 */
public class SecureSystemInfo {
    // SECURE: LocalDate is an immutable type.
    // It has no mutator methods; all operations return a new instance.
    public static final LocalDate RELEASE_DATE = LocalDate.of(2026, 1, 1);

    public static void display() {
        System.out.println("Secure System Release Date: " + RELEASE_DATE);
    }
}
