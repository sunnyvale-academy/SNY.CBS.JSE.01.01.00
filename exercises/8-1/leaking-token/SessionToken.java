import java.io.Serializable;

/**
 * VULNERABLE CLASS: SessionToken
 * Represents an authentication token used for user sessions.
 * VIOLATION: Implements Serializable, which can lead to information disclosure
 * of private fields via byte-stream inspection.
 */
public class SessionToken implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String username;
    private final String secretValue; // This should stay hidden inside the JVM!

    public SessionToken(String username, String secret) {
        this.username = username;
        this.secretValue = secret;
    }

    public String getUsername() {
        return username;
    }

    // Notice there is no getSecretValue() method, but serialization will expose it
    // anyway!

    @Override
    public String toString() {
        return "SessionToken[user=" + username + "]";
    }
}
