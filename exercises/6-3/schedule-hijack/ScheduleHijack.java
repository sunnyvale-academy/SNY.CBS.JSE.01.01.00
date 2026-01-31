import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ScheduleHijack {
    public static void main(String[] args) {
        System.out.println("--- Starting Exercise 6-3: Schedule Hijack ---");

        // Prepare legitimate data
        List<String> participants = new ArrayList<>();
        participants.add("CEO");
        participants.add("CFO");

        MaliciousDate startTime = new MaliciousDate(System.currentTimeMillis() + 3600000); // 1 hour from now

        // Create the meeting
        Meeting boardMeeting = new Meeting("Budget Approval", startTime, participants);

        System.out.println("\n[Original Scheduled Meeting]");
        boardMeeting.displayMeetingDetails();

        // 1. Hijack the Participant List (Direct Assignment Exploit)
        participants.add("Corporate Spy");

        // 2. Hijack the Start Time (clone() subclass Exploit)
        startTime.setTime(0); // Set to 1970 - the meeting is effectively cancelled/lost

        System.out.println("\n[Hijacked Meeting State]");
        boardMeeting.displayMeetingDetails();

        System.out.println("\nCheck: Was the meeting hijacked? " +
                (participants.size() > 2 ? "YES (Vulnerable)" : "NO (Secure)"));
    }
}
