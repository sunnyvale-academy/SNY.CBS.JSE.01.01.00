import java.util.Arrays;

/**
 * A mutable class representing a user profile.
 * VIOLATION: Does not provide copy functionality (no copy constructor, etc.).
 */
public class MutableProfile {
    private String bio;
    private String[] interests;

    public MutableProfile(String bio, String[] interests) {
        this.bio = bio;
        this.interests = interests.clone();
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
        this.interests = interests.clone();
    }

    @Override
    public String toString() {
        return "Bio: " + bio + " | Interests: " + Arrays.toString(interests);
    }
}
