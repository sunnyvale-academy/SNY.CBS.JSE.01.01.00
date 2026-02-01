import java.io.*;

public class RangeApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 8-3: The Impossible Range ---");

        try {
            // 1. Attacker generates a malicious stream
            byte[] maliciousData = MaliciousStream.createInvalidRangeStream();

            // 2. System deserializes the stream
            System.out.println("\n[2] Deserializing the tampered stream...");
            ByteArrayInputStream bais = new ByteArrayInputStream(maliciousData);
            ObjectInputStream ois = new ObjectInputStream(bais);

            DateRange leakedRange = (DateRange) ois.readObject();

            System.out.println("\n[3] Resulting Object State:");
            System.out.println(leakedRange);

            if (leakedRange.getStart() > leakedRange.getEnd()) {
                System.out.println("\nALERT: Invariant bypassed! We created an 'impossible' DateRange. (Vulnerable)");
            }

        } catch (Exception e) {
            System.out.println("\nCheck: Deserialization failed as expected. " + e);
        }
    }
}
