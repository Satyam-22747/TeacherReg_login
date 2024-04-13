package com.satdroid.teacherreg_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Images_Student extends AppCompatActivity {

    private String courseName_txt;
    private Spinner su;
    private GridView gridViewSubjects;

    ArrayList<CourseModal> Sub_List=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_student);


        Intent iStudImage=getIntent();
        String courseName_txt=iStudImage.getStringExtra("Course Name");
        int courseSemester=iStudImage.getIntExtra("Course Sem",0);
          gridViewSubjects=findViewById(R.id.gridView_sub_stud);

        SubJectSemester(courseName_txt,courseSemester);

        CourseAdapter Subadapter=new CourseAdapter(this,Sub_List);
        gridViewSubjects.setAdapter(Subadapter);

        gridViewSubjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(Images_Student.this,"Sem: "+sem,Toast.LENGTH_SHORT).show();
                String subjectname="";
                View viewItem = gridViewSubjects.getChildAt(position);
                if (viewItem != null) {
                    TextView SubjectTv = (TextView) viewItem.findViewById(R.id.courseName_tv);
                    subjectname=subjectname+SubjectTv.getText().toString();
                }
//                String subjectname=gridViewSubjects.getItemAtPosition(position).toString();

                Intent iSem=new Intent(Images_Student.this,ImageList.class);
                iSem.putExtra("Course_Name",courseName_txt);
                iSem.putExtra("Course_sem",courseSemester);
                iSem.putExtra("Course_subjects",subjectname);
                startActivity(iSem);
            }
        });


    }

    public void SubJectSemester(String course_name, int course_sem)
    {
        Resources res = getResources();

        if(course_name.equals("MCA"))
        {

            if(course_sem==1)
            {
                for(int i=1;i<res.getStringArray(R.array.Mca_subjects_1_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Mca_subjects_1_sem)[i]));
                }
            }
            if(course_sem==2)
            {
                for(int i=1;i<res.getStringArray(R.array.Mca_subjects_2_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Mca_subjects_2_sem)[i]));
                }
            }
            if(course_sem==3)
            {
                for(int i=1;i<res.getStringArray(R.array.Mca_subjects_3_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Mca_subjects_3_sem)[i]));
                }
            }
            if(course_sem==4)
            {
                for(int i=1;i<res.getStringArray(R.array.Mca_subjects_4_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Mca_subjects_4_sem)[i]));
                }
            }
        }

        if(course_name.equals("Civil"))
        {
            if(course_sem==1)
            {
                for(int i=1;i<res.getStringArray(R.array.Civil_subjects_1_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Mca_subjects_1_sem)[i]));
                }
            }
            if(course_sem==2)
            {
                for(int i=1;i<res.getStringArray(R.array.Civil_subjects_2_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Civil_subjects_2_sem)[i]));
                }
            }
            if(course_sem==3)
            {
                for(int i=1;i<res.getStringArray(R.array.Civil_subjects_3_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Civil_subjects_3_sem)[i]));
                }
            }
            if(course_sem==4)
            {
                for(int i=1;i<res.getStringArray(R.array.Civil_subjects_4_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Civil_subjects_4_sem)[i]));
                }
            }
            if(course_sem==4)
            {
                for(int i=1;i<res.getStringArray(R.array.Civil_subjects_5_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Civil_subjects_5_sem)[i]));
                }
            }
            if(course_sem==4)
            {
                for(int i=1;i<res.getStringArray(R.array.Civil_subjects_6_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Civil_subjects_6_sem)[i]));
                }
            }
            if(course_sem==4)
            {
                for(int i=1;i<res.getStringArray(R.array.Civil_subjects_7_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Civil_subjects_7_sem)[i]));
                }
            }
            if(course_sem==4)
            {
                for(int i=1;i<res.getStringArray(R.array.Civil_subjects_8_sem).length;i++)
                {
                    Sub_List.add(new CourseModal(res.getStringArray(R.array.Civil_subjects_8_sem)[i]));
                }
            }
        }
    }
}