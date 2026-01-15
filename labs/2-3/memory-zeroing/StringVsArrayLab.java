import java.util.Arrays;

/**
 * Lab demonstrating the difference between immutable Strings and mutable arrays
 * for sensitive data.
 */
public class StringVsArrayLab {

    public static void main(String[] args) {
        System.out.println("--- Lab 2-3: Memory Zeroing ---");

        // --- PART 1: The Immutable String ---
        String secretString = "SUPER_SECRET_PASSWORD";
        System.out.println("\n[STRING] Secret created.");

        // Even if we "set it to null", the actual content remains in the heap
        // until the GC decides to collect it.
        secretString = null;
        System.out.println("[STRING] Variable set to null, but the original string is still somewhere in memory.");

        // --- PART 2: The Mutable Array ---
        char[] secretArray = "SUPER_SECRET_PASSWORD".toCharArray();
        System.out.println("\n[ARRAY] Secret created: " + new String(secretArray));

        // Use the secret...
        processSecret(secretArray);

        // SECURE: Manually zeroing out the memory
        System.out.println("[ARRAY] Purging memory...");
        Arrays.fill(secretArray, '0');

        System.out.println("[ARRAY] Post-purge content: " + new String(secretArray));

        System.out.println("\nLab Complete: Arrays allow explicit memory purging, Strings do not.");
    }

    private static void processSecret(char[] secret) {
        System.out.println("[DEBUG] Secret is being used...");
    }
}
