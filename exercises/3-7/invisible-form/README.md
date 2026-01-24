# Exercise 3-7: The Invisible Form

## Scenario
You are part of the security team at "FastSupport Inc." Support agents use a custom Java Swing application to review customer tickets. 

A sophisticated attacker has discovered that the ticket viewer renders HTML. They've sent a "Support Ticket" that looks perfectly normal to a casual observer but actually uses HTML to spoof the priority level and hide the "Reject" button, tricking agents into prioritizing low-importance (or even malicious) requests.

## Your Goal
1.  **Analyze**: Run the `TicketApp.java`. Look closely at the "Priority" display. 
2.  **Identify**: Find the code in `TicketViewer.java` that allows the ticket description to manipulate the rest of the UI.
3.  **Fix**: Implement a secure version in `SecureTicketViewer.java` that prevents any HTML in the ticket from affecting the UI.

## The Payloads to Watch Out For
Attackers can use payloads like this in their ticket descriptions:
- `<html>Priority: <font color='red'>URGENT</font><!--` (This hides the REAL priority label by starting a comment or using complex layout)
- `<html><div style='background: white; position: absolute; top: 0; left: 0; width: 1000; height: 1000;'>...</div></html>` (Hiding the entire UI)

## Instructions
1.  Compile the exercise:
    ```bash
    javac *.java
    ```
2.  Run the application:
    ```bash
    java TicketApp
    ```

Observe how "Priority: LOW" appears as "Priority: URGENT" in red because of a clever injection in the description field.
