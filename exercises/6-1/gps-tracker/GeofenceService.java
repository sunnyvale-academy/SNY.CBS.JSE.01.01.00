/**
 * A service that checks if a location is within a secure zone.
 */
public class GeofenceService {
    // Simple square "secure zone" for demonstration
    private static final double MIN_LAT = 45.0;
    private static final double MAX_LAT = 46.0;
    private static final double MIN_LON = 10.0;
    private static final double MAX_LON = 11.0;

    /**
     * Checks if the given location is inside the secure zone.
     * 
     * @param loc the location to check
     * @return true if permitted, false otherwise
     */
    public boolean isInsideSecureZone(Location loc) {
        System.out.println("Validating location: " + loc);

        // Vulnerability: The location 'loc' is mutable.
        // It passes this check, but could be modified immediately after.
        return loc.latitude >= MIN_LAT && loc.latitude <= MAX_LAT &&
                loc.longitude >= MIN_LON && loc.longitude <= MAX_LON;
    }

    public void accessSecureResource(Location loc) {
        if (isInsideSecureZone(loc)) {
            System.out.println("ACCESS GRANTED to secure resource at " + loc);
        } else {
            System.out.println("ACCESS DENIED: You are outside the secure zone.");
        }
    }
}
