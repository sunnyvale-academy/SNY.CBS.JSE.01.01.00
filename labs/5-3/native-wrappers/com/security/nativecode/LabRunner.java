package com.security.nativecode;

/**
 * Lab Runner for Guideline 5-3.
 */
public class LabRunner {
    public static void main(String[] args) {
        VulnerableNativeWrapper vulnerable = new VulnerableNativeWrapper();
        NativeMethodWrapper secure = new NativeMethodWrapper();

        byte[] smallBuffer = new byte[10];

        // Attack Parameters: offset = 1, len = Integer.MAX_VALUE
        // This will cause (offset + len) to overflow to Integer.MIN_VALUE.
        int maliciousOffset = 1;
        int maliciousLen = Integer.MAX_VALUE;

        System.out.println("--- Scenario: Integer Overflow Attack ---");
        System.out.println("Buffer size: " + smallBuffer.length);
        System.out.println("Requested Offset: " + maliciousOffset);
        System.out.println("Requested Length: " + maliciousLen);

        // Try against vulnerable version
        try {
            System.out.println("\n[TEST] Calling Vulnerable Wrapper...");
            vulnerable.doOperation(smallBuffer, maliciousOffset, maliciousLen);
            System.out.println(
                    "[RESULT] Vulnerable check FAILED to detect overflow! Control reached simulated native code.");
        } catch (IllegalArgumentException e) {
            System.out.println("[RESULT] Vulnerable check caught the error (unexpected for this specific exploit).");
        }

        // Try against secure version
        try {
            System.out.println("\n[TEST] Calling Secure Wrapper...");
            secure.doOperation(smallBuffer, maliciousOffset, maliciousLen);
            System.out.println("[RESULT] Secure check failed (unexpected).");
        } catch (IllegalArgumentException e) {
            System.out.println("[RESULT] Secure check SUCCESS: Detected malicious/overflowed parameters.");
        }
    }
}
