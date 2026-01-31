/**
 * A simulation of a tracker app where a user might attempt to spoof their
 * location.
 */
public class TrackerApp {
    public static void main(String[] args) {
        // User starts at a safe location
        Location myLocation = new Location(45.5, 10.5);
        GeofenceService service = new GeofenceService();

        System.out.println("--- Normal Operation ---");
        service.accessSecureResource(myLocation);

        System.out.println("\n--- Tampering Attempt ---");
        // The user wants to access the secure resource while actually being elsewhere.
        // Or they want to "teleport" after the check.

        // This is a simulation of shared state or an attacker holding a reference.
        Location maliciousLocation = new Location(45.5, 10.5); // "Safe" location for the check

        System.out.println("User is checking in...");

        // In a real scenario, this might happen in different threads or through an
        // interceptor.
        // Here we demonstrate the core flaw: mutability allows changing values after
        // validation.

        if (service.isInsideSecureZone(maliciousLocation)) {
            System.out.println("Validation passed. Spoofing real coordinates now...");

            // Tamper with the location object AFTER it was validated but BEFORE it's used
            maliciousLocation.latitude = 90.0; // North Pole!
            maliciousLocation.longitude = 0.0;

            System.out.println("Accessing resource with spoofed location...");
            // The service might use the 'loc' object again, or log it,
            // and it will see the tampered values.
            service.accessSecureResource(maliciousLocation);
        }
    }
}
