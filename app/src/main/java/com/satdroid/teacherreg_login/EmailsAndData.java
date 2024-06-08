package com.satdroid.teacherreg_login;

import java.util.HashMap;


import java.util.HashMap;

public class EmailsAndData {

    public HashMap<String, String[]> emailDataMap;
    public HashMap<String, String[]> emailDataMapStud;

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
        //mca
        emailDataMapStud.put("satyam.22747@knit.ac.in", new String[]{"Satyam Kushwaha", "22747", "MCA"});
        emailDataMapStud.put("aayush.22701@knit.ac.in", new String[]{"Aayush Jain", "22701", "MCA"});
        emailDataMapStud.put("abhishek.22703@knit.ac.in", new String[]{"Abhishek Pandey", "22703", "MCA"});
        emailDataMapStud.put("ajay.22704@knit.ac.in", new String[]{"Ajay Gupta", "22704", "MCA"});
        emailDataMapStud.put("akash.22706@knit.ac.in", new String[]{"Akash Agrawal", "22706", "MCA"});
        emailDataMapStud.put("bhavishya.22714@knit.ac.in", new String[]{"Bhavishya Sachdeva", "22714", "MCA"});
        emailDataMapStud.put("rudra.22746@knit.ac.in", new String[]{"Rudra", "22746", "MCA"});
        //civil
        emailDataMapStud.put("dev.22718@knit.ac.in", new String[]{"Dev", "22718", "Civil"});
        emailDataMapStud.put("govind.22720@knit.ac.in", new String[]{"Govind Yadav", "22720", "Civil"});
        emailDataMapStud.put("harsh.22723@knit.ac.in", new String[]{"Harsh Bardhan", "22723", "Civil"});
        emailDataMapStud.put("kalsar.22725@knit.ac.in", new String[]{"Kalsar", "22725", "Civil"});
        emailDataMapStud.put("mohd.22731@knit.ac.in", new String[]{"Mohd Azam", "22731", "Civil"});
        //electrical
        emailDataMapStud.put("prajjwal.22739@knit.ac.in", new String[]{"Prajjwal", "22739", "Electrical"});
        emailDataMapStud.put("prashant.22740@knit.ac.in", new String[]{"Prashant", "22740", "Electrical"});
        emailDataMapStud.put("pratyancha.22741@knit.ac.in", new String[]{"Pratyancha", "22741", "Electrical"});
        emailDataMapStud.put("rahul.22742@knit.ac.in", new String[]{"Rahul", "22742", "Electrical"});
        emailDataMapStud.put("rakesh.22743@knit.ac.in", new String[]{"Rakesh", "22743", "Electrical"});
        emailDataMapStud.put("ravindra.22744@knit.ac.in", new String[]{"Ravindra", "22744", "Electrical"});
        emailDataMapStud.put("rohit.22745@knit.ac.in", new String[]{"Rohit", "22745", "Electrical"});
        //electronics
        emailDataMapStud.put("shailesh.22748@knit.ac.in", new String[]{"Shailesh", "22748", "Electronics"});
        emailDataMapStud.put("shalini.22749@knit.ac.in", new String[]{"Shalini", "22749", "Electronics"});
        emailDataMapStud.put("shantanu.22750@knit.ac.in", new String[]{"Shantanu", "22750", "Electronics"});
        emailDataMapStud.put("shaquib.22752@knit.ac.in", new String[]{"Shaquib", "22752", "Electronics"});
        emailDataMapStud.put("shikha.22753@knit.ac.in", new String[]{"Shikha", "22753", "Electronics"});
        emailDataMapStud.put("shiva.22754@knit.ac.in", new String[]{"Shiva", "22754", "Electronics"});
        emailDataMapStud.put("shri.22755@knit.ac.in", new String[]{"Shri", "22755", "Electronics"});
        emailDataMapStud.put("sujit.22756@knit.ac.in", new String[]{"Sujit", "22756", "Electronics"});
        emailDataMapStud.put("tanishq.22757@knit.ac.in", new String[]{"Tanishq", "22757", "Electronics"});
//mechanical
        emailDataMapStud.put("tanisk.22758@knit.ac.in", new String[]{"Tanisk", "22758", "Mechanical"});
        emailDataMapStud.put("tannu.22759@knit.ac.in", new String[]{"Tannu", "22759", "Mechanical"});
        emailDataMapStud.put("umang.22760@knit.ac.in", new String[]{"Umang", "22760", "Mechanical"});
        emailDataMapStud.put("vidushi.22761@knit.ac.in", new String[]{"Vidushi", "22761", "Mechanical"});
        emailDataMapStud.put("vikas.22762@knit.ac.in", new String[]{"Vikas", "22762", "Mechanical"});
        emailDataMapStud.put("vishal.22764@knit.ac.in", new String[]{"Vishal", "22764", "Mechanical"});
        emailDataMapStud.put("vishesh.22765@knit.ac.in", new String[]{"Vishesh", "22765", "Mechanical"});
        emailDataMapStud.put("abhay.22702@knit.ac.in", new String[]{"Abhay", "22702", "Mechanical"});
//cs
        emailDataMapStud.put("akhand.22707@knit.ac.in", new String[]{"Akhand", "22707", "CS"});
        emailDataMapStud.put("anushka.22708@knit.ac.in", new String[]{"Anushka", "22708", "CS"});
        emailDataMapStud.put("arshil.22709@knit.ac.in", new String[]{"Arshil", "22709", "CS"});
        emailDataMapStud.put("aryan.22710@knit.ac.in", new String[]{"Aryan", "22710", "CS"});
        emailDataMapStud.put("atul.22711@knit.ac.in", new String[]{"Atul", "22711", "CS"});
        emailDataMapStud.put("avinash.22712@knit.ac.in", new String[]{"Avinash", "22712", "CS"});
        emailDataMapStud.put("ayush.22713@knit.ac.in", new String[]{"Ayush", "22713", "CS"});
        emailDataMapStud.put("chandan.22715@knit.ac.in", new String[]{"Chandan", "22715", "CS"});

        //IT
        emailDataMapStud.put("chauhan.22716@knit.ac.in", new String[]{"Chauhan", "22716", "IT"});
        emailDataMapStud.put("deepak.22717@knit.ac.in", new String[]{"Deepak", "22717", "IT"});
        emailDataMapStud.put("dhatri.22719@knit.ac.in", new String[]{"Dhatri", "22719", "IT"});
        emailDataMapStud.put("gyanendra.22721@knit.ac.in", new String[]{"Gyanendra", "22721", "IT"});
        emailDataMapStud.put("harish.22722@knit.ac.in", new String[]{"Harish", "22722", "IT"});
        emailDataMapStud.put("jalaj.22724@knit.ac.in", new String[]{"Jalaj", "22724", "IT"});
        emailDataMapStud.put("khushi.22726@knit.ac.in", new String[]{"Khushi", "22726", "IT"});
        emailDataMapStud.put("kishan.22727@knit.ac.in", new String[]{"Kishan", "22727", "IT"});
        emailDataMapStud.put("kuldeep.22728@knit.ac.in", new String[]{"Kuldeep", "22728", "IT"});
        emailDataMapStud.put("adil.22729@knit.ac.in", new String[]{"Adil", "22729", "IT"});



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

