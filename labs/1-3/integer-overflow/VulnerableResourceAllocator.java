/**
 * Guideline 1-3 (DOS-3): Resource limit checks should not suffer from integer
 * overflow.
 * 
 * This class represents a vulnerable resource allocator that is susceptible to
 * integer overflow.
 */
public class VulnerableResourceAllocator {
    private static final int MAX_MEMORY_LIMIT = 1024 * 1024; // 1MB limit

    /**
     * Attempts to allocate memory based on element count and size.
     * VULNERABLE: The multiplication numElements * elementSize can overflow.
     */
    public void allocate(int numElements, int elementSize) {
        System.out.println("Vulnerable: Attempting to allocate " + numElements + " elements of size " + elementSize);

        // VULNERABLE CHECK
        if (numElements * elementSize > MAX_MEMORY_LIMIT) {
            System.err.println("Vulnerable: [REJECTED] Request exceeds limit.");
            return;
        }

        System.out.println("Vulnerable: [APPROVED] Allocation logic would proceed here.");
        try {
            // Simulated allocation
            byte[] data = new byte[numElements * elementSize];
            System.out.println("Vulnerable: Successfully allocated " + data.length + " bytes.");
        } catch (Throwable t) {
            System.err.println("Vulnerable: [ERROR] Allocation failed: " + t.toString());
        }
    }
}
