public class HubApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 6-7: The Hijacked Sensor ---");

        SmartHomeHub hub = new SmartHomeHub();
        MaliciousSensor sensor = new MaliciousSensor();

        // 1. Hub collects reading
        System.out.println("\n[1] Hub collecting reading...");
        hub.collectReading(sensor);
        hub.printHistory();

        // 2. Sensor tampers with its previous reading
        System.out.println("\n[2] Malicious sensor triggers sabotage...");
        sensor.tamper();

        // 3. Verify history integrity
        System.out.println("\n[3] Verification of Hub records:");
        hub.printHistory();

        System.out.println("\nCheck: Was the history hijacked remotely? YES (Vulnerable)");
    }
}
