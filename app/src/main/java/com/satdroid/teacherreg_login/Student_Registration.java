package com.satdroid.teacherreg_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class Student_Registration extends AppCompatActivity {

    private AppCompatButton btnregister,selectCourseBtn;
    private Spinner MCA,civil,cs,electrical,electronics,IT,mechanical;
    private RadioGroup RdGroup;
    private RadioButton Rdbtn,MCArd,civilrd,csrd,electricalrd,electronicsrd,ITrd,mechanicalrd;
    private TextInputLayout fullname,rollno,password,re_password,email_stud;
    private TextView selected_course_tv;
 private String course_selected_text_rd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration);

         fullname = findViewById(R.id.reg_name_std);
         rollno = findViewById(R.id.rollNo_std);
         email_stud = findViewById(R.id.email_std);
         password = findViewById(R.id.password_std);
         re_password = findViewById(R.id.confm_password_std);
        selected_course_tv=findViewById(R.id.Tv_Selected_Course);
        btnregister=findViewById(R.id.reg_btn_std);
        selectCourseBtn=findViewById(R.id.selecCourse_stud);

        selectCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout=inflater.inflate(R.layout.new_course_add_stud,null);
                //radio group init
                RadioGroupint(alertLayout);
                //spinner view semester
                SemesterSpinnerView(alertLayout);

                //Alert dialog
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Student_Registration.this);
                alertDialog.setView(alertLayout);

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int selectedRdBTn=RdGroup.getCheckedRadioButtonId();
                        if(selectedRdBTn!=-1) {
                            Rdbtn = RdGroup.findViewById(selectedRdBTn);
                             course_selected_text_rd=Rdbtn.getText().toString();

                            if(course_selected_text_rd.equals("MCA"))
                            {
                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd+" "+MCA.getSelectedItem().toString()+" semester");
                            }
                            if(course_selected_text_rd.equals("Civil"))
                            {
                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd+" "+civil.getSelectedItem().toString()+" semester");
                            }
                            if(course_selected_text_rd.equals("Computer Science"))
                            {
//
                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd+" "+cs.getSelectedItem().toString()+" semester");
                            }
                            if(course_selected_text_rd.equals("Electrical"))
                            {

                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd+" "+electrical.getSelectedItem().toString()+" semester");
                            }
                            if(course_selected_text_rd.equals("Electronics"))
                            {

                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd+" "+electronics.getSelectedItem().toString()+" semester");
                            }
                            if(course_selected_text_rd.equals("IT"))
                            {

                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd+" "+IT.getSelectedItem().toString()+" semester");
                            }
                            if(course_selected_text_rd.equals("Mechanical"))
                            {
                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd+" "+mechanical.getSelectedItem().toString()+" semester");
                            }
                        }
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog1=alertDialog.create();
                alertDialog1.show();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent iStud_dash=new Intent(Student_Registration.this, StudentDashboard.class);
               iStud_dash.putExtra("Course_name",course_selected_text_rd);
                startActivity(iStud_dash);
            }
        });
    }

    private void RadioGroupint(View alertLayout)
    {
        RdGroup=alertLayout.findViewById(R.id.Rad_group);
        RdGroup.clearCheck();


    }
    private void SemesterSpinnerView(View alertLayout)
    {
        MCA=alertLayout.findViewById(R.id.MCA_sem_stud);
        civil=alertLayout.findViewById(R.id.Civil_sem_stud);
        cs=alertLayout.findViewById(R.id.Computer_Science_sem_stud);
        electrical=alertLayout.findViewById(R.id.Electrical_sem_stud);
        electronics=alertLayout.findViewById(R.id.Electronics_sem_stud);
        IT=alertLayout.findViewById(R.id.IT_sem_stud);
        mechanical=alertLayout.findViewById(R.id.Mechanical_sem_stud);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(Student_Registration.this, R.array.Semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        MCA.setAdapter(adapter);
        civil.setAdapter(adapter);
        cs.setAdapter(adapter);
        electrical.setAdapter(adapter);
        electronics.setAdapter(adapter);
        IT.setAdapter(adapter);
        mechanical.setAdapter(adapter);
    }

}