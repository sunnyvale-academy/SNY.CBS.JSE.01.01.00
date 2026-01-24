package com.bank.core.internal;

public class SecurityUtils {
    // This MASTER_KEY should be internal and never exposed to consumers
    private static final String MASTER_KEY = "BANK-INTERNAL-SECRET-999";

    public static String generateSessionKey() {
        return "SESSION-" + System.currentTimeMillis();
    }

    public static String getMasterKey() {
        return MASTER_KEY;
    }
}
