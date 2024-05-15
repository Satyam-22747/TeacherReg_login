package com.satdroid.teacherreg_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CourseStudent extends AppCompatActivity {
private TextView Course_name,Course_sem;
    private CardView ebookcv, imagecv, pdfcv, studentcv, noticecv, assignmentcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_student);

        Course_name=findViewById(R.id.courseName_stud);
        Course_sem=findViewById(R.id.CourseSem_stud);

        Intent iCourseStud=getIntent();
        String course_name=iCourseStud.getStringExtra("Course_Name");
        Course_name.setText(course_name);
        int course_sem=iCourseStud.getIntExtra("Course_sem",0);
       Course_sem.setText("Sem "+String.valueOf(course_sem));

        imagecv=findViewById(R.id.addimage_stud);
        pdfcv=findViewById(R.id.PDF_stud);
        noticecv=findViewById(R.id.Notice_stud);
        imagecv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iImage=new Intent(CourseStudent.this,Images_Student.class);
                iImage.putExtra("Course Name",course_name);
                iImage.putExtra("Course Sem",course_sem);
                startActivity(iImage);
            }
        });

        pdfcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iPDF=new Intent(CourseStudent.this,Pdf_student.class);
                iPDF.putExtra("Course Name",course_name);
                iPDF.putExtra("Course Sem",course_sem);
                startActivity(iPDF);
            }
        });

        noticecv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNotice=new Intent(CourseStudent.this,Notice_student.class);
                iNotice.putExtra("Course Name",course_name);
                iNotice.putExtra("Course Sem",course_sem);
                startActivity(iNotice);

            }
        });
    }
}