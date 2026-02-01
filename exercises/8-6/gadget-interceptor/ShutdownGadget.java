import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * DANGEROUS CLASS: ShutdownGadget
 * Simulates a malicious gadget that performs a destructive action during
 * deserialization.
 */
public class ShutdownGadget implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Payload execution triggered during deserialization.
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        System.out.println("\n[!] CRITICAL: SHUTDOWN GADGET DETECTED!");
        System.out.println("[!] Initiating simulated system shutdown...");
        System.out.println("[!] System.exit(1) would be here in a real scenario.\n");
    }
}
