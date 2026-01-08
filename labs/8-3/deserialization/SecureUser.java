import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class SecureUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private boolean isAdmin;

    // Default constructor for reflection in Exploit
    public SecureUser() {
    }

    public SecureUser(String username, boolean isAdmin) {
        validateUser(username, isAdmin);
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getUsername() {
        return username;
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // SECURE: Re-apply validation after deserialization
        validateUser(this.username, this.isAdmin);
    }

    private void validateUser(String username, boolean isAdmin) {
        if (isAdmin && !"admin".equals(username)) {
            throw new IllegalArgumentException("Only 'admin' user can be an administrator.");
        }
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', isAdmin=" + isAdmin + "}";
    }
}
