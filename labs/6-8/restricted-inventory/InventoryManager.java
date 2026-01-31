import java.util.ArrayList;
import java.util.List;

/**
 * VULNERABLE CLASS: InventoryManager
 * Manages an internal list of inventory items.
 * VIOLATION: Exposes the internal mutable collection directly to callers.
 */
public class InventoryManager {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    /**
     * VULNERABILITY: Returns the actual internal list reference.
     * Guideline 6-8 Violation: Modifiable internal state should be wrapped.
     */
    public List<Item> getItems() {
        return items;
    }

    public void printInventory() {
        System.out.println("Inventory Contents: " + items);
    }
}
