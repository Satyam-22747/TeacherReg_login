package com.satdroid.teacherreg_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentDashboard extends AppCompatActivity {

    private TextView course_name_TV;
    private String courseName_txt;
    private GridView gridViewSemesters;
    ArrayList<CourseModal> SemList=new ArrayList<>();


   // ArrayList<CourseModal> Sub_List=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        course_name_TV=findViewById(R.id.Course_TV_stud);


        //gridview init
        gridViewSemesters=findViewById(R.id.gridView_sem_stud);

        Intent intent=getIntent();
        courseName_txt=intent.getStringExtra("Course_name");
        course_name_TV.setText(courseName_txt);

        if(courseName_txt.equals("MCA"))
        {
            for(int i=0;i<4;i++)
            {
                SemList.add(new CourseModal(String.valueOf(i+1)));
            }
        }
        else {
            for(int i=0;i<8;i++)
            {
                SemList.add(new CourseModal(String.valueOf(i+1)));
            }
        }
        CourseAdapter Semadapter=new CourseAdapter(this,SemList);
        gridViewSemesters.setAdapter(Semadapter);
        gridViewSemesters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int sem=position+1;
                Toast.makeText(StudentDashboard.this,"Sem: "+sem,Toast.LENGTH_SHORT).show();
                Intent iSem=new Intent(StudentDashboard.this,CourseStudent.class);
                iSem.putExtra("Course_Name",courseName_txt);
                iSem.putExtra("Course_sem",sem);
                startActivity(iSem);
            }
        });
    }
}