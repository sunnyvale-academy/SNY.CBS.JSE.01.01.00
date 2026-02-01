import java.io.Serializable;

/**
 * Harmless serializable class intended for communication.
 */
public class DataPacket implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String message;

    public DataPacket(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DataPacket[message=" + message + "]";
    }
}
