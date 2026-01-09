# Lab 0-1: Design APIs to Avoid Security Concerns

## The Guideline (0-1 / FUNDAMENTALS-1)

The Java SE Security Coding Guidelines state: **"Design APIs to avoid security concerns."**

One common way APIs fail is by being easy to use incorrectly. When a method takes multiple parameters of the same type (like `int` or `String`), it is extremely easy for a developer to swap them, leading to logical errors that can have security implications (e.g., swapping a sender and receiver in a financial transaction).

## The Vulnerability: Positional Parameter Confusion

In `VulnerableBankService.java`, the `transfer` method is defined as:
```java
public void transfer(int fromId, int toId, double amount)
```
If a developer accidentally calls `transfer(target, source, 100)`, money is withdrawn from the wrong account. This is a common source of bugs in complex systems.

## The Solution: Fluent API or Typed Parameters

In `SecureBankService.java`, we use a **Fluent API** (or Domain Specific Language) to make the code self-documenting:
```java
bank.from(aliceId).to(bobId).amount(100.0);
```
With this design, it is almost impossible to swap the accounts accidentally without the code looking obviously wrong.

## How to Run
1. Compile: `javac labs/api_design/*.java`
2. Run Exploit: `java labs.api_design.TransferExploit`
