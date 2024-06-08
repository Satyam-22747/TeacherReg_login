package com.satdroid.teacherreg_login;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class ImageList extends AppCompatActivity {
    private FirebaseFirestore firestore;

    RecyclerView recyclerView;
    private ImageAdapterStd adapter;
    String Course_Name,course_subject;
    int  Course_Semester;
    ArrayList<ImageDataModal> imagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        firestore=FirebaseFirestore.getInstance();
        Intent iStudImagelist=getIntent();
        Course_Name=iStudImagelist.getStringExtra("Course_Name");
       Course_Semester=iStudImagelist.getIntExtra("Course_sem",0);
        course_subject=iStudImagelist.getStringExtra("Course_subjects");
        //recyclerview
        firestore=FirebaseFirestore.getInstance();
        imagesList=new ArrayList<>();
        recyclerView = findViewById(R.id.image_recycler);
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ImageList.this));
        //firestore
        adapter=new ImageAdapterStd(ImageList.this,imagesList);
        recyclerView.setAdapter(adapter);
        GetImageDetails();
    }
    private void GetImageDetails()
    {
        firestore.collection("Courses").document(Course_Name).collection(String.valueOf(Course_Semester)).document(course_subject)
                .collection("Images").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                ImageDataModal imageDataModal = d.toObject(ImageDataModal.class);
                                imagesList.add(imageDataModal);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ImageList.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ImageList.this, "Image fetching failed", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}