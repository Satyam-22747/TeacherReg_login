package com.satdroid.teacherreg_login;

public class CourseTeacherModal {
    private String CourseSelected,Semester,Subject;

    public CourseTeacherModal(String courseSelected, String semester, String subject) {
        CourseSelected = courseSelected;
        Semester = semester;
        Subject = subject;
    }
    public CourseTeacherModal() {

    }
    public String getCourseSelected() {
        return CourseSelected;
    }

    public void setCourseSelected(String courseSelected) {
        CourseSelected = courseSelected;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
