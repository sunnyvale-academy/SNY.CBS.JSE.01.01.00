import java.io.*;

/**
 * EXPLOIT: MaliciousStream
 * Manually creates a serialized byte stream that represents a
 * DateRange object with an invalid state (start > end).
 */
public class MaliciousStream {

    public static byte[] createInvalidRangeStream() throws IOException {
        // We create a valid object first, then we'll flip the values in the stream.
        DateRange valid = new DateRange(100, 200);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(valid);
        oos.close();

        byte[] stream = baos.toByteArray();

        // Standard Java Serialization logic for this class structure:
        // fields are serialized in alphabetical order: 'end' then 'start'.
        // end=200, start=100.

        System.out.println("Exploit: Tampering with serialized stream...");

        boolean foundEnd = false;
        for (int i = 0; i < stream.length - 8; i++) {
            if (!foundEnd && isLong(stream, i, 200)) {
                foundEnd = true;
                // The next long should be the 'start' field.
                if (isLong(stream, i + 8, 100)) {
                    writeLong(stream, i + 8, 500);
                    System.out.println(
                            "Exploit: Found 'end' (200) followed by 'start' (100). Injected 500 into 'start'.");
                }
            }
        }

        return stream;
    }

    private static boolean isLong(byte[] data, int offset, long value) {
        for (int i = 0; i < 8; i++) {
            if (data[offset + i] != (byte) ((value >> (8 * (7 - i))) & 0xFF))
                return false;
        }
        return true;
    }

    private static void writeLong(byte[] data, int offset, long value) {
        for (int i = 0; i < 8; i++) {
            data[offset + i] = (byte) ((value >> (8 * (7 - i))) & 0xFF);
        }
    }
}
