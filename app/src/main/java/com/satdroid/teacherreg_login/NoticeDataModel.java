package com.satdroid.teacherreg_login;

public class NoticeDataModel {
   private String CourseSelected,Notice,semesterName,subjectName,NoticeDate;

    public NoticeDataModel(String courseSelected, String notice, String semesterName, String subjectName, String noticeDate) {
        CourseSelected = courseSelected;
        Notice = notice;
        this.semesterName = semesterName;
        this.subjectName = subjectName;
        NoticeDate = noticeDate;
    }

    public NoticeDataModel() {

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

