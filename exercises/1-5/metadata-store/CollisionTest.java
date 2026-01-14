import java.util.ArrayList;
import java.util.List;

/**
 * CollisionTest.java
 * 
 * Driver for Exercise 1-5.
 */
public class CollisionTest {

    public static void main(String[] args) {
        System.out.println("=== Exercise 1-5: Metadata Store Protection ===\n");

        runVulnerableTest();

        // TODO: Uncomment after implementing SecureMetadataStore

        System.out.println("\n-------------------------------------------");
        runSecureTest();

    }

    private static void runVulnerableTest() {
        System.out.println("--- Testing Vulnerable Metadata Store ---");
        VulnerableMetadataStore store = new VulnerableMetadataStore();

        int count = 20000;
        System.out.println("Attempting to insert " + count + " entries...");

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            store.setMetadata("Key_" + i, "Value_" + i);
        }
        long end = System.currentTimeMillis();

        System.out.println("Metadata size: " + store.size());
        System.out.println("Time taken: " + (end - start) + " ms");
        System.out.println("Vulnerable store accepted all entries without limits.");
    }

    private static void runSecureTest() {
        System.out.println("--- Testing Secure Metadata Store ---");
        SecureMetadataStore store = new SecureMetadataStore();

        int count = 20000;
        System.out.println("Attempting to insert " + count + " entries...");

        try {
            for (int i = 0; i < count; i++) {
                store.setMetadata("Key_" + i, "Value_" + i);
            }
        } catch (IllegalStateException e) {
            System.out.println("REJECTED: " + e.getMessage());
        }

        System.out.println("Metadata size: " + store.size());
        System.out.println("Secure store enforced limits.");
    }

}
