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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TeacherLoginActivity extends AppCompatActivity {

    private TextView fgPass,NewReg;
    private AppCompatButton FacReg_btn,StudReg_btn,login_btn;
    private TextInputLayout email_input,password_input;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private ArrayList<ArrayList<String>> Courses;
    private FirebaseFirestore db;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
////            email_input=findViewById(R.id.email_login);
////            String email=email_input.getEditText().getText().toString().trim();
//            EmailsAndData emailsAndDatauser=new EmailsAndData();
//           if( emailsAndDatauser.checkEmailData(currentUser.getEmail())!=null)
//           {
//               Courses=new ArrayList<>();
//
//               db.collection("Teacher").document(mAuth.getCurrentUser().getUid())
//                                               .collection("Selected course").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                                   @Override
//                                                   public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                                       if (!queryDocumentSnapshots.isEmpty()) {
//
//                                                           List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                                                           for (DocumentSnapshot d : list) {
//                                                               CourseTeacherModal courseTeacherModal = d.toObject(CourseTeacherModal.class);
//                                                               ArrayList<String> checked = new ArrayList<>();
//                                                               checked.add(courseTeacherModal.getCourseSelected());
//                                                               checked.add(courseTeacherModal.getSemester());
//                                                               checked.add(courseTeacherModal.getSubject());
//                                                               Courses.add(checked);
//                                                           }
//                                                           progressBar.setVisibility(View.GONE);
//                                                           Toast.makeText(TeacherLoginActivity.this, "Login success.", Toast.LENGTH_SHORT).show();
//                                                           Intent intent = new Intent(TeacherLoginActivity.this, TeacherDashboard.class);
//                                                           intent.putExtra("Courses List", Courses);
//                                                           startActivity(intent);
//                                                           finish();
//
//                                                           //adapter.notifyDataSetChanged();
//                                                       } else {
//                                                           progressBar.setVisibility(View.GONE);
//                                                           Toast.makeText(TeacherLoginActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
//                                                       }
//                                                   }
//                                               });
//                                   }
//
//           else if(emailsAndDatauser.checkEmailDataStudent(currentUser.getEmail())!=null)
//           {
//               db.collection("Student").document(mAuth.getCurrentUser().getUid())
//                       .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                           @Override
//                           public void onSuccess(DocumentSnapshot documentSnapshot) {
//                               if(documentSnapshot.exists()) {
//                                   StudentDetailDataModel studentDetailDataModel = documentSnapshot.toObject(StudentDetailDataModel.class);
//                                   progressBar.setVisibility(View.GONE);
//                                   Toast.makeText(TeacherLoginActivity.this, "Login success.", Toast.LENGTH_SHORT).show();
//                                   Intent intentStud = new Intent(TeacherLoginActivity.this, StudentDashboard.class);
//                                   intentStud.putExtra("Course_name", studentDetailDataModel.getCourse());
//                                   startActivity(intentStud);
//                                   finish();
//                               }
//                               else {
//                                   progressBar.setVisibility(View.GONE);
//                                   Toast.makeText(TeacherLoginActivity.this, "No student course",
//                                           Toast.LENGTH_SHORT).show();
//                               } }
//                       }).addOnFailureListener(new OnFailureListener() {
//                           @Override
//                           public void onFailure(@NonNull Exception e) {
//
//                               progressBar.setVisibility(View.GONE);
//                               Toast.makeText(TeacherLoginActivity.this, "Failed to fetch student course",
//                                       Toast.LENGTH_SHORT).show();
//                           }
//                       });
//           }
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        fgPass=findViewById(R.id.forgot_pass);
        NewReg=findViewById(R.id.new_user);
        email_input=findViewById(R.id.email_login);
        password_input=findViewById(R.id.pass_login);
        login_btn=findViewById(R.id.login_btn);
        mAuth=FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.pg_bar_login);
        login_btn.setEnabled(true);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

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
                        Intent iRegT=new Intent(TeacherLoginActivity.this, EmailValidation.class);
                        iRegT.putExtra("UserType",FacReg_btn.getText().toString());
                        startActivity(iRegT);
                    }
                });

                StudReg_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent iRegSt=new Intent(TeacherLoginActivity.this, EmailValidation.class);
                        iRegSt.putExtra("UserType",StudReg_btn.getText().toString());
                        startActivity(iRegSt);
                    }
                });
            }
        });


    }

    void loginUser(){

        login_btn.setEnabled(false);

        progressBar.setVisibility(View.VISIBLE);
        Courses=new ArrayList<>();
        EmailsAndData emailAndData = new EmailsAndData();

        String email, password;
        email=email_input.getEditText().getText().toString().trim();
        password=password_input.getEditText().getText().toString().trim();


        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
        {
            Toast.makeText(TeacherLoginActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
        else {
            if(emailAndData.checkEmailData(email)!=null)
            {
                String[] Name_department = (emailAndData.checkEmailData(email));

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
//                                progressBar.setVisibility(View.GONE);
                                    if (Name_department != null) {
                                        db.collection("Teacher").document(mAuth.getCurrentUser().getUid())
                                                .collection("Selected course").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                        if (!queryDocumentSnapshots.isEmpty()) {

                                                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                                            for (DocumentSnapshot d : list) {
                                                                CourseTeacherModal courseTeacherModal = d.toObject(CourseTeacherModal.class);
                                                                ArrayList<String> checked = new ArrayList<>();
                                                                checked.add(courseTeacherModal.getCourseSelected());
                                                                checked.add(courseTeacherModal.getSemester());
                                                                checked.add(courseTeacherModal.getSubject());
                                                                Courses.add(checked);
                                                            }
                                                            progressBar.setVisibility(View.GONE);
                                                            Toast.makeText(TeacherLoginActivity.this, "Login success.", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(TeacherLoginActivity.this, TeacherDashboard.class);
                                                            intent.putExtra("Courses List", Courses);
                                                            startActivity(intent);
                                                            finish();

                                                            //adapter.notifyDataSetChanged();
                                                        } else {
                                                            progressBar.setVisibility(View.GONE);
                                                            Toast.makeText(TeacherLoginActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                }
                                     else {
                                    // If sign in fails, display a message to the user.
                                    progressBar.setVisibility(View.GONE);

                                    Toast.makeText(TeacherLoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
            else if(emailAndData.checkEmailDataStudent(email)!=null)
            {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
//                                progressBar.setVisibility(View.GONE);
                                        db.collection("Student").document(mAuth.getCurrentUser().getUid())
                                                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        if(documentSnapshot.exists()) {
                                                            StudentDetailDataModel studentDetailDataModel = documentSnapshot.toObject(StudentDetailDataModel.class);
                                                            progressBar.setVisibility(View.GONE);
                                                            Toast.makeText(TeacherLoginActivity.this, "Login success.", Toast.LENGTH_SHORT).show();
                                                            Intent intentStud = new Intent(TeacherLoginActivity.this, StudentDashboard.class);
                                                            intentStud.putExtra("Course_name", studentDetailDataModel.getCourse());
                                                            startActivity(intentStud);
                                                            finish();

                                                        }
                                                        else {
                                                            progressBar.setVisibility(View.GONE);
                                                            Toast.makeText(TeacherLoginActivity.this, "No student course",
                                                                    Toast.LENGTH_SHORT).show();
                                                        } }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                        progressBar.setVisibility(View.GONE);
                                                        Toast.makeText(TeacherLoginActivity.this, "Failed to fetch student course",
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(TeacherLoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);

                                Toast.makeText(TeacherLoginActivity.this, "Some error occured",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else {
                // If sign in fails, display a message to the user.
                progressBar.setVisibility(View.GONE);

                Toast.makeText(TeacherLoginActivity.this, "Email not exist",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }
}