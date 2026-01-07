import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class VulnerableUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private boolean isAdmin;

    // Default constructor for reflection in Exploit
    public VulnerableUser() {
    }

    public VulnerableUser(String username, boolean isAdmin) {
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
        // VULNERABILITY: No validation after deserialization
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', isAdmin=" + isAdmin + "}";
    }
}