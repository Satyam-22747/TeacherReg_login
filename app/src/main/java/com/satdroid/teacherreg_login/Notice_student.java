package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Notice_student extends AppCompatActivity {

    private FirebaseFirestore NoticeFirestore;
    private ArrayList<NoticeDataModel> Notice_List;

    private RecyclerView noticeRecycler;
    private String Course_Name;
    int  Course_Semester;
    private NoticeAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_student);

        Intent iStudNotice=getIntent();
        Course_Name=iStudNotice.getStringExtra("Course Name");
        Course_Semester=iStudNotice.getIntExtra("Course Sem",0);
        //course_subject=iStudNotice.getStringExtra("Course_subjects");

        Toast.makeText(Notice_student.this,"course: "+Course_Name,Toast.LENGTH_SHORT).show();
        Toast.makeText(Notice_student.this,"course sem: "+Course_Semester,Toast.LENGTH_SHORT).show();

        NoticeFirestore=FirebaseFirestore.getInstance();
        Notice_List=new ArrayList<>();

        noticeRecycler = findViewById(R.id.notice_recycler_student);
        noticeRecycler.setHasFixedSize (true);
        noticeRecycler.setLayoutManager(new LinearLayoutManager(Notice_student.this));

        adapter=new NoticeAdapter(Notice_student.this,Notice_List);
        noticeRecycler.setAdapter(adapter);
        GetNoticeDetails();
    }

    private void GetNoticeDetails()
    {
        String today_date = new SimpleDateFormat("d-MM-yyyy", Locale.getDefault()).format(new Date());

        NoticeFirestore.collection("Courses").document(Course_Name).collection(String.valueOf(Course_Semester)).document("Notices")
                .collection(today_date).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                NoticeDataModel noticeDataModel = d.toObject(NoticeDataModel.class);
                                Notice_List.add(noticeDataModel);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(Notice_student.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Notice_student.this, "Image fetching failed", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}