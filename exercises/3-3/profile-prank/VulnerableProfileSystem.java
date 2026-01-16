/**
 * A profile system that builds HTML using string concatenation.
 * VULNERABILITY: Context-unaware injection in a CSS attribute (Guideline 3-3).
 */
public class VulnerableProfileSystem {

    public String generateProfileHeader(String favoriteColor) {
        // VULNERABILITY: Direct injection into a style attribute.
        // Basic escaping of < and > is done, but it's useless in this context.
        String escapedText = favoriteColor.replace("<", "&lt;").replace(">", "&gt;");

        return "<div class=\"header\" style=\"background-color: " + escapedText + "; min-height: 100px;\">\n" +
                "  <h1>User Profile</h1>\n" +
                "</div>";
    }
}
