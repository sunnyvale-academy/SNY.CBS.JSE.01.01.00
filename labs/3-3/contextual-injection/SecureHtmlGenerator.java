/**
 * A secure HTML generator that performs contextual escaping.
 * SECURE: Escapes quotes to prevent attribute breakout (Guideline 3-3).
 */
public class SecureHtmlGenerator {

    public String generateInput(String userInput) {
        // SECURE: Perform contextual escaping for double-quoted attributes.
        String safeInput = escapeForDoubleQuotedAttribute(userInput);

        return "<input type=\"text\" name=\"username\" value=\"" + safeInput + "\">";
    }

    /**
     * Context-aware escaping for double-quoted attributes.
     * Guideline 3-3 recommends care based on the specific generation context.
     */
    private String escapeForDoubleQuotedAttribute(String text) {
        if (text == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    sb.append("&quot;");
                    break; // CRITICAL: Escape the context delimiter
                case '\'':
                    sb.append("&#39;");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}
