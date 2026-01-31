import java.io.*;

/**
 * VULNERABLE CLASS: BackupManager
 * Guidelines:
 * - SERIAL-3 (8-3): Unsafe deserialization of objects.
 */
public class BackupManager {
    public void restore(File backupFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(backupFile))) {
            // Vulnerability: Reading an object without any filtering.
            // An attacker could provide a malicious .ser file.
            Object data = ois.readObject();
            System.out.println("Restored data: " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
