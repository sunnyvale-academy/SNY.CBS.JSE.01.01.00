package com.example.crypto.internal;

public class LegacyKeyManager {
    // This should be internal and hidden from library consumers
    public static String getMasterKey() {
        return "SUPER_SECRET_MASTER_KEY_123";
    }
}
