/**
 * A service that manages cryptographic keys.
 * VULNERABILITY: Uses Strings for keys, leaving them in memory.
 */
public class VulnerableKeyService {

    private String internalKey;

    public void setKey(String key) {
        // String is immutable, we can't purge this version
        this.internalKey = key;
    }

    public void encryptData(String data) {
        System.out.println("[VULNERABLE] Using key to encrypt: " + internalKey);
        // ... encryption logic ...

        // Even if we null it, the string object "SUPER_SECRET_KEY"
        // remains in the heap until GC.
        this.internalKey = null;
    }
}
