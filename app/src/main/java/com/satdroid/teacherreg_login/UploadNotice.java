package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UploadNotice extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonNotice;
    private ArrayList<String> course_selected;

    private FirebaseFirestore NoticeFirestore;
    private ArrayList<NoticeDataModel> Notice_List;

    private RecyclerView noticeRecycler;
    private NoticeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);


        NoticeFirestore=FirebaseFirestore.getInstance();
        Notice_List=new ArrayList<>();
        Intent inotice=getIntent();
        course_selected=(ArrayList<String>)inotice.getSerializableExtra("Selected Course");
        noticeRecycler = findViewById(R.id.notice_recycler_teacher);
        noticeRecycler.setHasFixedSize (true);
        noticeRecycler.setLayoutManager(new LinearLayoutManager(UploadNotice.this));

        adapter=new NoticeAdapter(UploadNotice.this,Notice_List);
        noticeRecycler.setAdapter(adapter);

        GetNoticeDetails();


        floatingActionButtonNotice=findViewById(R.id.ftbtn_notice);



        floatingActionButtonNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  iCreateNotice=new Intent(UploadNotice.this,CreateNotice.class);
                iCreateNotice.putExtra("Selected Course",course_selected);
                startActivity(iCreateNotice);
                finish();
            }
        });
    }
    private void GetNoticeDetails()
    {
        String today_date = new SimpleDateFormat("d-MM-yyyy", Locale.getDefault()).format(new Date());

        NoticeFirestore.collection("Courses").document(course_selected.get(0)).collection(course_selected.get(1)).document("Notices")
                .collection(today_date).whereEqualTo("subjectName",course_selected.get(2)).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                NoticeDataModel noticeDataModel = d.toObject(NoticeDataModel.class);
                                Notice_List.add(noticeDataModel);
                                Toast.makeText(UploadNotice.this, "fetching notice", Toast.LENGTH_SHORT).show();
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(UploadNotice.this, "No Notices Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadNotice.this, "Notice fetching failed", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}