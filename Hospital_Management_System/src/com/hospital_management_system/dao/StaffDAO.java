package com.hospital_management_system.dao;

import com.hospital_management_system.model.*;
import com.hospital_management_system.utils.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {
    
    // Add new staff member
    public boolean addStaff(Staff staff) throws SQLException {
        String sql = "INSERT INTO staff (name, role, phone) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getRole());
            stmt.setString(3, staff.getPhone());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Get staff by ID
    public Staff getStaffById(int staffId) throws SQLException {
        String sql = "SELECT * FROM staff WHERE staff_id=?";
        Staff staff = null;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, staffId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    staff = new Staff(
                        rs.getInt("staff_id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("phone")
                    );
                }
            }
        }
        return staff;
    }
    
    // Get all staff members
    public List<Staff> getAllStaff() throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Staff staff = new Staff(
                    rs.getInt("staff_id"),
                    rs.getString("name"),
                    rs.getString("role"),
                    rs.getString("phone")
                );
                staffList.add(staff);
            }
        }
        return staffList;
    }
    
    // Get staff by role
    public List<Staff> getStaffByRole(String role) throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE role=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, role);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Staff staff = new Staff(
                        rs.getInt("staff_id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("phone")
                    );
                    staffList.add(staff);
                }
            }
        }
        return staffList;
    }
    
    // Update staff information
    public boolean updateStaff(Staff staff) throws SQLException {
        String sql = "UPDATE staff SET name=?, role=?, phone=? WHERE staff_id=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, staff.getName());
            stmt.setString(2, staff.getRole());
            stmt.setString(3, staff.getPhone());
            stmt.setInt(4, staff.getStaffId());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Delete staff member
    public boolean deleteStaff(int staffId) throws SQLException {
        String sql = "DELETE FROM staff WHERE staff_id=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, staffId);
            return stmt.executeUpdate() > 0;
        }
    }
}
