import java.io.Serializable;

/**
 * VULNERABLE CLASS: UserCredentials
 * A simple container for user login data.
 * VIOLATION: The password field is not marked as transient,
 * making it accessible in the serialized form.
 */
public class UserCredentials implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String username;
    private final String password; // THIS SHOULD BE TRANSIENT

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    // Notice no getPassword() getter is defined to "protect" the secret.
    // However, serialization doesn't care about getters!

    @Override
    public String toString() {
        return "UserCredentials[user=" + username + ", pass=***]";
    }
}
