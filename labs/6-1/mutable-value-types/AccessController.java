import java.util.Date;

public class AccessController {
    /**
     * Checks if the current time is within the allowed period.
     * 
     * @param period the allowed time period
     * @return true if access is allowed, false otherwise
     */
    public boolean isAccessAllowed(MutablePeriod period) {
        Date now = new Date();
        System.out.println("Checking access for: " + now);
        System.out.println("Allowed period: " + period);

        if (now.after(period.start()) && now.before(period.end())) {
            // Vulnerability: An attacker could modify 'period' after this check
            // but before the resource is actually accessed (TOCTOU).
            // Or more simply, just by holding a reference to the Date objects.
            return true;
        }
        return false;
    }
}
