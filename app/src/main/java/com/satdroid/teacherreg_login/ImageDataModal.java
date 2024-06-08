package com.satdroid.teacherreg_login;

public class ImageDataModal {
    String ImageUrl,CourseSelected,SemesterName,SubjectName,ImageDate,ImageTime,TeacherName;
    int ImageCounter;

    public String getImageDate() {
        return ImageDate;
    }

    public void setImageDate(String imageDate) {
        ImageDate = imageDate;
    }

    public String getImageTime() {
        return ImageTime;
    }

    public void setImageTime(String imageTime) {
        ImageTime = imageTime;
    }

    public int getImageCounter() {
        return ImageCounter;
    }

    public void setImageCounter(int imageCounter) {
        ImageCounter = imageCounter;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
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

    public ImageDataModal(String imageUrl, String courseSelected, String semesterName, String subjectName,
                          String imageDate,String imageTime,int imageCounter,String teacherName) {
        ImageUrl = imageUrl;
        CourseSelected = courseSelected;
        SemesterName = semesterName;
        SubjectName = subjectName;
        ImageCounter=imageCounter;
        ImageDate=imageDate;
        ImageTime=imageTime;
        TeacherName=teacherName;
    }

    public ImageDataModal() {

    }
}
