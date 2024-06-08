package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Firebase;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

   private AppCompatButton btnregister;
private    String MCA_Course,Civil_Course,Mca_sem,Mca_sub,Civil_sub,Civil_sem;
    private ArrayList<ArrayList<String>> Courses;
  private  Spinner MCA,civil,cs,electrical,electronics,IT,mechanical;
    private  Spinner MCAsub,civilsub,cssub,electricalsub,electronicssub,ITsub,mechanicalsub;
    private  CheckBox MCAcb,civilcb,cscb,electricalcb,electronicscb,ITcb,mechanicalcb;
  private  AppCompatButton selectCourseBtn;
  private TextInputLayout pass,cnf_pass;
  private TextView selected_course,t_name,t_email,t_department;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth FAuth; //firebase authentication
   private  FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectCourseBtn=findViewById(R.id.selecCourseBtn);
        btnregister=findViewById(R.id.reg_btn);
        selected_course=findViewById(R.id.course_status);
        t_name=findViewById(R.id.teacher_name);
        t_email=findViewById(R.id.teacher_email);
        t_department=findViewById(R.id.teacher_department);

        Intent emailIntent=getIntent();
        String T_name=emailIntent.getStringExtra("Name");
        String T_department=emailIntent.getStringExtra("Department");
        String T_email=emailIntent.getStringExtra("Email");

        t_name.setText(T_name);
        t_email.setText(T_email);
        t_department.setText(T_department);


        databaseReference=firebaseDatabase.getInstance().getReference("Teacher");

        FAuth= FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
        selectCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Courses=new ArrayList<>();
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout=inflater.inflate(R.layout.new_course_add_design_teacher,null);
                //checkbox
                CheckBoxint(alertLayout);
                //semester init and semester spinner adapter
                spinnerViewSem( alertLayout);
                //subject init
                spinnerViewSubject(alertLayout);
                //subject spinner adapter
                spinnerViewSubject( alertLayout);
                    //Alert dialog
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                alertDialog.setView(alertLayout);
                alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MCAcb.isChecked())
                        {
                            ArrayList<String> checked=new ArrayList<>();
                            checked.add(MCAcb.getText().toString());
                            checked.add(MCA.getSelectedItem().toString());
                            checked.add(MCAsub.getSelectedItem().toString());
                            Courses.add(checked);
                            selected_course.setText("Course Selected");
                        }
                         if(civilcb.isChecked())
                        {
                            ArrayList<String> checked=new ArrayList<>();
                            checked.add(civilcb.getText().toString()); //course name
                            checked.add(civil.getSelectedItem().toString());  //course semester
                            checked.add(civilsub.getSelectedItem().toString());  //course subject
                            Courses.add(checked);
                            selected_course.setText("Course Selected");
                        }
                        if(electricalcb.isChecked())
                        {
                            ArrayList<String> checked=new ArrayList<>();
                            checked.add(electricalcb.getText().toString()); //course name
                            checked.add(electrical.getSelectedItem().toString());  //course semester
                            checked.add(electricalsub.getSelectedItem().toString());  //course subject
                            Courses.add(checked);
                            selected_course.setText("Course Selected");
                        }
                         if(electronicscb.isChecked())
                        {
                            ArrayList<String> checked=new ArrayList<>();
                            checked.add(electronicscb.getText().toString()); //course name
                            checked.add(electronics.getSelectedItem().toString());  //course semester
                            checked.add(electronicssub.getSelectedItem().toString());  //course subject
                            Courses.add(checked);
                            selected_course.setText("Course Selected");
                        }
                         if(mechanicalcb.isChecked())
                        {
                            ArrayList<String> checked=new ArrayList<>();
                            checked.add(mechanicalcb.getText().toString()); //course name
                            checked.add(mechanical.getSelectedItem().toString());  //course semester
                            checked.add(mechanicalsub.getSelectedItem().toString());  //course subject
                            Courses.add(checked);
                            selected_course.setText("Course Selected");
                        }
                         if(cscb.isChecked())
                        {
                            ArrayList<String> checked=new ArrayList<>();
                            checked.add(cscb.getText().toString()); //course name
                            checked.add(cs.getSelectedItem().toString());  //course semester
                            checked.add(cssub.getSelectedItem().toString());  //course subject
                            Courses.add(checked);
                            selected_course.setText("Course Selected");
                        }
                        if(ITcb.isChecked())
                        {
                            ArrayList<String> checked=new ArrayList<>();
                            checked.add(ITcb.getText().toString()); //course name
                            checked.add(IT.getSelectedItem().toString());  //course semester
                            checked.add(ITsub.getSelectedItem().toString());  //course subject
                            Courses.add(checked);
                            selected_course.setText("Course Selected");
                        }
