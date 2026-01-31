public class ProfileLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 6-4: Missing Copy Functionality ---");

        String[] musicInterests = { "Jazz", "Blues" };
        MutableProfile original = new MutableProfile("Java Developer", musicInterests);

        System.out.println("Original: " + original);

        // Scenario: A caller wants to create a copy of the profile to modify it safely.
        // But MutableProfile lacks a copy constructor!

        // MISTAKE 1: Direct assignment
        MutableProfile shared = original;
        shared.setBio("Hacker");
        System.out.println("\n[After direct assignment modify]");
        System.out.println("Shared:   " + shared);
        System.out.println("Original: " + original + " (BROKEN - shared state!)");

        // Restore original
        original.setBio("Java Developer");

        // MISTAKE 2: Manual field copying (Incomplete/Error-prone)
        // Oops, the developer forgot to clone the interests!
        MutableProfile manualCopy = new MutableProfile(original.getBio(), original.getInterests());

        System.out.println("\n[After manual field copy modify]");
        manualCopy.getInterests()[0] = "ROCK"; // Modifying the array directly
        System.out.println("Copy:     " + manualCopy);
        System.out.println("Original: " + original + " (BROKEN - interests shared!)");

        System.out.println("\n--- End of Lab ---");
    }
}
