public class EventTicket {
    // Blueprint for standard access
    public static final EventTicket STANDARD_BLUEPRINT = new EventTicket("STANDARD");

    private final String status;

    // VULNERABILITY: This public constructor allows anyone to create
    // a ticket object that looks like a STANDARD ticket but HAS A DIFFERENT
    // IDENTITY.
    public EventTicket(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Ticket [" + status + "]";
    }
}
