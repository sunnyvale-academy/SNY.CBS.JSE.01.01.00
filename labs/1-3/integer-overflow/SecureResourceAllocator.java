/**
 * Guideline 1-3 (DOS-3): Resource limit checks should not suffer from integer
 * overflow.
 * 
 * This class represents a secure resource allocator that prevents integer
 * overflow during limit checks.
 */
public class SecureResourceAllocator {
    private static final int MAX_MEMORY_LIMIT = 1024 * 1024; // 1MB limit

    /**
     * Attempts to allocate memory based on element count and size.
     * SECURE: Uses long arithmetic or checked multiplication to prevent overflow
     * bypass.
     */
    public void allocate(int numElements, int elementSize) {
        System.out.println("Secure: Attempting to allocate " + numElements + " elements of size " + elementSize);

        // SECURE CHECK using long to avoid overflow
        long totalRequested = (long) numElements * (long) elementSize;

        if (numElements < 0 || elementSize < 0 || totalRequested > MAX_MEMORY_LIMIT) {
            System.err.println("Secure: [REJECTED] Request is invalid or exceeds limit.");
            return;
        }

        System.out.println("Secure: [APPROVED] Allocation logic would proceed here.");
        try {
            // Simulated allocation
            byte[] data = new byte[(int) totalRequested];
            System.out.println("Secure: Successfully allocated " + data.length + " bytes.");
        } catch (Throwable t) {
            System.err.println("Secure: [ERROR] Allocation failed: " + t.toString());
        }
    }
}
