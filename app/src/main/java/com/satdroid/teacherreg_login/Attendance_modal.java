package com.satdroid.teacherreg_login;

public class Attendance_modal {
   private String present,absent;

    public Attendance_modal(String present, String absent) {
        this.present = present;
        this.absent = absent;
    }
    public Attendance_modal() {
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getAbsent() {
        return absent;
    }

    public void setAbsent(String absent) {
        this.absent = absent;
    }
}
