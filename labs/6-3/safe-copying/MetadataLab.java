import java.util.Date;

public class MetadataLab {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Starting Lab 6-3: Unsafe Input Copying ---");

        // 1. Attack: Direct Array Assignment
        String[] myTags = { "initial", "data" };
        MutableMetadata meta = new MutableMetadata(new Date(), myTags);

        System.out.println("\n[1] Before Array Tamper:");
        meta.showMetadata();

        // Caller modifies the original array
        myTags[0] = "MALICIOUS";

        System.out.println("[1] After Array Tamper (Direct Assignment Vulnerability):");
        meta.showMetadata();

        // 2. Attack: clone() on Subclassable types
        MaliciousDate maliciousDate = new MaliciousDate(System.currentTimeMillis());
        MutableMetadata meta2 = new MutableMetadata(maliciousDate, new String[] { "test" });

        System.out.println("\n[2] Before Date Tamper:");
        meta2.showMetadata();

        // Wait a bit and modify the "copy" (which is actually the original)
        Thread.sleep(100);
        maliciousDate.setTime(0); // Set to Epoch

        System.out.println("[2] After Date Tamper (clone() on Subclass Vulnerability):");
        meta2.showMetadata();

        System.out.println("\n--- Lab End ---");
    }
}
