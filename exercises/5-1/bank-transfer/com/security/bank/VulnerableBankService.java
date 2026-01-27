package com.security.bank;

import java.util.HashMap;
import java.util.Map;

/**
 * Vulnerable service that processes bank transfers.
 * 
 * VULNERABILITY: Missing validation for the transfer amount.
 */
public class VulnerableBankService {
    private final Map<String, Integer> accounts = new HashMap<>();

    public VulnerableBankService() {
        // Initial balances
        accounts.put("ACC-USER-123", 100); // Poor user
        accounts.put("ACC-VAULT-999", 1000000); // Rich vault
    }

    public void processTransfer(TransferRequest request) {
        String from = request.getFromAccountId();
        String to = request.getToAccountId();
        int amount = request.getAmount();

        System.out.println("[BANK] Requesting transfer of $" + amount + " from " + from + " to " + to);

        // Logic error: adding a negative amount effectively reverses the transfer
        // direction
        int fromBalance = accounts.getOrDefault(from, 0);
        int toBalance = accounts.getOrDefault(to, 0);

        accounts.put(from, fromBalance - amount);
        accounts.put(to, toBalance + amount);

        System.out.println("[BANK] Transfer complete.");
        System.out.println("[BANK] New Balance " + from + ": $" + accounts.get(from));
        System.out.println("[BANK] New Balance " + to + ": $" + accounts.get(to));
    }
}
