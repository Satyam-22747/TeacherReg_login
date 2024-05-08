package com.satdroid.teacherreg_login;

public class studentAttendenceModal {

   private String RollNO,Name,attendence;

    public studentAttendenceModal(String rollNO, String name, String attendence) {
        RollNO = rollNO;
        Name = name;
        this.attendence = attendence;
    }
    public studentAttendenceModal() {

    }

    public String getRollNO() {
        return RollNO;
    }

    public void setRollNO(String rollNO) {
        RollNO = rollNO;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }
}
