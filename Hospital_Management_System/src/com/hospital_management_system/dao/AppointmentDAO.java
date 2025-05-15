package com.hospital_management_system.dao;

import com.hospital_management_system.model.*;
import com.hospital_management_system.utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    // Schedule a new appointment
    public boolean scheduleAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments (patient_id, doctor_name, appointment_date) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, appointment.getPatientId());
            stmt.setString(2, appointment.getDoctorName());
            stmt.setDate(3, new java.sql.Date(appointment.getAppointmentDate().getTime()));
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Get all appointments
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Appointment appointment = new Appointment(
                    rs.getInt("appointment_id"),
                    rs.getInt("patient_id"),
                    rs.getDate("appointment_date"),
                    rs.getString("doctor_name")
                );
                appointments.add(appointment);
            }
        }
        return appointments;
    }
    
    // Get appointments by patient ID
    public List<Appointment> getAppointmentsByPatient(int patientId) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments WHERE patient_id=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, patientId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Appointment appointment = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name")
                    );
                    appointments.add(appointment);
                }
            }
        }
        return appointments;
    }
    
    // Cancel an appointment
    public boolean cancelAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE appointment_id=?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, appointmentId);
            return stmt.executeUpdate() > 0;
        }
    }
}