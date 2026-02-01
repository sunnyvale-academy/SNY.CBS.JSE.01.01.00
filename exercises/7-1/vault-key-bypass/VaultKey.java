/**
 * VULNERABLE CLASS: VaultKey
 * Represents a key required to access the central data vault.
 * VIOLATION: Exposes a public constructor for a sensitive class.
 */
public class VaultKey {
    private final String keyType;

    // Guideline 7-1 Violation: Public constructor for a sensitive class.
    // This allows an attacker to bypass the official KeyManager and
    // forge a high-privilege 'MASTER' key directly.
    public VaultKey(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyType() {
        return keyType;
    }

    @Override
    public String toString() {
        return "VaultKey[Type: " + keyType + "]";
    }
}
