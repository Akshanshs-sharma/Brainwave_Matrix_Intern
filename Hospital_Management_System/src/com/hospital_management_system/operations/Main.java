package com.hospital_management_system.operations;

import com.hospital_management_system.dao.AppointmentDAO;
import com.hospital_management_system.dao.BillingDAO;
import com.hospital_management_system.dao.HealthRecordDAO;
import com.hospital_management_system.dao.InventoryDAO;
import com.hospital_management_system.dao.PatientDAO;
import com.hospital_management_system.dao.StaffDAO;
import com.hospital_management_system.model.*;
import com.hospital_management_system.utils.*;




import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
 private static final Scanner scanner = new Scanner(System.in);
 private static final PatientDAO patientDAO = new PatientDAO();
 private static final AppointmentDAO appointmentDAO = new AppointmentDAO();
 private static final HealthRecordDAO healthRecordDAO = new HealthRecordDAO();
 private static final BillingDAO billingDAO = new BillingDAO();
 private static final InventoryDAO inventoryDAO = new InventoryDAO();
 private static final StaffDAO staffDAO = new StaffDAO();

 public static void main(String[] args) {
     boolean exit = false;
     while (!exit) {
         printMenu();
         int choice = Integer.parseInt(scanner.nextLine());
         try {
             switch (choice) {
                 case 1 -> managePatients();
                 case 2 -> manageAppointments();
                 case 3 -> manageHealthRecords();
                 case 4 -> manageBilling();
                 case 5 -> manageInventory();
                 case 6 -> manageStaff();
                 case 0 -> exit = true;
                 default -> System.out.println("Invalid choice. Please try again.");
             }
         } catch (SQLException e) {
             System.err.println("Database error: " + e.getMessage());
         }
     }
     DBConnection.closeConnection(null);
     scanner.close();
     System.out.println("Exiting system. Goodbye!");
 }

 private static void printMenu() {
     System.out.println("=== Hospital Management System ===");
     System.out.println("1. Patient Registration");
     System.out.println("2. Appointment Scheduling");
     System.out.println("3. Manage Health Records");
     System.out.println("4. Billing and Invoicing");
     System.out.println("5. Inventory Management");
     System.out.println("6. Staff Management");
     System.out.println("0. Exit");
     System.out.print("Enter your choice: ");
 }

 // 1. Patients
 private static void managePatients() throws SQLException {
     System.out.println("-- Patient Management --");
     System.out.println("a. Add Patient");
     System.out.println("b. View All Patients");
     System.out.println("c. Update Patient");
     System.out.println("d. Delete Patient");
     System.out.print("Choose an option: ");
     String opt = scanner.nextLine();
     switch (opt) {
         case "a" -> {
             System.out.print("Name: "); String name = scanner.nextLine();
             System.out.print("Age: "); int age = Integer.parseInt(scanner.nextLine());
             System.out.print("Gender: "); String gender = scanner.nextLine();
             System.out.print("Phone: "); String phone = scanner.nextLine();
             System.out.print("Address: "); String address = scanner.nextLine();
             Patient p = new Patient(0, name, age, gender, phone, address);
             boolean added = patientDAO.addPatient(p);
             System.out.println(added ? "Patient added." : "Failed to add patient.");
         }
         case "b" -> {
             List<Patient> patients = patientDAO.getAllPatients();
             patients.forEach(System.out::println);
         }
         case "c" -> {
             System.out.print("Patient ID to update: "); int id = Integer.parseInt(scanner.nextLine());
             Patient existing = patientDAO.getPatientById(id);
             if (existing != null) {
                 System.out.print("New Name ("+existing.getName()+"): "); String nn = scanner.nextLine();
                 if (!nn.isBlank()) existing.setName(nn);
                 System.out.print("New Age ("+existing.getAge()+"): "); String na = scanner.nextLine();
                 if (!na.isBlank()) existing.setAge(Integer.parseInt(na));
                 System.out.print("New Phone ("+existing.getPhone()+"): "); String np = scanner.nextLine();
                 if (!np.isBlank()) existing.setPhone(np);
                 boolean updated = patientDAO.updatePatient(existing);
                 System.out.println(updated ? "Patient updated." : "Update failed.");
             } else {
                 System.out.println("Patient not found.");
             }
         }
         case "d" -> {
             System.out.print("Patient ID to delete: "); int did = Integer.parseInt(scanner.nextLine());
             boolean deleted = patientDAO.deletePatient(did);
             System.out.println(deleted ? "Patient deleted." : "Delete failed.");
         }
         default -> System.out.println("Invalid option.");
     }
 }

 // 2. Appointments
 private static void manageAppointments() throws SQLException {
     System.out.println("-- Appointment Management --");
     System.out.println("a. Schedule Appointment");
     System.out.println("b. View All Appointments");
     System.out.println("c. View by Patient");
     System.out.println("d. Cancel Appointment");
     System.out.print("Choose an option: ");
     String opt = scanner.nextLine();
     switch (opt) {
         case "a" -> {
             System.out.print("Patient ID: "); int pid = Integer.parseInt(scanner.nextLine());
             System.out.print("Doctor Name: "); String doc = scanner.nextLine();
             System.out.print("Date (yyyy-mm-dd): "); java.sql.Date date = java.sql.Date.valueOf(scanner.nextLine());
             Appointment a = new Appointment(0, pid, date, doc);
             boolean sch = appointmentDAO.scheduleAppointment(a);
             System.out.println(sch ? "Scheduled." : "Scheduling failed.");
         }
         case "b" -> {
             List<Appointment> list = appointmentDAO.getAllAppointments();
             list.forEach(System.out::println);
         }
         case "c" -> {
             System.out.print("Patient ID: "); int pid2 = Integer.parseInt(scanner.nextLine());
             List<Appointment> list2 = appointmentDAO.getAppointmentsByPatient(pid2);
             list2.forEach(System.out::println);
         }
         case "d" -> {
             System.out.print("Appointment ID to cancel: "); int aid = Integer.parseInt(scanner.nextLine());
             boolean can = appointmentDAO.cancelAppointment(aid);
             System.out.println(can ? "Cancelled." : "Cancel failed.");
         }
         default -> System.out.println("Invalid option.");
     }
 }

 // 3. Health Records
 private static void manageHealthRecords() throws SQLException {
     System.out.println("-- Health Records --");
     System.out.println("a. Add Record");
     System.out.println("b. View by Patient");
     System.out.println("c. Update Record");
     System.out.print("Choose an option: ");
     String opt = scanner.nextLine();
     switch (opt) {
         case "a" -> {
             System.out.print("Patient ID: "); int pid = Integer.parseInt(scanner.nextLine());
             System.out.print("Diagnosis: "); String diag = scanner.nextLine();
             System.out.print("Treatment: "); String treat = scanner.nextLine();
             HealthRecord hr = new HealthRecord(0, pid, diag, treat);
             boolean added = healthRecordDAO.addHealthRecord(hr);
             System.out.println(added ? "Record added." : "Add failed.");
         }
         case "b" -> {
             System.out.print("Patient ID: "); int pid2 = Integer.parseInt(scanner.nextLine());
             List<HealthRecord> recs = healthRecordDAO.getHealthRecordsByPatient(pid2);
             recs.forEach(System.out::println);
         }
         case "c" -> {
             System.out.print("Record ID to update: "); int rid = Integer.parseInt(scanner.nextLine());
             HealthRecord existing = healthRecordDAO.getHealthRecordById(rid);
             if (existing != null) {
                 System.out.print("New Diagnosis ("+existing.getDiagnosis()+"): "); String nd = scanner.nextLine();
                 if (!nd.isBlank()) existing.setDiagnosis(nd);
                 System.out.print("New Treatment ("+existing.getTreatment()+"): "); String nt = scanner.nextLine();
                 if (!nt.isBlank()) existing.setTreatment(nt);
                 boolean upd = healthRecordDAO.updateHealthRecord(existing);
                 System.out.println(upd ? "Updated." : "Update failed.");
             } else {
                 System.out.println("Record not found.");
             }
         }
         default -> System.out.println("Invalid option.");
     }
 }

 // 4. Billing
 private static void manageBilling() throws SQLException {
     System.out.println("-- Billing --");
     System.out.println("a. Add Bill");
     System.out.println("b. View Bills by Patient");
     System.out.println("c. View Total Due");
     System.out.print("Choose an option: ");
     String opt = scanner.nextLine();
     switch (opt) {
         case "a" -> {
             System.out.print("Patient ID: "); int pid = Integer.parseInt(scanner.nextLine());
             System.out.print("Amount: "); double amt = Double.parseDouble(scanner.nextLine());
             Billing bill = new Billing(0, pid, amt, new java.sql.Date(new Date().getTime()));
             boolean bAdded = billingDAO.addBill(bill);
             System.out.println(bAdded ? "Bill added." : "Add failed.");
         }
         case "b" -> {
             System.out.print("Patient ID: "); int pid2 = Integer.parseInt(scanner.nextLine());
             List<Billing> bills = billingDAO.getBillsByPatient(pid2);
             bills.forEach(System.out::println);
         }
         case "c" -> {
             System.out.print("Patient ID: "); int pid3 = Integer.parseInt(scanner.nextLine());
             double total = billingDAO.getTotalAmountDue(pid3);
             System.out.println("Total Due: " + total);
         }
         default -> System.out.println("Invalid option.");
     }
 }

 // 5. Inventory
 private static void manageInventory() throws SQLException {
     System.out.println("-- Inventory --");
     System.out.println("a. Add Item");
     System.out.println("b. View All Items");
     System.out.println("c. Update Quantity");
     System.out.println("d. Low Stock Report");
     System.out.print("Choose an option: ");
     String opt = scanner.nextLine();
     switch (opt) {
         case "a" -> {
             System.out.print("Item Name: "); String name = scanner.nextLine();
             System.out.print("Quantity: "); int qty = Integer.parseInt(scanner.nextLine());
             System.out.print("Supplier: "); String sup = scanner.nextLine();
             InventoryItem item = new InventoryItem(0, name, qty, sup);
             boolean iAdded = inventoryDAO.addInventoryItem(item);
             System.out.println(iAdded ? "Item added." : "Add failed.");
         }
         case "b" -> {
             List<InventoryItem> items = inventoryDAO.getAllInventoryItems();
             items.forEach(System.out::println);
         }
         case "c" -> {
             System.out.print("Item ID: "); int iid = Integer.parseInt(scanner.nextLine());
             System.out.print("New Quantity: "); int nq = Integer.parseInt(scanner.nextLine());
             boolean updated = inventoryDAO.updateInventoryQuantity(iid, nq);
             System.out.println(updated ? "Quantity updated." : "Update failed.");
         }
         case "d" -> {
             System.out.print("Threshold: "); int th = Integer.parseInt(scanner.nextLine());
             List<InventoryItem> low = inventoryDAO.getLowStockItems(th);
             low.forEach(System.out::println);
         }
         default -> System.out.println("Invalid option.");
     }
 }

 // 6. Staff
 private static void manageStaff() throws SQLException {
     System.out.println("-- Staff Management --");
     System.out.println("a. Add Staff");
     System.out.println("b. View All Staff");
     System.out.println("c. Update Staff");
     System.out.println("d. View by Role");
     System.out.print("Choose an option: ");
     String opt = scanner.nextLine();
     switch (opt) {
         case "a" -> {
             System.out.print("Name: "); String name = scanner.nextLine();
             System.out.print("Role: "); String role = scanner.nextLine();
             System.out.print("Phone: "); String phone = scanner.nextLine();
             Staff s = new Staff(0, name, role, phone);
             boolean sAdded = staffDAO.addStaff(s);
             System.out.println(sAdded ? "Staff added." : "Add failed.");
         }
         case "b" -> {
             List<Staff> all = staffDAO.getAllStaff();
             all.forEach(System.out::println);
         }
         case "c" -> {
             System.out.print("Staff ID to update: "); int sid = Integer.parseInt(scanner.nextLine());
             Staff existing = staffDAO.getStaffById(sid);
             if (existing != null) {
                 System.out.print("New Name ("+existing.getName()+"): "); String nname = scanner.nextLine();
                 if (!nname.isBlank()) existing.setName(nname);
                 System.out.print("New Role ("+existing.getRole()+"): "); String nrole = scanner.nextLine();
                 if (!nrole.isBlank()) existing.setRole(nrole);
                 boolean supd = staffDAO.updateStaff(existing);
                 System.out.println(supd ? "Staff updated." : "Update failed.");
             } else {
                 System.out.println("Staff not found.");
             }
         }
         case "d" -> {
             System.out.print("Role: "); String r = scanner.nextLine();
             List<Staff> byRole = staffDAO.getStaffByRole(r);
             byRole.forEach(System.out::println);
         }
         default -> System.out.println("Invalid option.");
     }
 }
}
