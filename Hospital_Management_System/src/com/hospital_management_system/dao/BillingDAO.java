package com.hospital_management_system.dao;

import com.hospital_management_system.model.*;
import com.hospital_management_system.utils.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {
    // Create a new bill
    public boolean addBill(Billing bill) throws SQLException {
        String sql = "INSERT INTO billing (patient_id, amount, bill_date) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, bill.getPatientId());
            stmt.setDouble(2, bill.getAmount());
            stmt.setDate(3, new java.sql.Date(bill.getBillDate().getTime()));
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Get all bills for a patient
    public List<Billing> getBillsByPatient(int patientId) throws SQLException {
        List<Billing> bills = new ArrayList<>();
        String sql = "SELECT * FROM billing WHERE patient_id=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Billing bill = new Billing(
                        rs.getInt("bill_id"),
                        rs.getInt("patient_id"),
                        rs.getDouble("amount"),
                        rs.getDate("bill_date")
                    );
                    bills.add(bill);
                }
            }
        }
        return bills;
    }
    
    // Get total amount due for a patient
    public double getTotalAmountDue(int patientId) throws SQLException {
        String sql = "SELECT SUM(amount) AS total FROM billing WHERE patient_id=?";
        double total = 0.0;
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getDouble("total");
                }
            }
        }
        return total;
    }
}