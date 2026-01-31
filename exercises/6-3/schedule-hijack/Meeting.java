import java.util.Date;
import java.util.List;

/**
 * VULNERABLE CLASS: Meeting
 * This class stores meeting details but fails to safely copy its inputs.
 */
public class Meeting {
    private final String title;
    private final Date startTime;
    private final List<String> participants;

    public Meeting(String title, Date startTime, List<String> participants) {
        this.title = title;

        // VULNERABILITY 1: Unsafe copy of a subclassable type (Date).
        // Using clone() allows a malicious subclass to bypass the copy.
        this.startTime = (Date) startTime.clone();

        // VULNERABILITY 2: Direct assignment of a mutable Collection.
        // The caller can modify the list after construction.
        this.participants = participants;
    }

    public void displayMeetingDetails() {
        System.out.println("Meeting: " + title);
        System.out.println("Time: " + startTime);
        System.out.println("Participants: " + participants);
    }
}
