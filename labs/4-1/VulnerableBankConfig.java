/**
 * VulnerableBankConfig.java
 * 
 * This class demonstrates a failure to limit accessibility (Guideline 4-1).
 * By making fields and internal methods public, it allows any other class
 * to modify the internal state without restriction.
 */
public class VulnerableBankConfig {

    // VULNERABILITY: Making fields public allows direct modification,
    // bypassing any validation logic that might otherwise be in a setter.
    public double interestRate = 0.05;

    // VULNERABILITY: This method is intended for internal initialization,
    // but its 'public' access allows anyone to trigger a reset.
    public void resetToDefault() {
        System.out.println("[VulnerableConfig] Resetting interest rate to default...");
        this.interestRate = 0.05;
    }
}
