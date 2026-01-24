package com.bank.core;

import com.bank.core.internal.SecurityUtils;

public class BankingService {
    public void processTransaction(double amount) {
        System.out.println("Processing transaction of $" + amount);
        String sessionKey = SecurityUtils.generateSessionKey();
        System.out.println("Transaction secured with temporary session key.");
    }
}
