package com.satdroid.teacherreg_login;

public class NoticeDataModel {
   private String CourseSelected,Notice,semesterName,subjectName,NoticeDate,TeacherName,UploadTime;

    public NoticeDataModel(String courseSelected, String notice, String semesterName, String subjectName, String noticeDate,String teachername, String uploadTime) {
        CourseSelected = courseSelected;
        Notice = notice;
        this.semesterName = semesterName;
        this.subjectName = subjectName;
        NoticeDate = noticeDate;
        TeacherName=teachername;
        UploadTime=uploadTime;
    }

    public NoticeDataModel() {

    }

    public String getUploadTime() {
        return UploadTime;
    }

    public void setUploadTime(String uploadTime) {
        UploadTime = uploadTime;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getCourseSelected() {
        return CourseSelected;
    }

    public void setCourseSelected(String courseSelected) {
        CourseSelected = courseSelected;
    }

    public String getNotice() {
        return Notice;
    }

    public void setNotice(String notice) {
        Notice = notice;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getNoticeDate() {
        return NoticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        NoticeDate = noticeDate;
    }
}

