package com.hospital_management_system.model;

public class InventoryItem {
    private int itemId;
    private String itemName;
    private int quantity;
    private String supplier;

    public InventoryItem(int itemId, String itemName, int quantity, String supplier) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }

    @Override
    public String toString() {
        return "InventoryItem [ID=" + itemId + ", Name=" + itemName +
               ", Quantity=" + quantity + ", Supplier=" + supplier + "]";
    }
}
