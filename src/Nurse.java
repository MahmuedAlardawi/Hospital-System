/*
Name: Mahmued Ahmed Alardawi
ID: 21352089
Section: ZJ
Course name: Programming II
Course code: CPCS 203
Assignment number: Assignment #3 (KAU Hospital System)
 */


public class Nurse extends Staff {
    private int experience;

    //

    public Nurse(int experience, String staffID, String specialization, int id, String name,
                 String nationality, char gender, int phone) {
        super(id, name, nationality, gender, phone, specialization, staffID);
        this.experience = experience;
    }

    //

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    //

    @Override
    public String toString() {
        return "Nurse{" +
                "experience=" + experience +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", gender=" + gender +
                ", phone=" + phone +
                '}';
    }
}
