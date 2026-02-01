import java.io.*;

/**
 * EXPLOIT: CredentialSniffer
 * Demonstrates how to extract password information from a serialized stream.
 */
public class CredentialSniffer {

    public static String sniffPassword(UserCredentials credentials) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(credentials);
        oos.close();

        byte[] stream = baos.toByteArray();
        String streamContent = new String(stream);

        System.out.println("Sniffer: Intercepted " + stream.length + " bytes of session data.");

        // Let's assume the attacker knows we use a standard 'P@ss' prefix or just
        // searches.
        // For this demo, we'll locate the "topSecret" string in the stream.
        if (streamContent.contains("topSecret")) {
            int start = streamContent.indexOf("topSecret");
            return streamContent.substring(start, start + 13);
        }

        return "Not found";
    }
}
