package com.satdroid.teacherreg_login;

public class StudentDetailDataModel {

    private String Course,EmailId,Name,Password,RollNo;

    public StudentDetailDataModel(String course, String emailId, String name, String password, String rollNo) {
        Course = course;
        EmailId = emailId;
        Name = name;
        Password = password;
        RollNo = rollNo;
    }

    public StudentDetailDataModel() {

    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }
}
