package com.security.nativecode;

/**
 * Guideline 5-3 / INPUT-3: Define wrappers around native methods
 * 
 * This class follows the secure pattern exactly as shown in the Oracle Secure
 * Coding Guidelines.
 */
public final class NativeMethodWrapper {

    /**
     * Private native method ensures it cannot be called directly by untrusted code.
     */
    private native void nativeOperation(byte[] data, int offset, int len);

    /**
     * Wrapper method performs necessary security checks and defensive copying.
     */
    public void doOperation(byte[] data, int offset, int len) {
        if (data == null) {
            throw new NullPointerException();
        }

        // Defensive copy: clones the input array to prevent TOCTOU attacks
        data = data.clone();

        // Validate input carefully to avoid integer overflow
        // Note: offset + len would be subject to integer overflow.
        // For example, if offset = 1 and len = Integer.MAX_VALUE,
        // then offset + len == Integer.MIN_VALUE, which is less than data.length.
        if (offset < 0 || len < 0 || offset > data.length - len) {
            throw new IllegalArgumentException("Invalid offset or length");
        }

        // nativeOperation(data, offset, len);
        // In a real JNI scenario, this would call the native code.
        System.out.println("[SECURE] Native operation simulation started for length: " + len);
    }
}
