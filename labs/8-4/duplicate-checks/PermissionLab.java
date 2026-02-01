import java.io.*;

public class PermissionLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 8-4: The Permission Bypass ---");

        try {
            // 1. Give permission to create the document
            System.out.println("[1] Granting permission and creating document...");
            SecurityContext.setPermission(true);
            SecureDocument doc = new SecureDocument("Classified", "The secret formula is 42.");

            // 2. Serialize it
            System.out.println("[2] Serializing document...");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(doc);
            oos.close();

            // 3. Revoke permission
            System.out.println("[3] Revoking permission...");
            SecurityContext.setPermission(false);

            // 4. Try to deserialize
            System.out.println("[4] Attempting to deserialize without permission...");
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);

            System.out.println("\nALERT: Security check bypassed via deserialization! (Vulnerable)");

            System.out.println("\n--- Verifying ValidatedSecureDocument (Secure) ---");
            // 1. Give permission to create the document
            SecurityContext.setPermission(true);
            ValidatedSecureDocument vDoc = new ValidatedSecureDocument("Top Secret", "Burn after reading.");

            // 2. Serialize it
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(vDoc);
            oos.close();

            // 3. Revoke permission
            SecurityContext.setPermission(false);

            // 4. Try to deserialize
            System.out.println("[6] Attempting to deserialize ValidatedSecureDocument WITHOUT permission...");
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);

            ValidatedSecureDocument secureDoc = (ValidatedSecureDocument) ois.readObject();
            System.out.println("ERROR: Should not reach here!");

        } catch (SecurityException e) {
            System.out.println("\n[7] SUCCESS: Deserialization blocked by duplicate check! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nRESULT: Unexpected error: " + e);
        }
    }
}
