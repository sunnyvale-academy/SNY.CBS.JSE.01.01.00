public class ConstantsLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 6-10: The Mutable Constant ---");

        System.out.println("\n[1] Initial State:");
        SystemInfo.display();

        // Simulate an untrusted module running
        System.out.println("\n[2] Running untrusted module...");
        TimeExploit.sabotage();

        System.out.println("\n[3] Final State:");
        SystemInfo.display();

        if (SystemInfo.RELEASE_DATE.getTime() == 0) {
            System.out.println("\nALERT: \"Constant\" field was MODIFIED! (Vulnerable)");
        } else {
            System.out.println("\nCheck: \"Constant\" field preserved. (Secure)");
        }
    }
}
