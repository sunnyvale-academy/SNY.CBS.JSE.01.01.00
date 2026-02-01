public class OverrideLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 7-4: The Constructor Trap ---");

        System.out.println("\n[1] Instantiating MaliciousService(\"SECRET-123\")...");
        // This will trigger the BaseService constructor, which calls the overridden
        // setup()
        MaliciousService service = new MaliciousService("SECRET-123");

        System.out.println("\n[2] Calling doWork() after full initialization...");
        service.doWork();

        System.out.println("\nALERT: Subclass method was executed on uninitialized state! (Vulnerable)");
    }
}
