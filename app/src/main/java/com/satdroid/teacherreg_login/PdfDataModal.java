package com.satdroid.teacherreg_login;

public class PdfDataModal {
    String PdfUrl,CourseSelected,SemesterName,SubjectName,PdfDate,PdfTime,TeacherName;
    int PdfCounter;

    public PdfDataModal( String courseSelected,int pdfCounter, String pdfDate,String pdfTime,String pdfUrl, String semesterName, String subjectName,String teacherName) {
        PdfUrl = pdfUrl;
        CourseSelected = courseSelected;
        SemesterName = semesterName;
        SubjectName = subjectName;
        PdfDate = pdfDate;
        PdfTime = pdfTime;
        TeacherName = teacherName;
        PdfCounter = pdfCounter;
    }
    public PdfDataModal() {
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        PdfUrl = pdfUrl;
    }

    public String getCourseSelected() {
        return CourseSelected;
    }

    public void setCourseSelected(String courseSelected) {
        CourseSelected = courseSelected;
    }

    public String getSemesterName() {
        return SemesterName;
    }

    public void setSemesterName(String semesterName) {
        SemesterName = semesterName;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getPdfDate() {
        return PdfDate;
    }

    public void setPdfDate(String pdfDate) {
        PdfDate = pdfDate;
    }

    public String getPdfTime() {
        return PdfTime;
    }

    public void setPdfTime(String pdfTime) {
        PdfTime = pdfTime;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public int getPdfCounter() {
        return PdfCounter;
    }

    public void setPdfCounter(int pdfCounter) {
        PdfCounter = pdfCounter;
    }
}
