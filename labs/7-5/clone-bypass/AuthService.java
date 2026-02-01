/**
 * Mock authentication service for the lab.
 */
public class AuthService {
    private static boolean authorized = false;

    public static boolean isAuthorized() {
        return authorized;
    }

    public static void setAuthorized(boolean value) {
        authorized = value;
    }
}
