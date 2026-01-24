package com.bank.app;

import com.bank.core.BankingService;
import com.bank.core.internal.SecurityUtils;

public class BankApp {
    public static void main(String[] args) {
        System.out.println("--- Secure Bank Application ---");

        BankingService service = new BankingService();
        service.processTransaction(100.0);

        // EXERCISE: This code should NOT compile if the library is properly
        // modularized.
        // It accesses an internal package that shouldn't be visible.
        try {
            String leakedKey = SecurityUtils.getMasterKey();
            System.out.println("\n[VULNERABILITY] Leaked Master Key: " + leakedKey);
        } catch (Throwable t) {
            System.out.println("\n[SECURE] Could not access internal security utils.");
        }
    }
}
