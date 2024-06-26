package com.satdroid.teacherreg_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class StudentDashboard extends AppCompatActivity {

    private TextView course_name_TV;
    private String courseName_txt;
    private GridView gridViewSemesters;
    private ArrayList<CourseModal> SemList=new ArrayList<>();
    private Toolbar toolbar;

    // ArrayList<CourseModal> Sub_List=new ArrayList<>();
//    String dateTime;
//    Calendar calendar;
//    SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

//        calendar = Calendar.getInstance();
//        simpleDateFormat = new SimpleDateFormat("KK:mm aaa ");
//        dateTime = simpleDateFormat.format(calendar.getTime()).toString();

        //Toast.makeText(StudentDashboard.this,"Time: "+dateTime,Toast.LENGTH_SHORT).show();

        course_name_TV=findViewById(R.id.Course_TV_stud);
        toolbar=findViewById(R.id.toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int id=item.getItemId();
                if(id==R.id.logout_btn) {

                    Toast.makeText(StudentDashboard.this,"Loged out",Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    Intent inext=new Intent(StudentDashboard.this, TeacherLoginActivity.class);
                    startActivity(inext);
                    finish();
                }
                return true;
            }
        });
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