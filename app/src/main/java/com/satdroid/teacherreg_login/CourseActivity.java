package com.satdroid.teacherreg_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

   private TextView courseName;
  private    CardView  imagecv, pdfcv, studentcv, noticecv, assignmentcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        courseName=findViewById(R.id.courseName);

        imagecv=findViewById(R.id.addimage);pdfcv=findViewById(R.id.addPDF);
        studentcv=findViewById(R.id.StudentDetails);noticecv=findViewById(R.id.addNotice);assignmentcv=findViewById(R.id.addassignments);

        Intent intent=getIntent();
        ArrayList<String> course_selected;
        course_selected=(ArrayList<String>)intent.getSerializableExtra("course_clicked");
        courseName.setText(course_selected.get(0)+" "+course_selected.get(1));
        imagecv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  iImage=new Intent(CourseActivity.this,AddImage.class);
                iImage.putExtra("Selected Course",course_selected);
                startActivity(iImage);
            }
        });

        pdfcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  iPDF=new Intent(CourseActivity.this,AddPDF.class);
                iPDF.putExtra("Selected Course",course_selected);
                startActivity(iPDF);
            }
        });

        studentcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  iPDF=new Intent(CourseActivity.this,StudentDetails.class);
                iPDF.putExtra("Selected Course",course_selected);
                startActivity(iPDF);
            }
        });

        noticecv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  iNOTICE=new Intent(CourseActivity.this,UploadNotice.class);
                iNOTICE.putExtra("Selected Course",course_selected);
                startActivity(iNOTICE);
            }
        });

    }
}