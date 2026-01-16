/**
 * An HTML generator that only escapes basic tag delimiters.
 * VULNERABILITY: Inadequate escaping for attribute contexts (Guideline 3-3).
 */
public class VulnerableHtmlGenerator {

    public String generateInput(String userInput) {
        // VULNERABILITY: User input is placed in a double-quoted attribute,
        // but double quotes aren't escaped.
        String partiallyEscaped = escapeBasicTags(userInput);

        return "<input type=\"text\" name=\"username\" value=\"" + partiallyEscaped + "\">";
    }

    private String escapeBasicTags(String text) {
        if (text == null)
            return "";
        return text.replace("<", "&lt;").replace(">", "&gt;");
    }
}
