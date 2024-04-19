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

public class KAUHospitalSystem2 {
    public static void main(String[] args) throws IOException {
        File file = new File ("input.txt");
        // accessing a file.

        if (!file.exists())
        {
            System.out.println("File 'file' not found");
            System.exit(0);
        }
        // checking if the file exist to evade errors.

        PrintWriter output = new PrintWriter("Output_copy2.txt");
        // to print to a new file or existing file.

        printData
                (
                input(file),
                output,
                getRoom(input(file)),
                getMedicine(input(file)),
                getDoctor(input(file)),
                getNurse(input(file)),
                getPatient(input(file))
                );

        printAssignments
                (
                input(file),
                output,
                getDoctor(input(file)),
                getRoom(input(file)),
                getMedicine(input(file)),
                getPatient(input(file))
                );


        printBill
                (
                input(file),
                output,
                getPatientFull
                        (
                        input(file),
                        getPatient(input(file)),
                        getDoctor(input(file)),
                        getRoom(input(file)),
                        getMedicine(input(file))
                        )
                );

        output.close();
        // closing the output to print the data.
    }

    //
    //

    public static Scanner input(File file) throws FileNotFoundException {
        return new Scanner(file);
    }
    // returns a scanner to scan the data from the file.

    //
    //

    private static Room[] getRoom(Scanner input) {
        int arrayLength = input.nextInt();
        // getting the array length.

        Room[] rooms = new Room[arrayLength];
        int count = 0;

        while (input.hasNext())
        {
            if (input.next().equals("Add_Room"))
            // finding the command 'Add_Room'.
            {
                int roomNo = input.nextInt();
                String floor = input.next();
                String block = input.next();
                double charges = input.nextDouble();

                rooms[count++] = new Room(roomNo, floor, block, charges);
                // creating a Room object in the rooms array.
            }
        }
        input.close();
        // closing the input to access it.

        return rooms;
    }
    // returns an array of Room objects.

    //

    private static Medicine[] getMedicine(Scanner input) {
        int arrayLength = 0;
        for (int token = 0; token < 2; token++) {arrayLength = input.nextInt();}
        // getting the array length.

        Medicine[] medicines = new Medicine[arrayLength];
        int count = 0;

        while (input.hasNext())
        {
            if (input.next().equals("Add_Medicine"))
            // finding the command 'Add_Medicine'.
            {
                int medicineCode = input.nextInt();
                String name = input.next();
                double price = input.nextDouble();

                medicines[count++] = new Medicine(medicineCode, name, price);
                // creating the Medicine object in the medicines array.
            }
        }
        input.close();
        // closing the input to access it.

        return medicines;
    }
    // returns an array of Medicine objects.

    //

    private static Doctor[] getDoctor(Scanner input) {
        int arrayLength = 0;
        for (int token = 0; token < 3; token++) {arrayLength = input.nextInt();}
        // getting the array length.

        Doctor[] doctors = new Doctor[arrayLength];
        int count = 0;

        while (input.hasNext())
        {
            if (input.next().equals("Add_Doctor"))
            // finding the command 'Add_Doctor'.
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
                // creating a Doctor object in the doctors array.
            }
        }

        int realLength = 0;
        for (Doctor doctor : doctors)
        {
            if (doctor != null) {realLength++;}
        }

        Doctor[] realDoctors = new Doctor[realLength];

        System.arraycopy(doctors, 0, realDoctors, 0, realLength);
        // copying the array with nulls (doctors) to a new array without nulls (realDoctors).

        input.close();
        // closing the input to access it.

