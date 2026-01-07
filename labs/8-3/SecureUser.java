import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class SecureUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean isAdmin;

    public SecureUser(boolean isAdmin) {
        validateAdminStatus(isAdmin);
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    // This readObject method is secure because it re-validates the object's state
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Deserialize fields
        // Perform the same validation as the constructor
        validateAdminStatus(this.isAdmin);
    }

    private void validateAdminStatus(boolean isAdmin) {
        if (isAdmin) {
            throw new IllegalArgumentException("Deserialization failed: Direct creation of admin user is not allowed.");
        }
    }

    @Override
    public String toString() {
        return "User{isAdmin=" + isAdmin + "}";
    }
}

