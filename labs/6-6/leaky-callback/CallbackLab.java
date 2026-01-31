public class CallbackLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 6-6: The Leaky Callback ---");

        DataProcessor processor = new DataProcessor();

        System.out.println("\nInitial State:");
        processor.printResultsSummary();

        // Pass a malicious callback
        System.out.println("\nExecuting process with untrusted callback...");
        processor.process(new MaliciousCallback());

        System.out.println("\nVerification after callback execution:");
        processor.printResultsSummary();

        System.out.println("\nCheck: Was internal state protected? NO (Vulnerable)");
    }
}
