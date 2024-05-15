package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class CreateNotice extends AppCompatActivity {

    private TextInputLayout createNotice_textINput;
    private AppCompatButton UploadNotice_btn;
    private FirebaseFirestore dbNotice;
    private ProgressBar progressBarNotice;
    private ArrayList<String> courseDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice);

        createNotice_textINput = findViewById(R.id.writeNotice);
        UploadNotice_btn = findViewById(R.id.upload_notice_btn);
        progressBarNotice=findViewById(R.id.pg_notice);

        dbNotice = FirebaseFirestore.getInstance();

        Intent iCreatenotice = getIntent();

        courseDetail = (ArrayList<String>) iCreatenotice.getSerializableExtra("Selected Course");

        UploadNotice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotice_textINput.setErrorEnabled(false);
                createNotice_textINput.setError("");
                boolean isValidltext = false;
                if (TextUtils.isEmpty(createNotice_textINput.getEditText().getText().toString().trim())) {
                    createNotice_textINput.setErrorEnabled(true);
                    createNotice_textINput.setError("Empty notice cant be uploaded");
                } else {
                    isValidltext = true;
                }
                if (isValidltext) {
                    progressBarNotice.setVisibility(View.VISIBLE);
                    String today_date = new SimpleDateFormat("d-MM-yyyy", Locale.getDefault()).format(new Date());

                    CollectionReference notice_teacher = dbNotice.collection("Courses").document(courseDetail.get(0)).collection(courseDetail.get(1))
                            .document("Notices").collection(today_date);


                    HashMap<String, String> NoticeHash = new HashMap<>();
                    NoticeHash.put("Notice", createNotice_textINput.getEditText().getText().toString().trim());

                    NoticeHash.put("CourseSelected", courseDetail.get(0));
                    NoticeHash.put("semesterName", courseDetail.get(1));
                    NoticeHash.put("subjectName", courseDetail.get(2));
                    NoticeHash.put("NoticeDate", today_date);

                    notice_teacher.add(NoticeHash).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            progressBarNotice.setVisibility(View.GONE);
                            Toast.makeText(CreateNotice.this, "Notice uploaded", Toast.LENGTH_SHORT).show();
                            Intent  iUploadteNotice=new Intent(CreateNotice.this,UploadNotice.class);
                            iUploadteNotice.putExtra("Selected Course",courseDetail);
                            startActivity(iUploadteNotice);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBarNotice.setVisibility(View.GONE);
                            Toast.makeText(CreateNotice.this, "Notice not uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

        });
    }
}