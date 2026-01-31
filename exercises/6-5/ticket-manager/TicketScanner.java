/**
 * VULNERABLE SERVICE: TicketScanner
 * Scans tickets at the entrance of the VIP Lounge.
 */
public class TicketScanner {

    public void scanForVipLounge(EventTicket ticket) {
        System.out.println("Scanning: " + ticket);

        // VIOLATION: Trusting identity equality (==) to block a restricted type.
        // It assumes that any "STANDARD" ticket MUST BE exactly the blueprint object.
        if (ticket == EventTicket.STANDARD_BLUEPRINT) {
            System.out.println("ACCESS DENIED: Standard tickets are not allowed in the VIP Lounge.");
        } else {
            System.out.println("ACCESS GRANTED: Welcome to the VIP Lounge!");
        }
    }
}
