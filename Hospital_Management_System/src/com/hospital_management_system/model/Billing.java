package com.hospital_management_system.model;

import java.sql.Date;

public class Billing {
    private int billId;
    private int patientId;
    private double amount;
    private Date billDate;

    public Billing(int billId, int patientId, double amount, Date billDate) {
        this.billId = billId;
        this.patientId = patientId;
        this.amount = amount;
        this.billDate = billDate;
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getBillDate() { return billDate; }
    public void setBillDate(Date billDate) { this.billDate = billDate; }

    @Override
    public String toString() {
        return "Billing [ID=" + billId + ", PatientID=" + patientId +
               ", Amount=" + amount + ", Date=" + billDate + "]";
    }
}
