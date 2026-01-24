/**
 * Driver for Exercise 3-9: The Infinite Bidding Wars.
 */
public class AuctionApp {
    public static void main(String[] args) {
        AuctionService auction = new AuctionService();

        // 1. Initial legitimate bid
        auction.placeBid("Alice", 100.0);

        // 2. Attacker places an "Infinite" bid
        // In a real application, this could be sent via REST API or serialized form
        double infiniteBid = Double.POSITIVE_INFINITY;
        System.out.println("\n[ATTACK] Attacker 'Malory' places an infinite bid...");
        auction.placeBid("Malory", infiniteBid);

        // 3. Legitimate user tries to outbid with 1 billion dollars
        double hugeBid = 1_000_000_000.0;
        System.out.println("\n[Legitimate] Bob tries to outbid with $1 Billion...");
        boolean success = auction.placeBid("Bob", hugeBid);

        if (!success) {
            System.out.println("\n[RESULT] THE AUCTION IS LOCKED! Bob's $1 Billion bid was rejected.");
            System.out.println("Current Highest Bid: " + auction.getHighestBid());
        }
    }
}
