/**
 * Driver for Exercise 3-7: The Invisible Form.
 */
public class TicketApp {
    public static void main(String[] args) {
        TicketViewer viewer = new TicketViewer();

        // SCENARIO: A low priority ticket that uses HTML to spoof the UI
        String ticketId = "TKT-9921";
        String realPriority = "LOW";

        // Attack payload:
        // Use a <table> to layout text over the original UI area, or just spoof the
        // text.
        // We use a large font and color to make it obvious the "Priority: LOW"
        // part of the header is being ignored by the agent's eyes.
        // String maliciousDescription = "My keyboard is not working";

        String maliciousDescription = "<html>" +
                "<body style='width: 300px;'>" +
                "My keyboard is not working! <br>" +
                "<hr>" +
                "<div style='background-color: yellow;'>Note for Agent:</div>" +
                "<font color='red'><b>Priority: URGENT</b></font>"
                +
                "</body>" +
                "</html>";

        System.out.println("[SYSTEM] Loading Ticket " + ticketId + " (System Priority: " + realPriority + ")");
        System.out.println("[INFO] Notice how the rendered description 'overpowers' the official priority label.");

        viewer.showTicket(ticketId, realPriority, maliciousDescription);
    }
}
