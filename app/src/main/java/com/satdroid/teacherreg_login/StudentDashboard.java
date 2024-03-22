package com.satdroid.teacherreg_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentDashboard extends AppCompatActivity {

    private TextView course_name_TV;
    private String courseName_txt;
    private Spinner semester;
    private GridView gridViewSemester;

    ArrayList<CourseModal> Sub_List=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        course_name_TV=findViewById(R.id.Course_TV_stud);

        //spinner view
        semester=findViewById(R.id.Sem_spin);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(StudentDashboard.this, R.array.Semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        semester.setAdapter(adapter);

        //gridview init
        gridViewSemester=findViewById(R.id.gridView_sub_stud);

        Intent intent=getIntent();
        courseName_txt=intent.getStringExtra("Course_name");

        if(courseName_txt.equals("MCA"))
        {

        }
        course_name_TV.setText(courseName_txt);
    }
}