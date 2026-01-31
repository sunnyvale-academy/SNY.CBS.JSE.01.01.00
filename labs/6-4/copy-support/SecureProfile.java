import java.util.Arrays;

/**
 * SECURE CLASS: SecureProfile
 * Follows Guideline 6-4 / MUTABLE-4: Support copy functionality.
 */
public final class SecureProfile {
    private String bio;
    private String[] interests;

    public SecureProfile(String bio, String[] interests) {
        this.bio = bio;
        this.interests = (interests != null) ? interests.clone() : new String[0];
    }

    /**
     * FIX: Copy Constructor.
     * Provides an easy and safe way for users to create a deep copy.
     */
    public SecureProfile(SecureProfile other) {
        this(other.bio, other.interests);
    }

    /**
     * OPTIONAL: Static factory method for copying.
     */
    public static SecureProfile copyOf(SecureProfile other) {
        return new SecureProfile(other);
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = (interests != null) ? interests.clone() : new String[0];
    }

    @Override
    public String toString() {
        return "Bio: " + bio + " | Interests: " + Arrays.toString(interests);
    }
}
