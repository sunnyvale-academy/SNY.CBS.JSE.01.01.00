/**
 * VULNERABLE CLASS: AccessLevel
 * A reference type intended to represent security levels.
 * VIOLATION: Uses a public constructor alongside static "singleton" instances.
 */
public class AccessLevel {
    public static final AccessLevel ADMIN = new AccessLevel("ADMIN");
    public static final AccessLevel GUEST = new AccessLevel("GUEST");

    private final String name;

    // VULNERABILITY: Public constructor allows callers to create
    // their own instances with the same data.
    public AccessLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
