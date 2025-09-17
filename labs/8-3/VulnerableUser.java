import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class VulnerableUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private boolean isAdmin;

    public VulnerableUser(String username, boolean isAdmin) {
        if (isAdmin && !"admin".equals(username)) {
            throw new IllegalArgumentException("Only \'admin\' user can be an administrator.");
        }
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    // This readObject method is vulnerable because it bypasses the constructor\'s validation
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Deserialize fields directly
        // No validation performed here, allowing isAdmin to be true for non-admin users
    }

    @Override
    public String toString() {
        return "User{username=\'" + username + "\', isAdmin=" + isAdmin + "}";
    }
}

