/**
 * A badge generator that builds SVG images using string concatenation.
 * VULNERABILITY: Susceptible to structural injection in XML (SVG).
 */
public class VulnerableBadgeGenerator {

    public String generateBadge(String label, String status) {
        // VULNERABILITY: Building XML structure via string concatenation
        // without escaping user-provided 'label' or 'status'.
        return "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"200\" height=\"30\">\n" +
                "  <rect width=\"200\" height=\"30\" fill=\"#555\"/>\n" +
                "  <text x=\"10\" y=\"20\" fill=\"#fff\" font-family=\"Arial\" font-size=\"14\">\n" +
                "    " + label + ": " + status + "\n" +
                "  </text>\n" +
                "</svg>";
    }
}
