/**
 * Item class.
 * Represents an item in the inventory.
 */
public class Item {
    private final String name;
    private final int quantity;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return name + " (x" + quantity + ")";
    }
}
