package com.hospital_management_system.model;

public class Patient {
    private int patientId;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String address;

    public Patient(int patientId, String name, int age, String gender, String phone, String address) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Patient [ID=" + patientId + ", Name=" + name + ", Age=" + age +
               ", Gender=" + gender + ", Phone=" + phone + ", Address=" + address + "]";
    }
}
