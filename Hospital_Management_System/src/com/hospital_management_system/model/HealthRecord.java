package com.hospital_management_system.model;

public class HealthRecord {
    private int recordId;
    private int patientId;
    private String diagnosis;
    private String treatment;

    public HealthRecord(int recordId, int patientId, String diagnosis, String treatment) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    @Override
    public String toString() {
        return "HealthRecord [ID=" + recordId + ", PatientID=" + patientId +
               ", Diagnosis=" + diagnosis + ", Treatment=" + treatment + "]";
    }
}
