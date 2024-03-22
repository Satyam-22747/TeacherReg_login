package com.satdroid.teacherreg_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherLoginActivity extends AppCompatActivity {

    TextView fgPass,NewReg;
    AppCompatButton FacReg_btn,StudReg_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        fgPass=findViewById(R.id.forgot_pass);
        NewReg=findViewById(R.id.new_user);

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout=inflater.inflate(R.layout.reg_choice_design,null);

        FacReg_btn=alertLayout.findViewById(R.id.faculty_reg);
        StudReg_btn=alertLayout.findViewById(R.id.student_reg);
        fgPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TeacherLoginActivity.this, "Forgot Password", Toast.LENGTH_SHORT).show();
            }
        });

        NewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(TeacherLoginActivity.this);
                alertDialog.setView(alertLayout);

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog1=alertDialog.create();
                alertDialog1.show();
                FacReg_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent iRegT=new Intent(TeacherLoginActivity.this,MainActivity.class);
                        startActivity(iRegT);
                    }
                });

                StudReg_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent iRegSt=new Intent(TeacherLoginActivity.this, Student_Registration.class);
                        startActivity(iRegSt);
                    }
                });
            }
        });


    }
}