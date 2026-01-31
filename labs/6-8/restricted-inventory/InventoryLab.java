public class InventoryLab {
    public static void main(String[] args) {
        System.out.println("--- Lab 6-8: The Secure Inventory ---");

        InventoryManager manager = new InventoryManager();
        manager.addItem(new Item("Laptop", 5));
        manager.addItem(new Item("Monitor", 10));

        System.out.println("\nInitial Inventory:");
        manager.printInventory();

        // Pass the manager to the exploit
        System.out.println("\nPassing manager to untrusted code...");
        InventoryExploit.sabotage(manager);

        System.out.println("\nVerification of Inventory state:");
        manager.printInventory();

        System.out.println("\nCheck: Was the internal collection protected? NO (Vulnerable)");
    }
}
