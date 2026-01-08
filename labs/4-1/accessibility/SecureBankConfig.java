/**
 * SecureBankConfig.java
 * 
 * This class demonstrates how to correctly limit accessibility (Guideline 4-1).
 * It uses the 'private' keyword to encapsulate state and internal logic.
 */
public class SecureBankConfig {

    // SECURE: Fields are private. They can only be accessed or modified
    // through controlled methods within this class.
    private double interestRate = 0.05;

    // SECURE: Provide a public getter if the value needs to be read.
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Internal helper method.
     * SECURE: Marking this as 'private' ensures it can only be
     * called by other methods inside this class.
     */
    private void resetToDefault() {
        this.interestRate = 0.05;
    }
}
