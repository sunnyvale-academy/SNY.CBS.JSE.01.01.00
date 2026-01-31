public class SecureLabDemo {
    public static void main(String[] args) {
        System.out.println("--- Lab 6-5: Secure Enum Solution ---");

        SecureGateService secureGate = new SecureGateService();

        // 1. Try with GUEST
        System.out.println("\n[1] Attempting with SecureAccessLevel.GUEST:");
        secureGate.enterVault(SecureAccessLevel.GUEST);

        // 2. Try with ADMIN
        System.out.println("\n[2] Attempting with SecureAccessLevel.ADMIN:");
        secureGate.enterVault(SecureAccessLevel.ADMIN);

        // 3. Discussion on forgery:
        System.out.println("\n[Forgery Attempt]");
        System.out.println("Can we do 'new SecureAccessLevel(\"GUEST\")'?");
        System.out.println("NO: Java enums cannot be instantiated with 'new'.");
        System.out.println("The JVM guarantees that only ONE instance of GUEST exists.");

        System.out.println("\nConclusion: The '==' check is 100% reliable for enums.");
    }
}
