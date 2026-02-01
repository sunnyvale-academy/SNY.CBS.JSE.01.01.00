public class SessionLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 7-1: The Forged Session ---");

        // 1. Attempting to create a forged session.
        // We use the Session interface to interact with the object.
        System.out.println("\n[1] Attempting to create a forged session using Public Constructor...");

        Session session = SessionExploit.forgeAdminSession("evil_bob");

        // 2. System accepts the session because it's a valid Session object
        System.out.println("\n[2] System Verifying Session...");
        verifyAccess(session);

        // 3. Proper way: Using the SecureSession factory method
        System.out.println("\n[3] Creating a SecureSession via Factory Method...");
        Session secureSession = SecureSession.createSession("alice", "SECRET-VAL-123");
        if (secureSession != null) {
            System.out.println("Result: Created " + secureSession);
            verifyAccess(secureSession);
        }

        // 4. Demonstrating that SecureSession cannot be forged
        System.out.println("\n[4] Attempting to forge a SecureSession directly...");
        System.out.println(
                "Result: [COMPILATION ERROR] 'SecureSession(String, String)' has private access in 'SecureSession'");
        // Session forgedSecure = new SecureSession("evil_bob", "ADMIN"); // This fails
        // to compile!
    }

    private static void verifyAccess(Session session) {
        if ("ADMIN".equals(session.getRole())) {
            System.out.println("ACCESS GRANTED: Welcome, Administrator " + session.getUserId());
            System.out.println("ALERT: System Compromised! (Vulnerable)");
        } else {
            System.out.println("ACCESS DENIED: Insufficient privileges.");
        }
    }
}
