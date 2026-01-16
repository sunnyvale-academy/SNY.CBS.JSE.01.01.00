import java.util.*;

/**
 * A lightweight Recursive Descent Parser for a subset of SQL.
 * Specifically handles: SELECT * FROM table WHERE column = 'value' [OR/AND ...]
 */
public class SqlParser {

    private List<String> tokens;
    private int pos;

    public static class SyntaxException extends Exception {
        public SyntaxException(String message) {
            super(message);
        }
    }

    /**
     * Validates if the given SQL string conforms to our simple grammar.
     */
    public String validate(String sql) throws SyntaxException {
        this.tokens = tokenize(sql);
        this.pos = 0;

        return parseSelect();
    }

    private String parseSelect() throws SyntaxException {
        expect("SELECT");
        expect("*");
        expect("FROM");
        String table = next();
        expect("WHERE");
        String condition = parseCondition();

        if (pos < tokens.size()) {
            throw new SyntaxException("Tail end of query is invalid: " + tokens.get(pos));
        }

        return "Command: SELECT, Table: " + table + ", Condition: " + condition;
    }

    private String parseCondition() throws SyntaxException {
        StringBuilder condition = new StringBuilder();
        condition.append(next()); // Column
        expect("=");
        condition.append(" = ");
        condition.append(next()); // Value (with quotes)

        while (pos < tokens.size()) {
            String op = peek();
            if (op.equalsIgnoreCase("OR") || op.equalsIgnoreCase("AND")) {
                condition.append(" ").append(next()).append(" ");
                condition.append(parseCondition()); // Recurse for nested conditions
            } else {
                break;
            }
        }
        return condition.toString();
    }

    private void expect(String expected) throws SyntaxException {
        String actual = next();
        if (actual == null || !actual.equalsIgnoreCase(expected)) {
            throw new SyntaxException(
                    "Expected '" + expected + "' but found '" + (actual == null ? "EOF" : actual) + "'");
        }
    }

    private String next() {
        return (pos < tokens.size()) ? tokens.get(pos++) : null;
    }

    private String peek() {
        return (pos < tokens.size()) ? tokens.get(pos) : null;
    }

    private List<String> tokenize(String sql) {
        List<String> list = new ArrayList<>();
        // Simple regex-based tokenizer that preserves quoted strings
        // Matches keywords, identifiers, operators, or single-quoted strings
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("'[^']*'|\\w+|\\*|=").matcher(sql);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }
}
