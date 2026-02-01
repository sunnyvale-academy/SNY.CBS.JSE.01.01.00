public class MonitorLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 6-11: The Hijacked Counter ---");

        System.out.println("\n[1] Normal Operation: Recording 2 failures...");
        SecurityMonitor.failureCount++;
        SecurityMonitor.failureCount++;
        SecurityMonitor.displayStatus();

        // One more failure would lock the system
        System.out.println("\n[2] System is one failure away from lockout.");

        // Malicious module detects the risk and intervention happens
        CounterExploit.resetCounter();

        System.out.println("\n[3] Recording 2 more failures (Total 4 attempted)...");
        SecurityMonitor.failureCount++;
        SecurityMonitor.failureCount++;
        SecurityMonitor.displayStatus();

        if (SecurityMonitor.failureCount >= 3 || SecurityMonitor.isLockedOut()) {
            System.out.println("\nCheck: Security lockout active? YES (Secure)");
        } else {
            System.out.println("\nCheck: Security lockout active? NO (System compromised!)");
        }
    }
}
