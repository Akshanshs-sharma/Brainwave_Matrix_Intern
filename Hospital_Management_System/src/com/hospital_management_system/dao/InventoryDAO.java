package com.hospital_management_system.dao;

import com.hospital_management_system.model.*;
import com.hospital_management_system.utils.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    // Add a new inventory item
    public boolean addInventoryItem(InventoryItem item) throws SQLException {
        String sql = "INSERT INTO inventory (item_name, quantity, supplier) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, item.getItemName());
            stmt.setInt(2, item.getQuantity());
            stmt.setString(3, item.getSupplier());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Get all inventory items
    public List<InventoryItem> getAllInventoryItems() throws SQLException {
        List<InventoryItem> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                InventoryItem item = new InventoryItem(
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    rs.getString("supplier")
                );
                items.add(item);
            }
        }
        return items;
    }
    
    // Update inventory item quantity
    public boolean updateInventoryQuantity(int itemId, int newQuantity) throws SQLException {
        String sql = "UPDATE inventory SET quantity=? WHERE item_id=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, itemId);
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Get low stock items (quantity < threshold)
    public List<InventoryItem> getLowStockItems(int threshold) throws SQLException {
        List<InventoryItem> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory WHERE quantity < ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, threshold);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    InventoryItem item = new InventoryItem(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getInt("quantity"),
                        rs.getString("supplier")
                    );
                    items.add(item);
                }
            }
        }
        return items;
    }
}