package com.satdroid.teacherreg_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class EmailValidation extends AppCompatActivity {
private TextInputLayout valid_email;
private AppCompatButton Validate_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_validation);

        valid_email=findViewById(R.id.validataion_email);
        Validate_btn=findViewById(R.id.validate_btn);
        EmailsAndData emailAndData = new EmailsAndData();

        Validate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name,Department,NameStud,RollNO,DepartmentStud;
                String email_txt=valid_email.getEditText().getText().toString().trim();

                Intent iUser=getIntent();
                String UserType=iUser.getStringExtra("UserType");
                if(UserType.equals("Teacher")) {
                    String[] Name_department = (emailAndData.checkEmailData(email_txt));
                    if (Name_department != null) {
                        Name = Name_department[0];
                        Department = Name_department[1];

                        Intent iTeacher = new Intent(EmailValidation.this, MainActivity.class);
                        iTeacher.putExtra("Name", Name);
                        iTeacher.putExtra("Department", Department);
                        iTeacher.putExtra("Email", email_txt);
                        startActivity(iTeacher);

                        Toast.makeText(EmailValidation.this, "Email verified", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(EmailValidation.this, "Teacher Email Does not exist", Toast.LENGTH_SHORT).show();
                }
                if(UserType.equals("Student"))
                {
                    String[] Name_roll_department = (emailAndData.checkEmailDataStudent(email_txt));
                    if (Name_roll_department != null) {
                        NameStud = Name_roll_department[0];
                        RollNO=Name_roll_department[1];
                        DepartmentStud = Name_roll_department[2];

                        Intent iStud = new Intent(EmailValidation.this, Student_Registration.class);
                        iStud.putExtra("NameStud", NameStud);
                        iStud.putExtra("RollNo", RollNO);
                        iStud.putExtra("DepartmentStud", DepartmentStud);
                        iStud.putExtra("EmailStud", email_txt);
                        startActivity(iStud);

                        Toast.makeText(EmailValidation.this, "Email verified", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(EmailValidation.this, "Student Email Does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}