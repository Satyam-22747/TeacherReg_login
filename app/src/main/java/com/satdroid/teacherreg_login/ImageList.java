package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ImageList extends AppCompatActivity {
    private FirebaseFirestore firestore;
    private ImageAdapter adapter;
    private ImageDataModal model;
    private ArrayList<ImageDataModal> ImagesList;

    String Course_Name,Course_Semester,course_subject,Course_sub_images;
    RecyclerView imageRecycler;

    TextView subjectName;
  //  private MaterialCardView AddNewCan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        imageRecycler=findViewById(R.id.image_recycler);
        firestore=FirebaseFirestore.getInstance();
        subjectName=findViewById(R.id.Sub_stud_tv);

        ImagesList=new ArrayList<>();
        imageRecycler.setHasFixedSize(true);
        GetImageDetails();

        Intent iStudImagelist=getIntent();
        Course_Name=iStudImagelist.getStringExtra("Course_subjects");
        Course_Semester=iStudImagelist.getStringExtra("Course_sem");
        course_subject=iStudImagelist.getStringExtra("subjectname");
        //Course_sub_images=iStudImagelist.getStringExtra("Course_subjects");
        Course_sub_images="Images";


        subjectName.setText(Course_Name);
    }
    private void GetImageDetails()
    {
        firestore.collection("Courses").document(Course_Name).collection(Course_Semester)
                .document(course_subject).collection("Images").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty())
                        {
                            ArrayList<DocumentSnapshot> list= (ArrayList<DocumentSnapshot>) queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d:list)
                            {
                                model=d.toObject(ImageDataModal.class);

                                ImagesList.add(model);
                                adapter=new ImageAdapter(getApplicationContext(),ImagesList);
                                imageRecycler.setAdapter(adapter);
                                LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                                imageRecycler.setLayoutManager(layoutManager);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}