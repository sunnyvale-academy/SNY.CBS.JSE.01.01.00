import java.io.*;

/**
 * SAFE SERVER: Uses ObjectInputFilter to whitelist allowed classes.
 */
public class SafeServer {
    public static Object processRequest(byte[] requestData) throws Exception {
        System.out.println("SafeServer: Deserializing request with ObjectInputFilter...");

        try (ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
                ObjectInputStream ois = new ObjectInputStream(bais)) {

            // CONFIGURE FILTER: Only allow DataPacket and core classes
            // Guideline 8-6 / SERIAL-6
            ObjectInputFilter filter = ObjectInputFilter.Config.createFilter(
                    "DataPacket;java.base/*;!*");
            ois.setObjectInputFilter(filter);

            return ois.readObject();
        }
    }
}
