/*
Name: Mahmued Ahmed Alardawi
ID: 21352089
Section: ZJ
Course name: Programming II
Course code: CPCS 203
Assignment number: Assignment #3 (KAU Hospital System)
 */


import java.util.Arrays;

public class Patient extends Person {
    private String illness;
    private String bloodGroup;
    private Doctor doctor;
    private Medicine[] medicine;
    private Room room;
    private int totalMedicine;

    //

    public Patient(String illness, String bloodGroup, Doctor doctor, Medicine[] medicine, Room room,
                   int totalMedicine, int id, String name, String nationality, char gender, int phone) {
        super(id, name, nationality, gender, phone);
        this.illness = illness;
        this.bloodGroup = bloodGroup;
        this.doctor = doctor;
        this.medicine = medicine;
        this.room = room;
        this.totalMedicine = totalMedicine;
    }

    public Patient(String illness, String bloodGroup, int id, String name, String nationality, char gender,
                   int phone, int totalMedicine) {
        super(id, name, nationality, gender, phone);
        this.illness = illness;
        this.bloodGroup = bloodGroup;
        this.totalMedicine = totalMedicine;
    }

    //

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Medicine[] getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine[] medicine) {
        this.medicine = medicine;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getTotalMedicine() {
        return totalMedicine;
    }

    public void setTotalMedicine(int totalMedicine) {
        this.totalMedicine = totalMedicine;
    }

    //

    @Override
    public String toString() {
        return "Patient{" +
                "illness='" + illness + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", doctor=" + doctor +
                ", medicine=" + Arrays.toString(medicine) +
                ", room=" + room +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", gender=" + gender +
                ", phone=" + phone +
                '}';
    }
}
