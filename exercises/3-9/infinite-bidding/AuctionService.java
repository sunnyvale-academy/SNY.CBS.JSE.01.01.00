/**
 * Exercise 3-9: Vulnerable Auction Service.
 * Fails to validate exceptional floating point values.
 */
public class AuctionService {
    private double currentHighestBid = 0.0;
    private String currentWinner = "None";

    public boolean placeBid(String bidder, double amount) {
        System.out.println("[Auction] " + bidder + " attempts to bid: " + amount);

        // VULNERABILITY: Standard comparison check.
        // If amount is POSITIVE_INFINITY, it will be > currentHighestBid.
        // After that, currentHighestBid becomes POSITIVE_INFINITY.
        // No finite bid can ever be > POSITIVE_INFINITY.
        if (amount <= currentHighestBid) {
            System.out.println("   -> Rejected: Bid too low.");
            return false;
        }

        this.currentHighestBid = amount;
        this.currentWinner = bidder;
        System.out.println("   -> Accepted! " + bidder + " is now winning with " + amount);
        return true;
    }

    public double getHighestBid() {
        return currentHighestBid;
    }
}
