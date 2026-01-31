import java.util.List;

/**
 * ProcessorCallback interface.
 * Defines a method to handle a list of processed data.
 */
public interface ProcessorCallback {
    void handleData(List<String> data);
}
