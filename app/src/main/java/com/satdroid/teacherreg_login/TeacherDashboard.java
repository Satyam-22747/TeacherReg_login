package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class TeacherDashboard extends AppCompatActivity {


    private String MCAtxt,Civiltxt;
   private GridView gridViewCourse;
    private    String MCA_tv,Civil_tv;
   private TextView courseName;
  private  FloatingActionButton floatingActionButton;
  private  CheckBox MCAcb,civilcb,cscb,electricalcb,electronicscb,ITcb,mechanicalcb;
    private  Spinner MCAsub,civilsub,cssub,electricalsub,electronicssub,ITsub,mechanicalsub;

    private  Spinner MCA,civil,cs,electrical,electronics,IT,mechanical;

    ArrayList<CourseModal> courseList=new ArrayList<>();
    HashMap<String,String> CourseUpload;
    private FirebaseAuth FAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        gridViewCourse=findViewById(R.id.gridView_courses);
        courseName=findViewById(R.id.CourseTV);
        floatingActionButton=findViewById(R.id.floating_btn_newCourse);

        FAuth= FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        CourseUpload=new HashMap<>();

        Intent intent=getIntent();
        ArrayList<ArrayList<String>> coursesList;
        coursesList=(ArrayList<ArrayList<String>>)intent.getSerializableExtra("Courses List");
        courseName.setText("Courses");

        for(int i=0;i<coursesList.size();i++)
        {
            courseList.add(new CourseModal((coursesList.get(i)).get(0)+" "+(coursesList.get(i)).get(1)));
        }

        CourseAdapter adapter=new CourseAdapter(this,courseList);
        gridViewCourse.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(TeacherDashboard.this);
                alertDialog.setView(alertLayout);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MCAcb.isChecked())
                        {
                            ArrayList<String> checked=new ArrayList<>();
                            checked.add(MCAcb.getText().toString());
                            checked.add(MCA.getSelectedItem().toString());
                            checked.add(MCAsub.getSelectedItem().toString());
                            coursesList.add(checked);
                            courseList.add(new CourseModal(checked.get(0)+ " "+checked.get(1)));
                        }

                        if(civilcb.isChecked())
                        {
                            ArrayList<String> checked=new ArrayList<>();
                            checked.add(civilcb.getText().toString());
                            checked.add(civil.getSelectedItem().toString());
                            checked.add(civilsub.getSelectedItem().toString());
                            coursesList.add(checked);
                            courseList.add(new CourseModal(checked.get(0)+ " "+checked.get(1)));
                        }
                        gridViewCourse.setAdapter(adapter);
                        Toast.makeText(TeacherDashboard.this,"Course selected",Toast.LENGTH_SHORT).show();
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
        gridViewCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent inext=new Intent(TeacherDashboard.this,CourseActivity.class);
//                inext.putExtra("course_clicked",courseList.get(position).getCourse());
                inext.putExtra("course_clicked",coursesList.get(position));
                startActivity(inext);
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
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(TeacherDashboard.this, R.array.Semester, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        MCA.setAdapter(adapter);
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
                        ArrayAdapter<CharSequence> adapterMCAsub = ArrayAdapter.createFromResource(TeacherDashboard.this, getResources().getIdentifier("Mca_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
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
                        ArrayAdapter<CharSequence> adapterCivil = ArrayAdapter.createFromResource(TeacherDashboard.this, getResources().getIdentifier("Civil_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
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
                        ArrayAdapter<CharSequence> adapterCSsub = ArrayAdapter.createFromResource(TeacherDashboard.this, getResources().getIdentifier("CS_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
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
                        ArrayAdapter<CharSequence> adapterelectricalsub = ArrayAdapter.createFromResource(TeacherDashboard.this, getResources().getIdentifier("Electrical_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
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
                        ArrayAdapter<CharSequence> adapterCSsub = ArrayAdapter.createFromResource(TeacherDashboard.this, getResources().getIdentifier("IT_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
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
                        ArrayAdapter<CharSequence> adapterCSsub = ArrayAdapter.createFromResource(TeacherDashboard.this, getResources().getIdentifier("Mechanical_subjects_" + i + "_sem", "array", getPackageName()), android.R.layout.simple_spinner_item);
                        adapterCSsub.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        mechanicalsub.setAdapter(adapterCSsub);
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

}