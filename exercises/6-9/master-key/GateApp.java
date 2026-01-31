public class GateApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 6-9: The Master Key Injection ---");

        String hackerKey = "BOB-IS-HERE";

        // 1. Initial check: Hacker key should fail
        System.out.println("\n[1] Attempting entry with key: " + hackerKey);
        if (Gatekeeper.checkKey(hackerKey)) {
            System.out.println("ACCESS GRANTED (Internal error?)");
        } else {
            System.out.println("ACCESS DENIED (Expected)");
        }

        // 2. Run the exploit
        System.out.println("\n[2] Triggering KeyExploit...");
        KeyExploit.injectKey(hackerKey);

        // 3. Re-attempt entry: Hacker key should now work!
        System.out.println("\n[3] Re-attempting entry with key: " + hackerKey);
        if (Gatekeeper.checkKey(hackerKey)) {
            System.out.println("ACCESS GRANTED! (Security Compromised)");
        } else {
            System.out.println("ACCESS DENIED! (System Secure)");
        }

        if (Gatekeeper.checkKey(hackerKey)) {
            System.out.println("\nCheck: Master key array hijacked? YES (Vulnerable)");
        }
    }
}
