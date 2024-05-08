package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class StudentDetails extends AppCompatActivity {

    private AppCompatButton attendenceBtn;
    private ArrayList<String> course_selected;
    private FirebaseFirestore dbS;
    private ArrayList<studentAttendenceModal> student_attendence_DetailList;
    private RecyclerView recyclerStudent;
    private AdapterStudentDetails studentDetailsAdapter;
    private FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        attendenceBtn=findViewById(R.id.attendenceStud);
        Intent intent=getIntent();
        course_selected=(ArrayList<String>)intent.getSerializableExtra("Selected Course");
        dbS=FirebaseFirestore.getInstance();
        student_attendence_DetailList=new ArrayList<>();

        //recycler
        recyclerStudent=findViewById(R.id.recycler_studentDetails);
        recyclerStudent.setHasFixedSize(true);
        recyclerStudent.setLayoutManager(new LinearLayoutManager(StudentDetails.this));
        studentDetailsAdapter=new AdapterStudentDetails(StudentDetails.this,student_attendence_DetailList);
        recyclerStudent.setAdapter(studentDetailsAdapter);

        getStuddents();
        attendenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iAttendence =new Intent(StudentDetails.this,TakeAttendence.class);
                iAttendence.putExtra("Selected Course",course_selected);
                startActivity(iAttendence);
            }
        });
    }
    private void getStuddents()
    {
        FAuth= FirebaseAuth.getInstance();
        dbS.collection("Teacher").document(FAuth.getCurrentUser().getUid())
                .collection("StudentAttendence").document(course_selected.get(0)).collection(course_selected.get(1))
                .document(course_selected.get(2)).collection("8-05-2024").orderBy("rollNO", Query.Direction.ASCENDING)
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (error != null) {
                                    Log.w("Firestore error", "Listen failed.", error);
                                    return;
                                }
                                for (DocumentChange dc:value.getDocumentChanges()) {
                                    if (dc.getType() == DocumentChange. Type.ADDED){
                                        student_attendence_DetailList.add(dc.getDocument().toObject (studentAttendenceModal.class));
                                    }
                                }
                                studentDetailsAdapter.notifyDataSetChanged();

                            }
                        });
    }



}