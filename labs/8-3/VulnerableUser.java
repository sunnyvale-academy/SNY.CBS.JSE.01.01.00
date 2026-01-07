import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class VulnerableUser implements Serializable {
    private static final long serialVersionUID = 1L; 
    private boolean isAdmin;

    public VulnerableUser(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    // This readObject method is vulnerable because it bypasses the constructor's validation
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Deserialize fields directly
        // No validation performed here, allowing isAdmin to be true for non-admin users

    }

    @Override
    public String toString() {
        return "User{isAdmin=" + isAdmin + "}";
    }
}