package com.satdroid.teacherreg_login;

public class Teacher_data_Modal {
    private String EmailId, Name, Password;

    public Teacher_data_Modal(String emailId, String name, String password) {
        EmailId = emailId;
        Name = name;
        Password = password;
    }
    public Teacher_data_Modal() {
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
}
