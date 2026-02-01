import java.io.*;

/**
 * VULNERABLE CLASS: TaskProcessor
 * Blindly deserializes any object received via the processTasks method.
 */
public class TaskProcessor {
    public static void main(String[] args) {
        System.out.println("--- Exercise 8-6: The Gadget Interceptor ---");

        try {
            // 1. Prepare legitimate task
            byte[] taskData = serialize(new CommonTask("Generate Q4 Report"));

            // 2. Prepare malicious gadget
            byte[] evilData = serialize(new ShutdownGadget());

            System.out.println("[1] Processing a legitimate task...");
            process(taskData);

            System.out.println("\n[2] Processing an untrusted task from the network...");
            process(evilData);

            System.out.println("\n[3] FINISHED: All tasks processed successfully.");

        } catch (Exception e) {
            System.out.println("\nERROR: " + e);
        }
    }

    /**
     * VULNERABILITY: Blind deserialization of untrusted data.
     */
    private static void process(byte[] data) throws Exception {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(bais)) {

            Object obj = ois.readObject();
            System.out.println("Processor: Handled " + obj);
        }
    }

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
        }
        return baos.toByteArray();
    }
}
