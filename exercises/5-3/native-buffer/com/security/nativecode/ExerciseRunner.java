package com.security.nativecode;

/**
 * Runner to demonstrate the exploit against the vulnerable processor.
 */
public class ExerciseRunner {
    public static void main(String[] args) {
        NativeImageProcessor processor = new NativeImageProcessor();
        byte[] buffer = new byte[100];

        // Exploit parameters: offset + length will overflow
        int offset = 10;
        int length = Integer.MAX_VALUE - 5;

        System.out.println("--- Exercise 5-3: Native Buffer exploit ---");
        System.out.println("Buffer size: " + buffer.length);
        System.out.println("Offset: " + offset);
        System.out.println("Length: " + length);
        System.out.println("Calculated offset + length (overflowed): " + (offset + length));

        try {
            processor.applyFilter(buffer, offset, length);
            System.out.println("[CRITICAL] Exploit SUCCESS: Vulnerable check bypassed!");
        } catch (IllegalArgumentException e) {
            System.out.println("[SAFE] Check caught the error.");
        } catch (Exception e) {
            System.out.println("[ERROR] Unexpected exception: " + e.getMessage());
        }
    }
}
