package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class Student_Registration extends AppCompatActivity {

    private AppCompatButton btnregister,selectCourseBtn;
    //private RadioGroup RdGroup;
   // private RadioButton Rdbtn,MCArd,civilrd,csrd,electricalrd,electronicsrd,ITrd,mechanicalrd;
   private TextInputLayout password,re_password;
    private TextView studName,studRollno,studEmail,selected_course_tv;
 //private String course_selected_text_rd;
 private FirebaseAuth FAuth; //firebase authentication
    private FirebaseFirestore db;
    String email_st="",pass_st="",stud_name="",stud_Course="",stud_roll="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration);

        studName = findViewById(R.id.StudName);
        studRollno = findViewById(R.id.StudRollNo);
        studEmail = findViewById(R.id.StudEmail);
         password = findViewById(R.id.password_std);
         re_password = findViewById(R.id.confm_password_std);
        selected_course_tv=findViewById(R.id.Tv_Selected_Course);
        btnregister=findViewById(R.id.reg_btn_std);
      //  selectCourseBtn=findViewById(R.id.selecCourse_stud);

//        selectCourseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LayoutInflater inflater = getLayoutInflater();
//                View alertLayout=inflater.inflate(R.layout.new_course_add_stud,null);
//                //radio group init
//                RadioGroupint(alertLayout);
//                //spinner view semester
//               // SemesterSpinnerView(alertLayout);
//
//                //Alert dialog
//                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Student_Registration.this);
//                alertDialog.setView(alertLayout);
//
//                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        int selectedRdBTn=RdGroup.getCheckedRadioButtonId();
//                        if(selectedRdBTn!=-1) {
//                            Rdbtn = RdGroup.findViewById(selectedRdBTn);
//                             course_selected_text_rd=Rdbtn.getText().toString();
//
//                            if(course_selected_text_rd.equals("MCA"))
//                            {
//                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd);
//                            }
//                            if(course_selected_text_rd.equals("Civil"))
//                            {
//                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd);
//                            }
//                            if(course_selected_text_rd.equals("Computer Science"))
//                            {
////
//                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd);
//                            }
//                            if(course_selected_text_rd.equals("Electrical"))
//                            {
//
//                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd);
//                            }
//                            if(course_selected_text_rd.equals("Electronics"))
//                            {
//
//                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd);
//                            }
//                            if(course_selected_text_rd.equals("IT"))
//                            {
//
//                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd);
//                            }
//                            if(course_selected_text_rd.equals("Mechanical"))
//                            {
//                                selected_course_tv.setText("Course Selected: "+course_selected_text_rd);
//                            }
//                        }
//                    }
//                });
//                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                AlertDialog alertDialog1=alertDialog.create();
//                alertDialog1.show();
//            }
//        });

        //intent data receaving
        Intent istud=getIntent();
        email_st=email_st+istud.getStringExtra("EmailStud");
        stud_name=stud_name+istud.getStringExtra("NameStud");
        stud_Course=stud_Course+istud.getStringExtra("DepartmentStud");
        stud_roll=stud_roll+istud.getStringExtra("RollNo");

        //textviews
        studName.setText("Name: "+stud_name);
        studRollno.setText("Roll No: "+stud_roll);
        studEmail.setText("Email: "+email_st);
        selected_course_tv.setText("Course: "+stud_Course);

        FAuth= FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

//        email_st=email_st+studEmail.getText().toString();
//        pass_st=pass_st+password.getEditText().toString().trim();
//        stud_name=stud_name+studName.getText().toString();
//        stud_Course=stud_Course+selected_course_tv.getText().toString();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                pass_st=password.getEditText().getText().toString().trim();
                FAuth.createUserWithEmailAndPassword(email_st,pass_st).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Student_Registration.this, "Student Registered", Toast.LENGTH_SHORT).show();
                            DocumentReference dbStudents=db.collection("Student").document(FAuth.getCurrentUser().getUid());

                            HashMap<String,String> hashMap1=new HashMap<>();

                            hashMap1.put("Name",stud_name);
                            hashMap1.put("RollNo",stud_roll);
                            hashMap1.put("EmailId",email_st);
                            hashMap1.put("Password",pass_st);
                            hashMap1.put("Course",stud_Course);

                            dbStudents.set(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Student_Registration.this, "Student data in firestore", Toast.LENGTH_SHORT).show();
                                    Intent iStud_dash=new Intent(Student_Registration.this, TeacherLoginActivity.class);
                                    //iStud_dash.putExtra("Course_name",course_selected_text_rd);
                                    startActivity(iStud_dash);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Student_Registration.this, "Student data not in firestore", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(Student_Registration.this, "Student Registered task failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Student_Registration.this, "Student Some error occured", Toast.LENGTH_SHORT).show();
                    }
                });

//                Intent iStud_dash=new Intent(Student_Registration.this, StudentDashboard.class);
//         //      iStud_dash.putExtra("Course_name",course_selected_text_rd);
//                startActivity(iStud_dash);
            }
        });
    }

//    private void RadioGroupint(View alertLayout)
//    {
//        RdGroup=alertLayout.findViewById(R.id.Rad_group);
//        RdGroup.clearCheck();
//    }


}