package com.satdroid.teacherreg_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CourseActivity extends AppCompatActivity {

    TextView courseName;
    CardView ebookcv, imagecv, pdfcv, studentcv, noticecv, assignmentcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        courseName=findViewById(R.id.courseName);

        ebookcv=findViewById(R.id.addEbook); imagecv=findViewById(R.id.addimage);pdfcv=findViewById(R.id.addPDF);
        studentcv=findViewById(R.id.StudentDetails);noticecv=findViewById(R.id.addNotice);assignmentcv=findViewById(R.id.addassignments);

        Intent CourseIntent=getIntent();
        courseName.setText(CourseIntent.getStringExtra("course_name"));

        imagecv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  iImage=new Intent(CourseActivity.this,AddImage.class);
                startActivity(iImage);
            }
        });

        pdfcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  iImage=new Intent(CourseActivity.this,AddPDF.class);
                startActivity(iImage);
            }
        });

    }
}