package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 1000; // 5 seconds
    private FirebaseAuth mAuth;
    private ArrayList<ArrayList<String>> Courses;
    private FirebaseFirestore db;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        mAuth=FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
////            email_input=findViewById(R.id.email_login);
////            String email=email_input.getEditText().getText().toString().trim();
//            db = FirebaseFirestore.getInstance();
//
//            EmailsAndData emailsAndDatauser=new EmailsAndData();
//            if( emailsAndDatauser.checkEmailData(currentUser.getEmail())!=null)
//            {
//                Courses=new ArrayList<>();
//
//                db.collection("Teacher").document(mAuth.getCurrentUser().getUid())
//                        .collection("Selected course").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                            @Override
//                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                if (!queryDocumentSnapshots.isEmpty()) {
//
//                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                                    for (DocumentSnapshot d : list) {
//                                        CourseTeacherModal courseTeacherModal = d.toObject(CourseTeacherModal.class);
//                                        ArrayList<String> checked = new ArrayList<>();
//                                        checked.add(courseTeacherModal.getCourseSelected());
//                                        checked.add(courseTeacherModal.getSemester());
//                                        checked.add(courseTeacherModal.getSubject());
//                                        Courses.add(checked);
//                                    }
//                                 //   progressBar.setVisibility(View.GONE);
//                                    Toast.makeText(SplashScreen.this, "Login success.", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(SplashScreen.this, TeacherDashboard.class);
//                                    intent.putExtra("Courses List", Courses);
//                                    startActivity(intent);
//                                    finish();
//
//                                    //adapter.notifyDataSetChanged();
//                                } else {
//                                  //  progressBar.setVisibility(View.GONE);
//                                    Toast.makeText(SplashScreen.this, "No data found in Database", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//
//            else if(emailsAndDatauser.checkEmailDataStudent(currentUser.getEmail())!=null)
//            {
//                db.collection("Student").document(mAuth.getCurrentUser().getUid())
//                        .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                if(documentSnapshot.exists()) {
//                                    StudentDetailDataModel studentDetailDataModel = documentSnapshot.toObject(StudentDetailDataModel.class);
//                                  //  progressBar.setVisibility(View.GONE);
//                                    Toast.makeText(SplashScreen.this, "Login success.", Toast.LENGTH_SHORT).show();
//                                    Intent intentStud = new Intent(SplashScreen.this, StudentDashboard.class);
//                                    intentStud.putExtra("Course_name", studentDetailDataModel.getCourse());
//                                    startActivity(intentStud);
//                                    finish();
//                                }
//                                else {
//                                  //  progressBar.setVisibility(View.GONE);
//                                    Toast.makeText(SplashScreen.this, "No student course",
//                                            Toast.LENGTH_SHORT).show();
//                                } }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                              //  progressBar.setVisibility(View.GONE);
//                                Toast.makeText(SplashScreen.this, "Failed to fetch student course", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mAuth=FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null){
//            email_input=findViewById(R.id.email_login);
//            String email=email_input.getEditText().getText().toString().trim();
                    db = FirebaseFirestore.getInstance();

                    EmailsAndData emailsAndDatauser=new EmailsAndData();
                    if( emailsAndDatauser.checkEmailData(currentUser.getEmail())!=null)
                    {
                        Courses=new ArrayList<>();

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
                                            //   progressBar.setVisibility(View.GONE);
                                            Toast.makeText(SplashScreen.this, "Login success.", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SplashScreen.this, TeacherDashboard.class);
                                            intent.putExtra("Courses List", Courses);
                                            startActivity(intent);
                                            finish();

                                            //adapter.notifyDataSetChanged();
                                        } else {
                                            //  progressBar.setVisibility(View.GONE);
                                            Toast.makeText(SplashScreen.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }

                    else if(emailsAndDatauser.checkEmailDataStudent(currentUser.getEmail())!=null)
                    {
                        db.collection("Student").document(mAuth.getCurrentUser().getUid())
                                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if(documentSnapshot.exists()) {
                                            StudentDetailDataModel studentDetailDataModel = documentSnapshot.toObject(StudentDetailDataModel.class);
                                            //  progressBar.setVisibility(View.GONE);
                                            Toast.makeText(SplashScreen.this, "Login success.", Toast.LENGTH_SHORT).show();
                                            Intent intentStud = new Intent(SplashScreen.this, StudentDashboard.class);
                                            intentStud.putExtra("Course_name", studentDetailDataModel.getCourse());
                                            startActivity(intentStud);
                                            finish();
                                        }
                                        else {
                                            //  progressBar.setVisibility(View.GONE);
                                            Toast.makeText(SplashScreen.this, "No student course",
                                                    Toast.LENGTH_SHORT).show();
                                        } }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //  progressBar.setVisibility(View.GONE);
                                        Toast.makeText(SplashScreen.this, "Failed to fetch student course", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
                else {
                    Intent i = new Intent(SplashScreen.this, TeacherLoginActivity.class);
                    startActivity(i);
                    finish();

                }
                // Close this activity
            }
        }, SPLASH_TIME_OUT);
    }
}