//                       else
//                            selected_course.setText("Course Not Selected");

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
            public void onClick(View v) {

                TextInputInit();

                if(CheckField()) {

                    FAuth.createUserWithEmailAndPassword(T_email,pass.getEditText().getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                DocumentReference dbTeachers=db.collection("Teacher").document(FAuth.getCurrentUser().getUid());

                HashMap<String,String> hashMap1=new HashMap<>();
                                hashMap1.put("Name",T_name);
                                hashMap1.put("Password",pass.getEditText().getText().toString().trim());
                                hashMap1.put("EmailId",T_email);
                                dbTeachers.set(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        CollectionReference courseCollection=db.collection("Teacher").document(FAuth.getCurrentUser().getUid())
                                                        .collection("Selected course");

                                        for(int i=0;i<Courses.size();i++)
                                        {
                                            HashMap<String,String> hashMap2=new HashMap<>();
                                            hashMap2.put("CourseSelected",(Courses.get(i)).get(0));
                                            hashMap2.put("Semester",Courses.get(i).get(1));
                                            hashMap2.put("Subject",Courses.get(i).get(2));
                                            courseCollection.add(hashMap2).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Toast.makeText(MainActivity.this, "Course in firestore", Toast.LENGTH_SHORT).show();

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(MainActivity.this, "Course not in firestore", Toast.LENGTH_SHORT).show();

                                                }
                                            });
                                        }
                                        Toast.makeText(MainActivity.this,"Account created",Toast.LENGTH_SHORT).show();
                                        Intent RegToDash = new Intent(MainActivity.this, TeacherLoginActivity.class);
                                        startActivity(RegToDash);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(MainActivity.this,"Account not created",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Some error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            //    }
            }
        }
        });

    }
   private void CheckBoxint(View alertLayout)
    {
        MCAcb=alertLayout.findViewById(R.id.MCA_CheckBoxf);
        civilcb=alertLayout.findViewById(R.id.Civil_CheckBoxf);
        electronicscb = alertLayout.findViewById(R.id.Electronics_CheckBox);
        ITcb = alertLayout.findViewById(R.id.IT_CheckBox);
        mechanicalcb = alertLayout.findViewById(R.id.Mechanical_CheckBox);
        electricalcb=alertLayout.findViewById(R.id.Electrical_CheckBox);
        cscb=alertLayout.findViewById(R.id.Computer_Science_CheckBox);
    }
    private void spinnerViewSem(View alertLayout)
    {
        MCA=alertLayout.findViewById(R.id.MCA_semf);
        civil=alertLayout.findViewById(R.id.Civil_semf);
        cs=alertLayout.findViewById(R.id.Computer_Science_sem);
        electrical=alertLayout.findViewById(R.id.Electrical_sem);
        electronics=alertLayout.findViewById(R.id.Electronics_sem);
        IT=alertLayout.findViewById(R.id.IT_sem);
        mechanical=alertLayout.findViewById(R.id.Mechanical_sem);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(MainActivity.this, R.array.Semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterMCA_Mtech=ArrayAdapter.createFromResource(MainActivity.this, R.array.Semester_MCA_Mtech, android.R.layout.simple_spinner_item);
        adapterMCA_Mtech.setDropDownViewResource(android.R.layout.simple_spinner_item);

        MCA.setAdapter(adapterMCA_Mtech);
        civil.setAdapter(adapter);
        cs.setAdapter(adapter);
        electrical.setAdapter(adapter);
        electronics.setAdapter(adapter);
        IT.setAdapter(adapter);
        mechanical.setAdapter(adapter);
    }
    private void spinnerViewSubject(View alertLayout)
    {
        MCAsub=alertLayout.findViewById(R.id.subMca);
        civilsub=alertLayout.findViewById(R.id.subCivil);
        cssub=alertLayout.findViewById(R.id.sub_cs);
    electricalsub=alertLayout.findViewById(R.id.subElectrical);
        electronicssub=alertLayout.findViewById(R.id.subElectronics);
    ITsub=alertLayout.findViewById(R.id.subIT);
        mechanicalsub=alertLayout.findViewById(R.id.subMechanical);
        MCA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 1; i <= 8; i++) {
                    if (parent.getItemAtPosition(position).toString().equals(String.valueOf(i))) {
                        ArrayAdapter<CharSequence> adapterMCAsub = ArrayAdapter.createFromResource(MainActivity.this, getResources().getIdentifier("Mca_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
                        adapterMCAsub.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        MCAsub.setAdapter(adapterMCAsub);
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        civil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 1; i <= 8; i++) {
                    if (parent.getItemAtPosition(position).toString().equals(String.valueOf(i))) {
                        ArrayAdapter<CharSequence> adapterCivil = ArrayAdapter.createFromResource(MainActivity.this, getResources().getIdentifier("Civil_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
                        adapterCivil.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        civilsub.setAdapter(adapterCivil);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 1; i <= 8; i++) {
                    if (parent.getItemAtPosition(position).toString().equals(String.valueOf(i))) {
                        ArrayAdapter<CharSequence> adapterCSsub = ArrayAdapter.createFromResource(MainActivity.this, getResources().getIdentifier("CS_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
                        adapterCSsub.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        cssub.setAdapter(adapterCSsub);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        electrical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 1; i <= 8; i++) {
                    if (parent.getItemAtPosition(position).toString().equals(String.valueOf(i))) {
                        ArrayAdapter<CharSequence> adapterelectricalsub = ArrayAdapter.createFromResource(MainActivity.this, getResources().getIdentifier("Electrical_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
                        adapterelectricalsub.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        electricalsub.setAdapter(adapterelectricalsub);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        IT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 1; i <= 8; i++) {
                    if (parent.getItemAtPosition(position).toString().equals(String.valueOf(i))) {
                        ArrayAdapter<CharSequence> adapterCSsub = ArrayAdapter.createFromResource(MainActivity.this, getResources().getIdentifier("IT_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
                        adapterCSsub.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        ITsub.setAdapter(adapterCSsub);
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mechanical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (int i = 1; i <= 8; i++) {
                    if (parent.getItemAtPosition(position).toString().equals(String.valueOf(i))) {
                        ArrayAdapter<CharSequence> adapterCSsub = ArrayAdapter.createFromResource(MainActivity.this, getResources().getIdentifier("Mechanical_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
                        adapterCSsub.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        mechanicalsub.setAdapter(adapterCSsub);
                        break;
                    }
                }
            }@Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void TextInputInit()
    {
//        Fname=findViewById(R.id.teacher_name);
//        email_reg=findViewById(R.id.reg_email);
        pass=findViewById(R.id.reg_password);
        cnf_pass=findViewById(R.id.confm_password);
    }

    private boolean CheckField()
    {
//        Fname.setErrorEnabled(false);
//        Fname.setError("");
//        email_reg.setErrorEnabled(false);
//        email_reg.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");
        cnf_pass.setErrorEnabled(false);
        cnf_pass.setError("");

        String emailPattern="[a-zA-Z0-9._-]+@knit+\\.+ac+.+in+";

        boolean isValidlpass=false,isValidcnf_pass=false;



        if(TextUtils.isEmpty(pass.getEditText().getText().toString().trim()))
        {
            pass.setErrorEnabled(true);
            pass.setError("Enter Last Name");
        }
        else
        {
            isValidlpass=true;
        }

        if(TextUtils.isEmpty(cnf_pass.getEditText().getText().toString().trim()))
        {
            cnf_pass.setErrorEnabled(true);
            cnf_pass.setError("Enter password again");
        }
        else
        {
            if(!pass.getEditText().getText().toString().trim().equals(cnf_pass.getEditText().getText().toString().trim()))
            {
                cnf_pass.setErrorEnabled(true);
                cnf_pass.setError("Password didn't match");
            }
            else
            {isValidcnf_pass=true;}
        }


        boolean isvalid;
         isvalid=(isValidlpass&&isValidcnf_pass) ?true:false;
        return isvalid;

    }
}
