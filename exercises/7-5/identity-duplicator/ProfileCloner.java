/**
 * MALICIOUS SUBCLASS: ProfileCloner
 * Subclasses UserProfile and implements Cloneable to forge new identities.
 */
public class ProfileCloner extends UserProfile implements Cloneable {

    public ProfileCloner(String username, String role) {
        super(username, role);
    }

    /**
     * The clone() method allows creating a new profile WITHOUT the authentication
     * step in the UserProfile constructor.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("Exploit: Cloning profile... (Backdoor identity creation)");
        return super.clone();
    }

    public static UserProfile duplicateProfile(UserProfile original) {
        try {
            // If we have an instance of ProfileCloner (or a victim profile we can cast),
            // we can duplicate it.
            if (original instanceof ProfileCloner) {
                return (UserProfile) ((ProfileCloner) original).clone();
            }
        } catch (CloneNotSupportedException e) {
            System.out.println("Exploit: Duplicate failed! " + e.getMessage());
        }
        return null;
    }
}
