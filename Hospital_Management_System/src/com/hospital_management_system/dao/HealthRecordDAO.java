package com.hospital_management_system.dao;

import com.hospital_management_system.model.*;
import com.hospital_management_system.utils.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HealthRecordDAO {
    // Add a new health record
    public boolean addHealthRecord(HealthRecord record) throws SQLException {
        String sql = "INSERT INTO health_records (patient_id, diagnosis, treatment) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, record.getPatientId());
            stmt.setString(2, record.getDiagnosis());
            stmt.setString(3, record.getTreatment());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Get all health records for a patient
    public List<HealthRecord> getHealthRecordsByPatient(int patientId) throws SQLException {
        List<HealthRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM health_records WHERE patient_id=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    HealthRecord record = new HealthRecord(
                        rs.getInt("record_id"),
                        rs.getInt("patient_id"),
                        rs.getString("diagnosis"),
                        rs.getString("treatment")
                    );
                    records.add(record);
                }
            }
        }
        return records;
    }
    
    // Update a health record
    public boolean updateHealthRecord(HealthRecord record) throws SQLException {
        String sql = "UPDATE health_records SET diagnosis=?, treatment=? WHERE record_id=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, record.getDiagnosis());
            stmt.setString(2, record.getTreatment());
            stmt.setInt(3, record.getRecordId());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    public HealthRecord getHealthRecordById(int id) throws SQLException {
        String sql = "SELECT * FROM health_records WHERE record_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new HealthRecord(
                        rs.getInt("record_id"),
                        rs.getInt("patient_id"),
                        rs.getString("diagnosis"),
                        rs.getString("treatment")
                    );
                }
            }
        }
        return null;
    }
}