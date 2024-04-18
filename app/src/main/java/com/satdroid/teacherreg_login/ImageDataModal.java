package com.satdroid.teacherreg_login;

public class ImageDataModal {
    String ImageUrl,CourseSelected,SemesterName,SubjectName;

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

    public ImageDataModal(String imageUrl, String courseSelected, String semesterName, String subjectName) {
        ImageUrl = imageUrl;
        CourseSelected = courseSelected;
        SemesterName = semesterName;
        SubjectName = subjectName;
    }

    public ImageDataModal() {

    }
}
