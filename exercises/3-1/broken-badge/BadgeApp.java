/**
 * Application demonstrating how to "break" the SVG badge via injection.
 */
public class BadgeApp {
    public static void main(String[] args) {
        System.out.println("--- Exercise 3-1: The Broken Badge ---");

        String label = "Build Status";

        // Malicious status: Closes the <text> tag and injects a massive red rectangle
        String maliciousStatus = "Passing</text><rect width=\"100%\" height=\"100%\" fill=\"red\"/><text>";

        VulnerableBadgeGenerator generator = new VulnerableBadgeGenerator();

        System.out.println("\n[Action] Generating badge with malicious status...");
        String svgOutput = generator.generateBadge(label, maliciousStatus);

        System.out.println("\n[SVG OUTPUT]");
        System.out.println(svgOutput);

        if (svgOutput.contains("fill=\"red\"")) {
            System.out.println("\n!!! VULNERABILITY CONFIRMED: A malicious red rectangle was injected into the SVG!");
            System.out.println("The badge structure has been corrupted.");
        }

        System.out.println("\nNext step: Implement SecureBadgeGenerator to sanitize inputs!");
    }
}
