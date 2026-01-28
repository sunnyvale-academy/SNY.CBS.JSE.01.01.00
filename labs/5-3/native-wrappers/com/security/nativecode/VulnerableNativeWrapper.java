package com.security.nativecode;

/**
 * A vulnerable implementation that violates Guideline 5-3.
 */
public final class VulnerableNativeWrapper {

    /**
     * VULNERABILITY 1: Public native method can be called directly by malicious
     * code.
     */
    public native void nativeOperation(byte[] data, int offset, int len);

    /**
     * A "wrapper" that tries to validate but fails.
     */
    public void doOperation(byte[] data, int offset, int len) {
        // VULNERABILITY 2: No defensive copy (cloning).
        // A malicious thread can modify 'data' after validation but before/during
        // native use (TOCTOU).

        // VULNERABILITY 3: Unsafe overflow check.
        // offset + len can overflow!
        if (offset < 0 || len < 0 || (offset + len) > data.length) {
            throw new IllegalArgumentException("Invalid offset or length");
        }

        System.out.println("[VULNERABLE] Processing data... (Simulation)");
    }
}
