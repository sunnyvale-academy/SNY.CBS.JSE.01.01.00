public class BypassLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 6-5: The Identity Trap ---");

        GateService gate = new GateService();

        // 1. Legitimate Guest attempt
        System.out.println("\n[1] Attempting with official GUEST instance:");
        gate.enterVault(AccessLevel.GUEST);

        // 2. Attack: Create a fake GUEST instance
        // Because AccessLevel has a public constructor, we can create a
        // new object that LOOKS like a guest but HAS A DIFFERENT IDENTITY.
        AccessLevel fakeGuest = new AccessLevel("GUEST");

        System.out.println("\n[2] Attempting with forged GUEST instance:");
        System.out.println("fakeGuest == AccessLevel.GUEST is " + (fakeGuest == AccessLevel.GUEST));

        gate.enterVault(fakeGuest);

        System.out.println("\nCheck: Did the forged guest enter the vault? YES (Vulnerable)");
    }
}
