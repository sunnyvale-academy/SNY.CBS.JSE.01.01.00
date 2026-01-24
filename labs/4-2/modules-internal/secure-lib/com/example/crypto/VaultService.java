package com.example.crypto;

import com.example.crypto.internal.LegacyKeyManager;

public class VaultService {
    public void storeSecret(String secret) {
        String key = LegacyKeyManager.getMasterKey();
        System.out.println("Storing secret encrypted with: " + key);
    }
}
