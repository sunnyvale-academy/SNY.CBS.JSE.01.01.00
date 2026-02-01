import java.io.Serializable;

/**
 * A harmless serializable task intended for processing by the TaskProcessor.
 */
public class CommonTask implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String description;

    public CommonTask(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CommonTask[description=" + description + "]";
    }
}
