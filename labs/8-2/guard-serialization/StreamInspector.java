import java.io.*;

/**
 * UTILITY: StreamInspector
 * Inspects a serialized byte stream to see if a specific secret exists in it.
 */
public class StreamInspector {

    public static boolean isSecretInStream(Object obj, String secret) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.close();

        byte[] stream = baos.toByteArray();
        String streamContent = new String(stream);

        System.out.println("Inspector: Scanning " + stream.length + " bytes of serialized data...");

        return streamContent.contains(secret);
    }
}
