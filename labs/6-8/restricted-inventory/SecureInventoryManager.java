import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SECURE CLASS: SecureInventoryManager
 * Protects its internal state using wrapper methods and unmodifiable views.
 */
public class SecureInventoryManager {
    private final List<Item> items = new ArrayList<>();

    /**
     * WRAPPER METHOD: Validation is performed here.
     */
    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        // Additional business logic could go here (e.g. quantity checks)
        items.add(item);
    }

    /**
     * FIX: Guideline 6-8 / MUTABLE-8: Define wrapper methods around modifiable
     * internal state.
     * We return an unmodifiable view instead of the raw list.
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void printInventory() {
        System.out.println("Inventory Contents: " + items);
    }
}
