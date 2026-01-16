/**
 * Driver app for The Profile Prank exercise.
 */
public class ProfileApp {
    public static void main(String[] args) {
        System.out.println("=== Exercise 3-3: The Profile Prank ===");

        VulnerableProfileSystem system = new VulnerableProfileSystem();

        // 1. Normal Usage
        System.out.println("\n[Normal] Setting color to 'lightblue'");
        System.out.println(system.generateProfileHeader("lightblue"));

        // 2. The Prank (Exploit)
        // This payload closes the background-color and injects an alert via a malicious
        // style
        // Note: Real browser XSS in style attributes is tricky/rare now,
        // but breaking structure is easy.
        String prankColor = "blue;\" onmouseover=\"alert('Pranked!')\" data-hack=\"";

        System.out.println("\n[Action] Attempting prank with input: " + prankColor);
        String output = system.generateProfileHeader(prankColor);

        System.out.println("\n[RESULTING HTML]");
        System.out.println(output);

        if (output.contains("onmouseover=")) {
            System.out.println(
                    "\n!!! PRANK SUCCESSFUL: The 'style' attribute was broken and 'onmouseover' was injected!");
        }

        System.out.println("\nMISSION: Implement SecureProfileSystem to stop the prank!");
    }
}
