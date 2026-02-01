import java.io.*;

public class FilterLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 8-6: The Selective Guard ---");

        try {
            // 1. Prepare a valid packet
            DataPacket okPacket = new DataPacket("Hello Server");
            byte[] okData = serialize(okPacket);

            // 2. Prepare an exploit packet
            ExploitGadget evilGadget = new ExploitGadget("whoami");
            byte[] evilData = serialize(evilGadget);

            System.out.println("\n--- STEP 1: Sending valid data to VulnerableServer ---");
            Object result1 = VulnerableServer.processRequest(okData);
            System.out.println("Result: " + result1);

            System.out.println("\n--- STEP 2: Sending EXPLOIT gadget to VulnerableServer ---");
            VulnerableServer.processRequest(evilData);
            System.out.println("Result: [Exploit Bypassed Server Logic]");

            System.out.println("\n--- STEP 3: Sending EXPLOIT gadget to SafeServer ---");
            try {
                SafeServer.processRequest(evilData);
                System.out.println("ERROR: Exploit should have been blocked!");
            } catch (InvalidClassException e) {
                System.out.println("\nSUCCESS: Exploit blocked by ObjectInputFilter!");
                System.out.println("Reason: " + e.getMessage());
            }

            System.out.println("\n--- STEP 4: Sending valid data to SafeServer ---");
            Object result2 = SafeServer.processRequest(okData);
            System.out.println("Result: " + result2);

        } catch (Exception e) {
            e.printStackTrace();
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
