import java.io.*;
import java.util.Arrays;

public class FindOffset {
    public static void main(String[] args) throws Exception {
        SecureUser user = new SecureUser("attacker", false);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(user);
        oos.close();
        byte[] data = baos.toByteArray();

        System.out.println("Data length: " + data.length);
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%02x ", data[i]);
            if ((i + 1) % 16 == 0)
                System.out.println();
        }
        System.out.println();

        // Print byte array as hex string for easy reading
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }
        System.out.println("Hex: " + sb.toString());
    }
}
