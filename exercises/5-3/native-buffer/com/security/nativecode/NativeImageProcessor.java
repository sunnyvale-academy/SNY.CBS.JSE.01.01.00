package com.security.nativecode;

/**
 * Vulnerable class for Guideline 5-3 exercise.
 * 
 * CHALLENGE: Refactor this class to follow Guideline 5-3.
 */
public final class NativeImageProcessor {

    /**
     * VULNERABILITY: Public native method can be bypassed by untrusted code.
     */
    public native void applyFilterInternal(byte[] pixelData, int offset, int length);

    /**
     * VULNERABILITY:
     * 1. No defensive copy (cloning).
     * 2. Unsafe overflow check using addition.
     */
    public void applyFilter(byte[] pixelData, int offset, int length) {
        if (pixelData == null) {
            throw new NullPointerException();
        }

        // Potential for TOCTOU: another thread could modify pixelData after this check
        // Potential for Integer Overflow: (offset + length) could wrap around
        if (offset < 0 || length < 0 || (offset + length) > pixelData.length) {
            throw new IllegalArgumentException("Invalid offset or length");
        }

        System.out.println("[PROCESSOR] Applying native filter to buffer...");
        // applyFilterInternal(pixelData, offset, length);
    }
}
