package com.security.bank;

/**
 * Data object for a bank transfer request.
 */
public class TransferRequest {
    private final String fromAccountId;
    private final String toAccountId;
    private final int amount;

    public TransferRequest(String fromAccountId, String toAccountId, int amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public int getAmount() {
        return amount;
    }
}
