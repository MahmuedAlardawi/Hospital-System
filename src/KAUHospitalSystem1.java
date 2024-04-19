/*
Name: Mahmued Ahmed Alardawi
ID: 21352089
Section: ZJ
Course name: Programming II
Course code: CPCS 203
Assignment number: Assignment #3 (KAU Hospital System)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class KAUHospitalSystem1 {
    public static void main(String[] args) throws IOException {
        File file = new File ("input.txt");

        if (!file.exists())
        {
            System.out.println("File 'file' not found");
            System.exit(0);
        }

        PrintWriter output = new PrintWriter("Output_copy1.txt");

        printData(
                input(file),
                output,
                getRoom(input(file)),
                getMedicine(input(file)),
                getDoctor(input(file)),
                getNurse(input(file)),
                getPatient(input(file), getDoctor(input(file)), getRoom(input(file)), getMedicine(input(file))));

        printAssignments(
                input(file),
                output,
                getDoctor(input(file)),
                getRoom(input(file)),
                getMedicine(input(file)),
                getPatient(input(file), getDoctor(input(file)), getRoom(input(file)), getMedicine(input(file))));

        printBill(
                input(file),
                output,
                getPatient(input(file), getDoctor(input(file)), getRoom(input(file)), getMedicine(input(file))));

        output.close();
    }

    //
    //

    public static Scanner input(File file) throws FileNotFoundException {
        return new Scanner(file);
    }

    //
    //

    private static Room[] getRoom(Scanner input) {
        int arrayLength = input.nextInt();
        Room[] rooms = new Room[arrayLength];
        int count = 0;

        while (input.hasNext())
        {
            if (input.next().equals("Add_Room"))
            {
                int roomNo = input.nextInt();
                String floor = input.next();
                String block = input.next();
                double charges = input.nextDouble();

                rooms[count++] = new Room(roomNo, floor, block, charges);
            }
        }
        input.close();
        return rooms;
    }

    //

    private static Medicine[] getMedicine(Scanner input) {
        int arrayLength = 0;
        for (int token = 0; token < 2; token++) {arrayLength = input.nextInt();}
        Medicine[] medicines = new Medicine[arrayLength];
        int count = 0;

        while (input.hasNext())
        {
            if (input.next().equals("Add_Medicine"))
            {
                int medicineCode = input.nextInt();
                String name = input.next();
                double price = input.nextDouble();

                medicines[count++] = new Medicine(medicineCode, name, price);
            }
        }
        input.close();
        return medicines;
    }

    //

    private static Doctor[] getDoctor(Scanner input) {
        int arrayLength = 0;
        for (int token = 0; token < 3; token++) {arrayLength = input.nextInt();}
        Doctor[] doctors = new Doctor[arrayLength];
        int count = 0;

        while (input.hasNext())
        {
            if (input.next().equals("Add_Doctor"))
            {
                double consultationFees = input.nextDouble();
                String staffID = input.next();
                String specialization = input.next();
                int id = input.nextInt();
                String name = input.next();
                String nationality = input.next();
                char gender = input.next().charAt(0);
                int phone = input.nextInt();

                doctors[count++] = new Doctor(consultationFees,  staffID, specialization, id, name, nationality,
                        gender, phone);
            }
        }
        input.close();
        return doctors;
    }

    //

    private static Nurse[] getNurse(Scanner input) {
        int arrayLength = 0;
        for (int token = 0; token < 3; token++) {arrayLength = input.nextInt();}
        Nurse[] nurses = new Nurse[arrayLength];
        int count = 0;

        while (input.hasNext())
        {
            if (input.next().equals("Add_Nurse"))
            {
                int experience = input.nextInt();
                String staffID = input.next();
                String specialization = input.next();
                int id = input.nextInt();
                String name = input.next();
                String nationality = input.next();
                char gender = input.next().charAt(0);
                int phone = input.nextInt();

                nurses[count++] = new Nurse(experience, staffID, specialization, id, name, nationality,
                        gender, phone);
            }
        }
        input.close();
        return nurses;
    }

    //

    private static Patient[] getPatient(Scanner input, Doctor[] doctors, Room[] rooms, Medicine[] medicines) {
        int arrayLength = 0;
        for (int token = 0; token < 3; token++) {arrayLength = input.nextInt();}
        Patient[] patients = new Patient[arrayLength];
        int count = 0;

        while (input.hasNext())
        {
            String token = input.next();

            if (token.equals("Add_Patient"))
            {
                String illness = input.next();
                String bloodGroup = input.next();
                int id = input.nextInt();
                String name = input.next();
                String nationality = input.next();
                char gender = input.next().charAt(0);
                int phone = input.nextInt();
                int totalMedicine = input.nextInt();

                patients[count++] = new Patient(illness, bloodGroup, id, name, nationality, gender,
                        phone, totalMedicine);
            }

            if (token.equals("Assign_Doctor_Patient"))
            {
                int doctorID = input.nextInt();
                int patientID = input.nextInt();

                for (Doctor doctor : doctors)
                {
                    if (doctor == null) {continue;}
                    if (doctorID == doctor.getId())
                    {
                        for (Patient patient : patients)
                        {
                            if (patient == null) {continue;}
                            if (patientID == patient.getId())
                            {
                                patient.setDoctor(doctor);
                            }
                        }
                    }
                }
            }

            if (token.equals("Assign_Room_Patient"))
            {
                int roomNo = input.nextInt();
                int patientID = input.nextInt();

                for (Room room : rooms)
                {
                    if (room == null) {continue;}
                    if (roomNo == room.getRoomNo())
                    {
                        for (Patient patient : patients)
                        {
                            if (patient == null) {continue;}
                            if (patientID == patient.getId())
                            {
                                patient.setRoom(room);
                            }
                        }
                    }
                }
            }

            if (token.equals("Assign_Medicine_Patient"))
            {
                int patientID = input.nextInt();
                int totalMedicine = input.nextInt();
                int[] medicinesInputs = new int[totalMedicine];
                for (int medicinesInput = 0; medicinesInput < medicinesInputs.length; medicinesInput++)
                {
                    medicinesInputs[medicinesInput] = input.nextInt();
                }

                int medicineAvailable = 0;

                for (Patient patient : patients)
                {
                    if (patient == null) {continue;}
                    if (patientID == patient.getId())
                    {
                        if (totalMedicine == patient.getTotalMedicine())
                        {
                            for (Medicine medicine : medicines)
                            {
                                for (int medicinesInput : medicinesInputs)
                                {
                                    if (medicine.getMedicineCode() == medicinesInput)
                                    {
                                        medicineAvailable++;
                                    }
                                }
                            }

                            if (medicineAvailable == totalMedicine)
                            {
                                patient.setMedicine(new Medicine[totalMedicine]);
                                for (int m = 0; m < patient.getMedicine().length; m++)
                                {
                                    for (Medicine medicine : medicines)
                                    {
                                        if (medicine.getMedicineCode() == medicinesInputs[m])
                                        {
                                            patient.getMedicine()[m] = medicine;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        input.close();
        return patients;
    }


    //
    //

    public static void printData(Scanner input, PrintWriter output, Room[] rooms, Medicine[] medicines,
                                 Doctor[] doctors, Nurse[] nurses, Patient[] patients) {
        output.println("--------------- Welcome to KAU Hospital System ---------------");

        int count_rooms = 0;
        int count_medicines = 0;
        int count_doctors = 0;
        int count_nurses = 0;
        int count_patients = 0;
        while (input.hasNext())
        {
            String token = input.next();

            if (token.equals("Add_Room"))
            {
                output.println("Command Add_Room: Add a new room record in the System");
                output.println("\tRoom No: " + rooms[count_rooms].getRoomNo());
                output.println("\tLocated in floor: " + rooms[count_rooms].getFloor());
                output.println("\tBlock :" + rooms[count_rooms].getBlock());
                output.println("\tCharges: " + rooms[count_rooms].getCharges() + "\n");
                output.println("----------------------------------------------------------------");
                count_rooms++;
            }

            if (token.equals("Add_Medicine"))
            {
                output.println("Command Add_Medicine: Add a new Medicine record in the System");
                output.println("\tMedicine Code: " + medicines[count_medicines].getMedicineCode());
                output.println("\tName: " + medicines[count_medicines].getName());
                output.println("\tPrice :" + medicines[count_medicines].getPrice() + "\n");
                output.println("----------------------------------------------------------------");
                count_medicines++;
            }

            if (token.equals("Add_Doctor"))
            {
                output.println("Command Add_Doctor: Add a new doctor record in the System");
                output.println("\tID: " + doctors[count_doctors].getId());
                output.println("\tName: " + doctors[count_doctors].getName());
                output.println("\tNationality :" + doctors[count_doctors].getNationality());
                output.println("\tGender: " + doctors[count_doctors].getGender());
                output.println("\tPhone: " + doctors[count_doctors].getPhone());
                output.println("\tSpecilaization: " + doctors[count_doctors].getSpecialization());
                output.println("\tStaff ID: " + doctors[count_doctors].getStaffID());
                output.println("\tConsultation Fees :" + doctors[count_doctors].getConsultationFees() + "\n");
                output.println("----------------------------------------------------------------");
                count_doctors++;
            }

            if (token.equals("Add_Nurse"))
            {
                output.println("Command Add_Nurse: Add a new nurse record in the System");
                output.println("\tID: " + nurses[count_nurses].getId());
                output.println("\tName: " + nurses[count_nurses].getName());
                output.println("\tNationality :" + nurses[count_nurses].getNationality());
                output.println("\tGender: " + nurses[count_nurses].getGender());
                output.println("\tPhone: " + nurses[count_nurses].getPhone());
                output.println("\tSpecilaization: " + nurses[count_nurses].getSpecialization());
                output.println("\tStaff ID: " + nurses[count_nurses].getStaffID());
                output.println("\tExperience Year: " + nurses[count_nurses].getExperience() + "\n");
                output.println("----------------------------------------------------------------");
                count_nurses++;
            }

            if (token.equals("Add_Patient"))
            {
                output.println("Command Add_Patient: ");
                output.println("Add a new Patient record in the System");
                output.println("\tID: " + patients[count_patients].getId());
                output.println("\tName: " + patients[count_patients].getName());
                output.println("\tNationality :" + patients[count_patients].getNationality());
                output.println("\tGender: " + patients[count_patients].getGender());
                output.println("\tPhone: " + patients[count_patients].getPhone());
                output.println("-------------------------------------------------------------------------" +
                        "------------------");
                output.println("Illness: " + patients[count_patients].getIllness() + ", Blood Group: " +
                        patients[count_patients].getBloodGroup());
                output.println("-------------------------------------------------------------------------" +
                        "------------------\n");
                output.println("-------------------------------------------------------------------------------");
                count_patients++;
            }
        }
        input.close();
    }

    //


    public static void printAssignments(Scanner input, PrintWriter output, Doctor[] doctors, Room[] rooms,
                                        Medicine[] medicines, Patient[] patients) {
        while (input.hasNext())
        {
            String token = input.next();

            if (token.equals("Assign_Doctor_Patient"))
            {
                int doctorID = input.nextInt();
                int patientID = input.nextInt();

                for (Doctor doctor : doctors)
                {
                    if (doctor == null) {continue;}
                    if (doctorID == doctor.getId())
                    {
                        for (Patient patient : patients)
                        {
                            if (patient == null) {continue;}
                            if (patientID == patient.getId())
                            {
                                patient.setDoctor(doctor);
                            }
                        }
                    }
                }

                output.println("Command Assign_Doctor_Patient:");

                boolean patientExist = false;

                for (Patient patient : patients)
                {
                    if (patient == null) {continue;}
                    if (patientID == patient.getId())
                    {
                        patientExist = true;
                        if (doctorID == patient.getDoctor().getId())
                        {
                            output.println("Successfully Processed by the System:\n" +
                                    "Following are the details:");
                            output.println("\t\tPatient: " + patient.getName());
                            output.println("\t\tAssigned to Doctor: " + patient.getDoctor().getName());
                        }

                        else
                        {
                            output.println("Doctor " + doctorID + " NOT FOUND ");
                        }
                    }
                }

                if (!patientExist) {output.println("Patient " + patientID + " NOT FOUND ");}
                output.println("-------------------------------------------------------------------------------");
            }

            if (token.equals("Assign_Room_Patient"))
            {
                int roomNo = input.nextInt();
                int patientID = input.nextInt();

                for (Room room : rooms)
                {
                    if (room == null) {continue;}
                    if (roomNo == room.getRoomNo())
                    {
                        for (Patient patient : patients)
                        {
                            if (patient == null) {continue;}
                            if (patientID == patient.getId())
                            {
                                patient.setRoom(room);
                            }
                        }
                    }
                }

                output.println("Command Assign_Room_Patient:");

                boolean patientExist = false;

                for (Patient patient : patients)
                {
                    if (patient == null) {continue;}
                    if (patientID == patient.getId())
                    {
                        patientExist = true;
                        if (roomNo == patient.getRoom().getRoomNo())
                        {
                            output.println("Successfully Processed by the System:\n" +
                                    "Following are the details:");
                            output.println("\tPatient: " + patient.getName());
                            output.println("\tAssigned to Room:\tRoom No: " + patient.getRoom().getRoomNo());
                            output.println("\tLocated in floor: " + patient.getRoom().getFloor());
                            output.println("\tBlock :" + patient.getRoom().getBlock());
                            output.println("\tCharges: " + patient.getRoom().getCharges());
                            output.println("\n" + "---------------------------------------------------------------------------------");
                        }

                        else
                        {
                            output.println("Room " + roomNo + " NOT FOUND ");
                            output.println("---------------------------------------------------------------------------------");
                        }
                    }
                }
                if (!patientExist)
                {
                    output.println("Patient " + patientID + " NOT FOUND ");
                    output.println("---------------------------------------------------------------------------------");
                }
            }

            if (token.equals("Assign_Medicine_Patient"))
            {
                int patientID = input.nextInt();
                int totalMedicine = input.nextInt();
                int[][] medicinesInputs = new int[totalMedicine][2];
                for (int medicinesInput = 0; medicinesInput < medicinesInputs.length; medicinesInput++)
                {
                    medicinesInputs[medicinesInput][0] = input.nextInt();
                }

                int medicineAvailable = 0;

                for (Patient patient : patients)
                {
                    if (patient == null) {continue;}
                    if (patientID == patient.getId())
                    {
                        if (totalMedicine == patient.getTotalMedicine())
                        {
                            for (Medicine medicine : medicines)
                            {
                                for (int[] medicinesInput : medicinesInputs)
                                {
                                    if (medicine.getMedicineCode() == medicinesInput[0])
                                    {
                                        medicineAvailable++;
                                    }
                                }
                            }

                            if (medicineAvailable == totalMedicine)
                            {
                                patient.setMedicine(new Medicine[totalMedicine]);
                                for (int m = 0; m < patient.getMedicine().length; m++)
                                {
                                    for (Medicine medicine : medicines)
                                    {
                                        if (medicine.getMedicineCode() == medicinesInputs[m][0])
                                        {
                                            patient.getMedicine()[m] = medicine;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                output.println("Command Assign_Medicine_Patient:");

                boolean patientExist = false;

                int availableMedicine = 0;
                for (Patient patient : patients)
                {
                    if (patient == null) {continue;}
                    if (patientID == patient.getId())
                    {
                        patientExist = true;
                        if (totalMedicine == patient.getTotalMedicine())
                        {
                            for (int medicine = 0; medicine < totalMedicine; medicine++)
                            {
                                if (medicinesInputs[medicine][0] == patient.getMedicine()[medicine].getMedicineCode())
                                {
                                    medicinesInputs[medicine][1] = 1;
                                    availableMedicine++;
                                }
                            }

                            if (availableMedicine == totalMedicine)
                            {
                                output.println("Successfully Processed by the System:\n" +
                                               "Following are the details:");
                                output.println("\tPatient: " + patient.getName());
                                for (Medicine medicine : patient.getMedicine())
                                {
                                    output.println("\tMedicine Prescribed: " + medicine.getName());
                                }
                            }

                            else
                            {
                                for (int[] ints : medicinesInputs) {
                                    if (ints[1] == 0) {
                                        output.println("Medicines " + ints[0] + " NOT FOUND ");
                                    }
                                }
                            }
                        }

                        else
                        {
                            output.println("Information mismatch: Patient " + patientID + "must have " +
                                           patient.getTotalMedicine() + " medicine not " + totalMedicine);
                        }
                    }
                }

                if (!patientExist) {output.println("Patient " + patientID + " NOT FOUND ");}
                output.println("-----------------------------------------------------------------------------");
            }
        }
        input.close();
    }

    //

    public static void printBill(Scanner input, PrintWriter output, Patient[] patients) {
        while (input.hasNext())
        {
            String token = input.next();

            if (token.equals("Print_Bill"))
            {
                Date date = new Date();

                output.println("\n" +
                        "Command: Print_Bill\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "--------------- Welcome to KAU Hospital System ---------------\n" +
                        "\n" +
                        "--------- Current Date :  " + date + "--------\n" +
                        "\n" +
                        "==================================================================");

                int[] patientsIDs = new int[patients.length];

                for (int index = 0; index < patientsIDs.length; index++)
                {
                    if (patients[index] == null) {continue;}

                    patientsIDs[index] = patients[index].getId();
                }

                Arrays.sort(patientsIDs);

                for (int patientsID : patientsIDs)
                {
                    for (Patient patient : patients)
                    {
                        if (patient == null) {continue;}
                        if (patientsID == patient.getId())
                        {
                            double totalCharges = 0;

                            output.println("Patient Detail: ");
                            output.println(" ID: " + patient.getId() + " Name: " + patient.getName());
                            output.println("\tDoctor : " + patient.getDoctor().getName() +
                                    "   Doctor Consultation Fees: " + patient.getDoctor().getConsultationFees() +
                                    " SR\t");

                            totalCharges += patient.getDoctor().getConsultationFees();

                            output.println("\tMedicines  are:  ");

                            for (Medicine medicine : patient.getMedicine())
                            {
                                output.printf("%-30s%-15s\n", "\tMedicine name: " + medicine.getName(), "Price: "
                                        + medicine.getPrice() + " SR");
                                totalCharges += medicine.getPrice();
                            }

                            output.println("\tRoom: " + patient.getRoom().getRoomNo() + " Room Charge: " +
                                    patient.getRoom().getCharges() + " SR   ");

                            totalCharges += patient.getRoom().getCharges();

                            output.println("\t\t==== Total Charges :" + (totalCharges) + " SR ====");
                            output.println("----------------------------------------------------------------");
                        }
                    }
                }
                output.println("Thank you for using KAU Hospital System, Good Bye!");
            }
        }
    }
}
