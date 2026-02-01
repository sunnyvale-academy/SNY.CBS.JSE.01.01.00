import java.io.*;

/**
 * VULNERABLE SERVER: Deserializes any object without filtering.
 */
public class VulnerableServer {
    public static Object processRequest(byte[] requestData) throws Exception {
        System.out.println("VulnerableServer: Deserializing request...");
        try (ByteArrayInputStream bais = new ByteArrayInputStream(requestData);
                ObjectInputStream ois = new ObjectInputStream(bais)) {

            return ois.readObject();
        }
    }
}
