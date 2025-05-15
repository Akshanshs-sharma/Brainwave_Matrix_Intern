package com.hospital_management_system.model;

import java.sql.Date;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private Date appointmentDate;
    private String doctorName;

    public Appointment(int appointmentId, int patientId, Date appointmentDate, String doctorName) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.doctorName = doctorName;
    }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public Date getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(Date appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    @Override
    public String toString() {
        return "Appointment [ID=" + appointmentId + ", PatientID=" + patientId +
               ", Date=" + appointmentDate + ", Doctor=" + doctorName + "]";
    }
}
