import java.util.Date;

public class MaliciousUser {
    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();

        // Initially create a valid period that allows access
        MutablePeriod period = new MutablePeriod(start, end);

        AccessController controller = new AccessController();

        System.out.println("--- Scenario 1: Internal state modification ---");
        // The user can modify the internal state of the period because Date is mutable
        end.setYear(70); // 1970
        System.out.println("Modified period (end is now 1970): " + period);

        // Re-initialize for a more realistic exploit
        start = new Date(System.currentTimeMillis() - 100000);
        end = new Date(System.currentTimeMillis() + 100000);
        period = new MutablePeriod(start, end);

        System.out.println("\n--- Scenario 2: Bypassing end-of-period ---");
        if (controller.isAccessAllowed(period)) {
            System.out.println("Access GRANTED");

            // Maliciously extend the period after it was created
            end.setTime(end.getTime() + 1000000000000L);
            System.out.println("Maliciously extended period: " + period);

            // Now the user has access for a much longer time than intended
        } else {
            System.out.println("Access DENIED");
        }

        System.out.println("\n--- Scenario 3: Corrupting the invariant ---");
        // Start is now AFTER end, even though the constructor checks for this
        start.setTime(end.getTime() + 100000);
        System.out.println("Corrupted period (start > end): " + period);

        System.out.println("\n--- Scenario 4: Attempting to exploit SecurePeriod ---");
        java.time.Instant s = java.time.Instant.now();
        java.time.Instant e = s.plusSeconds(100);
        SecurePeriod securePeriod = new SecurePeriod(s, e);
        System.out.println("Secure period: " + securePeriod);

        // There is no way to modify 's' or 'e' to affect 'securePeriod'
        // Instant is immutable. Any method like plusSeconds returns a NEW Instant.
        s = s.plusSeconds(1000000);
        System.out.println("Modified 's' variable, but securePeriod.start() remains: " + securePeriod.start());

        if (securePeriod.start().equals(s)) {
            System.out.println("VULNERABLE: securePeriod was modified!");
        } else {
            System.out.println("SECURE: securePeriod remained unchanged.");
        }
    }
}
