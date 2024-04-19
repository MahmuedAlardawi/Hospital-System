/*
Name: Mahmued Ahmed Alardawi
ID: 21352089
Section: ZJ
Course name: Programming II
Course code: CPCS 203
Assignment number: Assignment #3 (KAU Hospital System)
 */


public abstract class Staff extends Person {
    private String specialization;
    private String staffID;

    //

    public Staff(int id, String name, String nationality, char gender, int phone, String specialization, String staffID) {
        super(id, name, nationality, gender, phone);
        this.specialization = specialization;
        this.staffID = staffID;
    }

    //

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    //

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", gender=" + gender +
                ", phone=" + phone +
                ", specialization='" + specialization + '\'' +
                ", staffID='" + staffID + '\'' +
                '}';
    }
}