        return realDoctors;
    }
    // returns an array of Doctor objects.

    //

    private static Nurse[] getNurse(Scanner input) {
        int arrayLength = 0;
        for (int token = 0; token < 3; token++) {arrayLength = input.nextInt();}
        // getting the array length.

        Nurse[] nurses = new Nurse[arrayLength];
        int count = 0;

        while (input.hasNext())
        {
            if (input.next().equals("Add_Nurse"))
            // finding the command 'Add_Nurse'.
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
                // creating a Nurse object in the nurses array.
            }
        }

        int realLength = 0;
        for (Nurse nurse : nurses)
        {
            if (nurse != null) {realLength++;}
        }

        Nurse[] realNurses = new Nurse[realLength];

        System.arraycopy(nurses, 0, realNurses, 0, realLength);
        // copying the array with nulls (nurses) to a new array without nulls (realNurses).

        input.close();
        // closing the input to access it.

        return realNurses;
    }
    // returns an array of nurses objects.

    //

    private static Patient[] getPatient(Scanner input) {
        int arrayLength = 0;
        for (int token = 0; token < 3; token++) {arrayLength = input.nextInt();}
        // getting the array length.

        Patient[] patients = new Patient[arrayLength];
        int count = 0;

        while (input.hasNext())
        {
            String token = input.next();

            if (token.equals("Add_Patient"))
            // finding the command 'Add_Patient'.
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
                // creating a Patient object in the patients array.
            }
        }

        int realLength = 0;
        for (Patient patient : patients)
        {
            if (patient != null) {realLength++;}
        }

        Patient[] realPatients = new Patient[realLength];

        System.arraycopy(patients, 0, realPatients, 0, realLength);
        // copying the array with nulls (patients) to a new array without nulls (realPatients).

        input.close();
        // closing the input to access it.

        return realPatients;
    }
    // returns an array of patients objects with nulls.

    //
    //

    public static Object[] getAssignmentDoctor(int doctorID, int patientID,
                                              Doctor [] doctors, Patient[] patients)
    {
        Object[] list = new Object[2];
        // creating an array of objects.

        for (Patient patient : patients)
        {
            if (patient.getId() == patientID)
            // checking if patient exist.
            {
                list[0] = patient;
                // assigning a patient of type Patient to element [0] in list.
            }
        }

        for (Doctor doctor : doctors)
        // checking if doctor exist.
        {
            if (doctor.getId() == doctorID)
            {
                list[1] = doctor;
                // assigning a doctor of type Doctor to element [1] in list.
            }
        }

        return list;
    }
    // returns an array of type object with to elements ([0] of type Patient, [1] of type Doctor).

    //

    public static Object[] getAssignmentRoom(int roomNo, int patientID,
                                               Room [] rooms, Patient[] patients)
    {
        Object[] list = new Object[2];
        // creating an array of objects.

        for (Patient patient : patients)
        {
            if (patient.getId() == patientID)
            // checking if patient exist.
            {
                list[0] = patient;
                // assigning a patient of type Patient to element [0] in list.
            }
        }

        for (Room room : rooms)
        {
            if (room.getRoomNo() == roomNo)
            // checking if room exist.
            {
                list[1] = room;
                // assigning a room of type Room to element [1] in list.
            }
        }

        return list;
    }
    // returns an array of type object with to elements ([0] of type Patient, [1] of type Doctor).

    //

    public static Object[] getAssignmentMedicine(int patientID, int totalMedicine, int[] medicinesCode,
                                                 Patient[] patients, Medicine [] medicines)
    {
        Object[] list = new Object[2];
        // creating an array of objects.

        for (Patient patient : patients)
        // checking if patient exist.
        {
            if (patient.getId() == patientID)
            {
                list[0] = patient;
                // assigning a patient of type Patient to element [0] in list.
            }
        }

        list[1] = new Medicine[totalMedicine];
        // creating in element [1] an array of medicines.

        int arrayIndex = 0;
        // a variable to iterate through list[1].

        for (int code : medicinesCode)
        {
            for (Medicine medicine : medicines)
            {
                if (code == medicine.getMedicineCode())
                // checking if medicine exist.
                {
                    ((Medicine[]) list[1]) [arrayIndex] = medicine;
                    // assigning a medicine of type Medicine to element [1][arrayIndex] in list.
                }
            }
            arrayIndex++;
            // incrementing the variable.
        }

        return list;
    }
    // returns an array of type object with to elements ([0] of type Patient, [1] of Medicine[]).

    //
    //

    private static Patient[] getPatientFull(Scanner input, Patient[] patients, Doctor[] doctors, Room[] rooms,
                                        Medicine[] medicines)
    {
        while (input.hasNext())
        // a loop to go through the scanner if there is a token in it.
        {
            String token = input.next();

            if (token.equals("Assign_Doctor_Patient"))
            // finding the command 'Assign_Doctor_Patient'.
            {
                int doctorID = input.nextInt();
                int patientID = input.nextInt();

                Object[] list = getAssignmentDoctor(doctorID, patientID, doctors, patients);
                // creating an array of objects to assign the method getAssignmentDoctor to it.

                if (list[0] != null)
                {
                    if (list[1] != null)
                    {
                        ((Patient) list[0]).setDoctor((Doctor) list[1]);
                        // assigning the doctor to the patient.
                    }
                }
            }
            // this if statement will assign the doctors to the patients.

            if (token.equals("Assign_Room_Patient"))
            // finding the command 'Assign_Room_Patient'.
            {
                int roomNo = input.nextInt();
                int patientID = input.nextInt();

                Object[] list = getAssignmentRoom(roomNo, patientID, rooms, patients);
                // creating an array of objects to assign the method getAssignmentRoom to it.

                if (list[0] != null)
                {
                    if (list[1] != null)
                    {
                        ((Patient) list[0]).setRoom((Room) list[1]);
                        // assigning the room to the patient.
                    }
                }
            }
            // this if statement will assign the rooms to the patients.

            if (token.equals("Assign_Medicine_Patient"))
            // finding the command 'Assign_Medicine_Patient'.
            {
                int patientID = input.nextInt();
                int totalMedicine = input.nextInt();
                int[] medicinesCode = new int[totalMedicine];
                for (int index = 0; index < medicinesCode.length; index++)
                {
                    medicinesCode[index] = input.nextInt();
                }

                Object[] list = getAssignmentMedicine(patientID, totalMedicine, medicinesCode, patients, medicines);
                // creating an array of objects to assign the method getAssignmentMedicine to it.

                if (list[0] != null)
                {
                    if (((Patient) list[0]).getTotalMedicine() == totalMedicine)
                    {
                        ((Patient) list[0]).setMedicine(new Medicine[totalMedicine]);
                        boolean medicineAvailability = true;

                        for (Medicine medicine : (Medicine[]) list[1])
                        {
                            if (medicine == null) {medicineAvailability = false;break;}
                            // finding if the list[1] array have a null.
                        }
                        if (medicineAvailability)
                        {
                            for (int medicine = 0; medicine < totalMedicine; medicine++)
                            {
                                ((Patient) list[0]).getMedicine()[medicine] = ((Medicine[]) list[1])[medicine];
                                // assigning the medicines to the patient.
                            }
                        }
                    }
                }
            }
            // this if statement will assign the medicines to the patients.
        }
        input.close();
        // closing the input to access it.

        return patients;
    }
    // returns an array of patients objects without any nulls.

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
            // finding the command 'Add_Room'.
            {
                output.println("Command Add_Room: Add a new room record in the System");
                output.println("\tRoom No: " + rooms[count_rooms].getRoomNo());
                output.println("\tLocated in floor: " + rooms[count_rooms].getFloor());
                output.println("\tBlock :" + rooms[count_rooms].getBlock());
                output.println("\tCharges: " + rooms[count_rooms].getCharges() + "\n");
                output.println("----------------------------------------------------------------");
                count_rooms++;
            }
            // printing the rooms' data in the file Output_copy2.

            if (token.equals("Add_Medicine"))
            // finding the command 'Add_Medicine'.
            {
                output.println("Command Add_Medicine: Add a new Medicine record in the System");
                output.println("\tMedicine Code: " + medicines[count_medicines].getMedicineCode());
                output.println("\tName: " + medicines[count_medicines].getName());
                output.println("\tPrice :" + medicines[count_medicines].getPrice() + "\n");
                output.println("----------------------------------------------------------------");
                count_medicines++;
            }
            // printing the medicines' data in the file Output_copy2.

            if (token.equals("Add_Doctor"))
            // finding the command 'Add_Doctor'.
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
            // printing the doctors' data in the file Output_copy2.

            if (token.equals("Add_Nurse"))
            // finding the command 'Add_Nurse'.
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
            // printing the nurses' data in the file Output_copy2.

            if (token.equals("Add_Patient"))
            // finding the command 'Add_Patient'.
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
            // printing the patients' data in the file Output_copy2.
        }
        input.close();
        // closing the input to access it.
    }
    // print the data of the class.

    //

    public static void printAssignments(Scanner input, PrintWriter output, Doctor[] doctors, Room[] rooms,
                                        Medicine[] medicines, Patient[] patients) {
        while (input.hasNext())
        {
            String token = input.next();

            if (token.equals("Assign_Doctor_Patient"))
            // finding the command 'Assign_Doctor_Patient'.
            {
                int doctorID = input.nextInt();
                int patientID = input.nextInt();

                Object[] list = getAssignmentDoctor(doctorID, patientID, doctors, patients);

                output.println("Command Assign_Doctor_Patient:");

                if (list[0] == null)
                {
                    output.println("Patient " + patientID + " NOT FOUND ");
                }

                else if (list[1] == null)
                {
                    output.println("Doctor " + doctorID + " NOT FOUND ");
                }

                else
                {
                    output.println("Successfully Processed by the System:\n" +
                            "Following are the details:");
                    output.println("\t\tPatient: " + ((Patient)list[0]).getName());
                    output.println("\t\tAssigned to Doctor: " + ((Doctor)list[1]).getName());
                }

                output.println("-------------------------------------------------------------------------------");
            }
            // printing the doctor/patient assignment data in the file Output_copy2.

            //

            if (token.equals("Assign_Room_Patient"))
            // finding the command 'Assign_Room_Patient'.
            {
                int roomNo = input.nextInt();
                int patientID = input.nextInt();

                Object[] list = getAssignmentRoom(roomNo, patientID, rooms, patients);

                output.println("Command Assign_Room_Patient:");

                if (list[0] == null)
                {
                    output.println("Patient " + patientID + " NOT FOUND ");
                    output.println("---------------------------------------------------------------------------------");
                }

                else if (list[1] == null)
                {
                    output.println("Room " + roomNo + " NOT FOUND ");
                    output.println("---------------------------------------------------------------------------------");
                }

                else
                {
                    output.println("Successfully Processed by the System:\n" +
                            "Following are the details:");
                    output.println("\tPatient: " + ((Patient) list[0]).getName());
                    output.println("\tAssigned to Room:\tRoom No: " + ((Room) list[1]).getRoomNo());
                    output.println("\tLocated in floor: " + ((Room) list[1]).getFloor());
                    output.println("\tBlock :" + ((Room) list[1]).getBlock());
                    output.println("\tCharges: " +((Room) list[1]).getCharges());
                    output.println("\n" + "---------------------------------------------------------------------------------");
                }
            }
            // printing the room/patient assignment data in the file Output_copy2.

            //

            if (token.equals("Assign_Medicine_Patient"))
            // finding the command 'Assign_Medicine_Patient'.
            {
                int patientID = input.nextInt();
                int totalMedicine = input.nextInt();
                int[] medicinesCode = new int[totalMedicine];
                for (int index = 0; index < medicinesCode.length; index++)
                {
                    medicinesCode[index] = input.nextInt();
                }

                Object[] list = getAssignmentMedicine(patientID, totalMedicine, medicinesCode, patients, medicines);

                output.println("Command Assign_Medicine_Patient:");

                boolean medicinesAvailability = true;
                for (int medicine = 0; medicine < totalMedicine; medicine++)
                {
                    if (((Medicine[]) list[1])[medicine] == null)
                    {
                        output.println("Medicines " + medicinesCode[medicine] + " NOT FOUND ");
                        medicinesAvailability = false;
                    }
                }

                if (list[0] == null)
                {
                    output.println("Patient " + patientID + " NOT FOUND ");
                }

                else if (totalMedicine != ((Patient) list[0]).getTotalMedicine())
                {
                    output.println("Information mismatch: Patient " + patientID + "must have " +
                            ((Patient) list[0]).getTotalMedicine()  + " medicine not " + totalMedicine);
                }

                else if (medicinesAvailability)
                {
                    output.println("Successfully Processed by the System:\n" +
                            "Following are the details:");
                    output.println("\tPatient: " + ((Patient) list[0]).getName());
                    for (Medicine medicine :(Medicine[]) list[1])
                    {
                        output.println("\tMedicine Prescribed: " + medicine.getName());
                    }
                }

                output.println("-----------------------------------------------------------------------------");
            }
            // printing the medicines/patient assignment data in the file Output_copy2.
        }
        input.close();
    }
    // print the assignments.

    //

    public static void printBill(Scanner input, PrintWriter output, Patient[] patients) {
        while (input.hasNext())
        {
            String token = input.next();

            if (token.equals("Print_Bill"))
            // finding the command 'Print_Bill'.
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
                    patientsIDs[index] = patients[index].getId();
                }

                Arrays.sort(patientsIDs);
                // to sort the patients according to their ID.

                for (int patientsID : patientsIDs)
                {
                    for (Patient patient : patients)
                    {
                        if (patientsID == patient.getId())
                        // checking if the id match to access the patient data.
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
    // print the bill.
}
