import java.io.*;

/**
 * EXPLOIT: TokenSniffer
 * Demonstrates how serialization exposes private secrets to anyone who can
 * inspect the byte stream.
 */
public class TokenSniffer {

    public static String extractSecretFromStream(SessionToken token) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(token);
        oos.close();

        byte[] stream = baos.toByteArray();
        String streamContent = new String(stream);

        // Simple search in the byte stream for the sensitive string.
        // In a real attack, the attacker would parse the stream more accurately.
        System.out.println("Sniffer: Scanning serialized byte stream for secrets...");

        // Let's assume the attacker knows the format and looks for the 'secretValue'
        // field data.
        // For this demo, we'll just show that the secret string is present in plain
        // text (roughly).
        if (streamContent.contains("SECRET-")) {
            int start = streamContent.indexOf("SECRET-");
            // Find end of string (non-printable usually or specific length)
            return streamContent.substring(start, start + 15);
        }

        return "Not found";
    }
}
