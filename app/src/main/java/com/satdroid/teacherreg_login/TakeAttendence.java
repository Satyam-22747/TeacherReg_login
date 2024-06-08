package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.LeadingMarginSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TakeAttendence extends AppCompatActivity implements AttendenceListener {

    private AppCompatButton save_attendenceBtn;
    private ArrayList<String> course_selected;
    private FirebaseFirestore dbSave;
    private ArrayList<StudentDetailDataModel> studentDetailList;
    private RecyclerView recyclerStudent;
    private AdapterStudentDetails studentDetailsAdapter;
    private ArrayList<studentAttendenceModal> studentAttendenceModalArrayList;
    private FirebaseAuth FAuth;
    private ProgressBar attendencePGbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendence);

        save_attendenceBtn=findViewById(R.id.Save_attendence);
        Intent intent=getIntent();
        course_selected=(ArrayList<String>)intent.getSerializableExtra("Selected Course");
        dbSave=FirebaseFirestore.getInstance();
        studentDetailList=new ArrayList<>();
        attendencePGbar=findViewById(R.id.pgBar_attendence);

        //recycler
        recyclerStudent=findViewById(R.id.recycler_save_student_Attendence);
        recyclerStudent.setHasFixedSize(true);
        recyclerStudent.setLayoutManager(new LinearLayoutManager(TakeAttendence.this));
        studentDetailsAdapter=new AdapterStudentDetails(TakeAttendence.this,studentDetailList,this);
        recyclerStudent.setAdapter(studentDetailsAdapter);

        getStuddents();

        studentAttendenceModalArrayList=new ArrayList<>();
        save_attendenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAttendence();

            }
        });
    }

    private void getStuddents()
    {
        attendencePGbar.setVisibility(View.VISIBLE);
        dbSave.collection("Student")
                .whereEqualTo("Course", course_selected.get(0)).orderBy("Name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("Firestore error", "Listen failed.", e);
                            return;
                        }
                        for (DocumentChange dc:value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange. Type.ADDED){
                                studentDetailList.add(dc.getDocument().toObject (StudentDetailDataModel.class));
                            }
                        }
                        studentDetailsAdapter.notifyDataSetChanged();
                        attendencePGbar.setVisibility(View.GONE);
                    }
                });
    }

    public void saveAttendence()
    {
        attendencePGbar.setVisibility(View.VISIBLE);
        if(studentAttendenceModalArrayList.isEmpty()) {
            Toast.makeText(TakeAttendence.this, "Empty attendence can not be saved", Toast.LENGTH_SHORT).show();
            attendencePGbar.setVisibility(View.GONE);
        }
        else {
            FAuth= FirebaseAuth.getInstance();
            String today_date = new SimpleDateFormat("d-MM-yyyy", Locale.getDefault()).format(new Date());
            Toast.makeText(TakeAttendence.this, "Size: " + studentAttendenceModalArrayList.size(), Toast.LENGTH_SHORT).show();
            int i;
            for(i=0;i<studentAttendenceModalArrayList.size();i++)
            {
                DocumentReference courseCollection = dbSave.collection("Teacher").document(FAuth.getCurrentUser().getUid())
                        .collection("StudentAttendence").document(course_selected.get(0)).collection(course_selected.get(1))
                        .document(course_selected.get(2)).collection(today_date).document(studentAttendenceModalArrayList.get(i).getRollNO());
                courseCollection.set(studentAttendenceModalArrayList.get(i)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        attendencePGbar.setVisibility(View.GONE);
                        Toast.makeText(TakeAttendence.this, "Attendence save failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            attendencePGbar.setVisibility(View.GONE);
            Toast.makeText(TakeAttendence.this, "Attendence Saved", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onAttendenceSelected(ArrayList<studentAttendenceModal> stattmodalArraylist) {
        Toast.makeText(TakeAttendence.this, "Size: "+stattmodalArraylist.size(), Toast.LENGTH_SHORT).show();
        studentAttendenceModalArrayList.addAll(stattmodalArraylist);
    }
}