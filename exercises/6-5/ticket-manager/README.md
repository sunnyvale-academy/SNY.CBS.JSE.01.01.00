# Exercise 6-5: The Forged Ticket (MUTABLE-5)

## The Scenario
"Grand Stadium Events" uses an automated ticket scanner for their VIP lounges. To save on processing time, the system developers used identity equality (`==`) to quickly identify and block "Standard" ticket holders.

However, the `EventTicket` class allows anyone to call `new EventTicket("STANDARD")`. Since this new object has a different memory address, it bypasses the scanner's block check.

## Your Task
Secure the ticket system by following **Guideline 6-5 / MUTABLE-5: Do not trust identity equality when overridable on input reference objects**.

1.  **Analyze the Vulnerability**:
    Run `ForgeTicket.java`. Why is the forged ticket allowed into the VIP Lounge even though its status is "STANDARD"?

2.  **Fix the System**:
    Choose the best approach to fix this:
    - **Option A (Recommended)**: Refactor `EventTicket` to use an **`enum`** for ticket types.
    - **Option B**: Update `TicketScanner.java` to use `.equals()` or state-based checks.

## Instructions

1.  **Observe the Bypass**:
    ```bash
    javac *.java
    java ForgeTicket
    ```
    Note that the forged ticket successfully enters the VIP Lounge.

2.  **Apply the Fix**:
    Refactor `EventTicket.java` and `TicketScanner.java` to use a more robust check.

3.  **Verify**:
    Compile and run again. Both the official and forged standard tickets should be denied access.
