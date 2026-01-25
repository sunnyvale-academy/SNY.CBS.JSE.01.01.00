package com.service.internal;

/**
 * Internal class that handles sensitive authentication tokens.
 * This class should NEVER be accessible to external clients.
 */
public class SecureAuthenticator {
    public static String getMasterToken() {
        return "SUPER-SECRET-TOKEN-99-ALPHA";
    }
}
