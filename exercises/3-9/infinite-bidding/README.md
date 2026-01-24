# Exercise 3-9: The Infinite Bidding Wars

## Scenario
"BidBay" is a high-traffic auction platform. The engineering team has received reports that orchestrated attacks are "locking" certain high-value auctions. Once a specific malicious bid is placed, any subsequent legitimate bids (even for billions of dollars) are rejected by the system.

You have been tasked with auditing the auction engine to find out why these auctions are becoming impossible to outbid.

## Your Goal
1.  **Analyze**: Open `AuctionService.java`. Review the `placeBid` logic.
2.  **Exploit**: Run `AuctionApp.java`. It simulates an attacker who bids an "exceptional" value.
3.  **Confirm**: Observe the console output. Does the attacker's bid make it impossible for any other user to outbid them?
4.  **Fix**: Implement a secure version `SecureAuctionService.java` that prevents any exceptional floating-point values (like `Infinity` or `NaN`) from being accepted as a bid.

## The Malicious Bid
The attacker isn't bidding a high price. They are bidding `Double.POSITIVE_INFINITY`. 
Because IEEE 754 logic dictates that any finite number is less than infinity, no legitimate bid can ever exceed it.

## Instructions
1.  Compile the exercise:
    ```bash
    javac *.java
    ```
2.  Run the application:
    ```bash
    java AuctionApp
    ```

Observe how "Bidding $1,000,000,000.0" returns `false` after the attacker's "infinite" bid.
