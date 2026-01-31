public class ForgeTicket {
    public static void main(String[] args) {
        System.out.println("--- Exercise 6-5: The Forged Ticket ---");

        TicketScanner scanner = new TicketScanner();

        // 1. Legitimate Standard Ticket - Blocked
        System.out.println("\n[1] Scanning official STANDARD ticket:");
        scanner.scanForVipLounge(EventTicket.STANDARD_BLUEPRINT);

        // 2. Attack: Create a Forged Standard Ticket
        // The attacker creates a new object with status "STANDARD".
        // Since the scanner uses '==' to check against the blueprint,
        // this forged object will NOT match, and access will be granted.
        EventTicket forgedStandard = new EventTicket("STANDARD");

        System.out.println("\n[2] Scanning forged STANDARD ticket (Identity Forgery):");
        System.out.println("forgedStandard == EventTicket.STANDARD_BLUEPRINT is "
                + (forgedStandard == EventTicket.STANDARD_BLUEPRINT));

        scanner.scanForVipLounge(forgedStandard);

        System.out.println("\nCheck: Did the forged ticket enter the VIP Lounge? YES (Vulnerable)");
    }
}
