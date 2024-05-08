package com.satdroid.teacherreg_login;

import java.util.HashMap;


import java.util.HashMap;

public class EmailsAndData {

    public HashMap<String, String[]> emailDataMap;
    public HashMap<String, String[]> emailDataMapStud;

    //student initilization constructor
//    public EmailsAndData(HashMap<String, String[]> emailDataMapStud) {
//        this.emailDataMapStud = emailDataMapStud;
//        initializeDataStudent();
//    }

    //Teacher initilization constructor
    public EmailsAndData() {
        // Initialize the HashMap
        this.emailDataMap = new HashMap<>();
        this.emailDataMapStud = new HashMap<>();
        initializeDataStudent();
        // Prepopulate the HashMap with initial data (optional)
        initializeData();
    }

    // Method to initialize the HashMap with initial data
    private void initializeData() {
        // Add initial data to the HashMap
        emailDataMap.put("piyush.9428@knit.ac.in", new String[]{"Piyush Pandey", "CE"});
        emailDataMap.put("pratyush@knit.ac.in", new String[]{"Pratyush", "CE"});
        emailDataMap.put("ramasheeshprajapati@gmail.com", new String[]{"Ram Asheesh Prajapati", "CE"});
        emailDataMap.put("shivamrck786@gmail.com", new String[]{"Shivam Srivastava", "CE"});
        emailDataMap.put("aasha@knit.ac.in", new String[]{"Aasha Singh", "CSED"});
        emailDataMap.put("atulsha08@gmail.com", new String[]{"Atul Lal Shrivastava", "CSED"});
        emailDataMap.put("babu.ram@knit.ac.in", new String[]{"Babu Ram", "CSED"});
        emailDataMap.put("dhirendrak@knit.ac.in", new String[]{"DHIRENDRA KUMAR", "CSED"});
        emailDataMap.put("garima.168204@knit.ac.in", new String[]{"Garima Yadav", "CSED"});
        emailDataMap.put("hanumanmaurya@gmail.com", new String[]{"Hanuman Maurya", "CSED"});
        emailDataMap.put("kriti@knit.ac.in", new String[]{"Kriti Chaurasia", "CSED"});
        emailDataMap.put("neetika@knit.ac.in", new String[]{"Neetika Gond", "CSED"});
        emailDataMap.put("nilesh@knit.ac.in", new String[]{"Nilesh Chandra", "CSED"});
        emailDataMap.put("sohit@knit.ac.in", new String[]{"Sohit Shukla", "CSED"});
        emailDataMap.put("vinay@knit.ac.in", new String[]{"Vinay Singh", "CSED"});
        emailDataMap.put("abhinav.gautam@knit.ac.in", new String[]{"Abhinav Gautam", "EE"});
        emailDataMap.put("dilip.patel.2019@knit.ac.in", new String[]{"Dilip Kumar Patel", "EE"});
        emailDataMap.put("nitish.1710307@knit.ac.in", new String[]{"Nitish Kumar Rai", "EE"});
        emailDataMap.put("pnvknit@gmail.com", new String[]{"Prem Narayan Verma", "EE"});
        emailDataMap.put("ravindrakumar@knit.ac.in", new String[]{"Ravindra Kumar", "EE"});
        emailDataMap.put("anuragknit.singh@gmail.com", new String[]{"Anurag Singh", "EL"});
        emailDataMap.put("vermagaurav1107@gmail.com", new String[]{"Gaurav Verma", "EL"});
        emailDataMap.put("preeti.kharwar@gmail.com", new String[]{"Preeti Kharwar", "EL"});
        emailDataMap.put("shweta.singh.2176@knit.ac.in", new String[]{"Shweta Singh", "EL"});
        emailDataMap.put("vinaykumar@knit.ac.in", new String[]{"Vinay Kumar", "EL"});
        emailDataMap.put("varun.sonkar@knit.ac.in", new String[]{"Dr. Varun Kumar Sonkar", "MED"});
        emailDataMap.put("manib@knit.ac.in", new String[]{"Mani Bhushan Singh", "MED"});
        emailDataMap.put("Manish@knit.ac.in", new String[]{"Manish Kumar Chauhan", "MED"});
        emailDataMap.put("praveen@knit.ac.in", new String[]{"Praveen Kumar Rai", "MED"});
        // Add more initial data as needed...
    }


    // Method to add or update data for a specific email
    public void addOrUpdateData(String email, String name, String department) {
        // Create a String array with the new data
        String[] newData = new String[]{name, department};

        // Add or update the entry in the HashMap
        emailDataMap.put(email, newData);
    }

    public String[] checkEmailData(String email) {
        return emailDataMap.get(email);

    }

    //students
    private void initializeDataStudent() {
        // Add initial data to the HashMap
        emailDataMapStud.put("satyam.22747@knit.ac.in", new String[]{"Satyam Kushwaha", "22747", "MCA"});
        emailDataMapStud.put("aayush.22701@knit.ac.in", new String[]{"Aayush Jain", "22701", "MCA"});
        emailDataMapStud.put("abhishek.22703@knit.ac.in", new String[]{"Abhishek Pandey", "22703", "MCA"});
        emailDataMapStud.put("ajay.22704@knit.ac.in", new String[]{"Ajay Gupta", "22704", "MCA"});
        emailDataMapStud.put("akash.22706@knit.ac.in", new String[]{"Akash Agrawal", "22706", "MCA"});
        emailDataMapStud.put("bhavishya.22714@knit.ac.in", new String[]{"Bhavishya Sachdeva", "22714", "MCA"});

        emailDataMapStud.put("dev.22718@knit.ac.in", new String[]{"Dev", "22718", "Civil"});
        emailDataMapStud.put("govind.22720@knit.ac.in", new String[]{"Govind Yadav", "22720", "Civil"});
        emailDataMapStud.put("harsh.22723@knit.ac.in", new String[]{"Harsh Bardhan", "22723", "Civil"});
        emailDataMapStud.put("kalsar.22725@knit.ac.in", new String[]{"Kalsar", "22725", "Civil"});
        emailDataMapStud.put("mohd.22731@knit.ac.in", new String[]{"Mohd Azam", "22731", "Civil"});
    }

    //add/update students
    public void addOrUpdateDataStudent(String email, String name, String rollNo, String department) {
        // Create a String array with the new data
        String[] newData = new String[]{name, rollNo,department};

        // Add or update the entry in the HashMap
        emailDataMapStud.put(email, newData);
    }

    //check student email
    public String[] checkEmailDataStudent(String email) {
        return emailDataMapStud.get(email);

    }

}

