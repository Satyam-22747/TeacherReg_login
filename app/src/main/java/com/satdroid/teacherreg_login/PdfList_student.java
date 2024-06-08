package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PdfList_student extends AppCompatActivity {

    private FirebaseFirestore firestore;

    private String Course_Name,course_subject;
    private RecyclerView PdfRecycler;
    int  Course_Semester;
    private ArrayList<PdfDataModal> PdfList;
    private PdfAdapterStd adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_list_student);

        Intent iStudImagelist=getIntent();
        Course_Name=iStudImagelist.getStringExtra("Course_Name");
        Course_Semester=iStudImagelist.getIntExtra("Course_sem",0);
        course_subject=iStudImagelist.getStringExtra("Course_subjects");


        Toast.makeText(PdfList_student.this,""+Course_Name+" "+Course_Semester+" "+course_subject,Toast.LENGTH_SHORT).show();
        //recyclerview
        PdfList=new ArrayList<>();
        PdfRecycler=findViewById(R.id.pdf_recycler_student);
        firestore=FirebaseFirestore.getInstance();
        PdfRecycler.setHasFixedSize (true);
        PdfRecycler.setLayoutManager(new LinearLayoutManager(PdfList_student.this));
        //firestore

        //recycler view adapter
        adapter=new PdfAdapterStd(PdfList_student.this,PdfList);
        PdfRecycler.setAdapter(adapter);

        GetPDFDetails();
    }

    private void GetPDFDetails()
    {
        firestore.collection("Courses").document(Course_Name).collection(String.valueOf(Course_Semester)).document(course_subject)
                .collection("Pdf").orderBy("pdfCounter", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                PdfDataModal pdfDataModal = d.toObject(PdfDataModal.class);
                                PdfList.add(pdfDataModal);
                                Toast.makeText(PdfList_student.this, "pdf found", Toast.LENGTH_SHORT).show();
                            }
                            adapter.notifyDataSetChanged();
                          //  progressBarPdf.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(PdfList_student.this, "No pdf found in Database", Toast.LENGTH_SHORT).show();
                           // progressBarPdf.setVisibility(View.GONE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PdfList_student.this, "Pdf fetching failed", Toast.LENGTH_SHORT).show();
                      //  progressBarPdf.setVisibility(View.GONE);
                    }
                });
    }

}