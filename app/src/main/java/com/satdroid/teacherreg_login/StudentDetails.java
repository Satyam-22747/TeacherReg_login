package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StudentDetails extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private AppCompatButton attendenceBtn,select_date_btn;
    private ArrayList<String> course_selected;
    private FirebaseFirestore dbS;
    private ArrayList<studentAttendenceModal> student_attendence_DetailList;
    private RecyclerView recyclerStudent;
    private AdapterStudentDetails studentDetailsAdapter;
    private FirebaseAuth FAuth;
    private TextView date_picked_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        attendenceBtn=findViewById(R.id.attendenceStud);
        Intent intent=getIntent();
        course_selected=(ArrayList<String>)intent.getSerializableExtra("Selected Course");
        dbS=FirebaseFirestore.getInstance();
        student_attendence_DetailList=new ArrayList<>();
        date_picked_tv=findViewById(R.id.selected_date);
        select_date_btn=findViewById(R.id.Select_date_btn);
        date_picked_tv.setText( new SimpleDateFormat("d-MM-yyyy", Locale.getDefault()).format(new Date()));
        //recycler
        getStuddents();
        recyclerStudent=findViewById(R.id.recycler_studentDetails);
        recyclerStudent.setHasFixedSize(true);
        recyclerStudent.setLayoutManager(new LinearLayoutManager(StudentDetails.this));
        studentDetailsAdapter=new AdapterStudentDetails(StudentDetails.this,student_attendence_DetailList);
        recyclerStudent.setAdapter(studentDetailsAdapter);

        date_picked_tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                getStuddents();
            }
        });
        select_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.satdroid.teacherreg_login.DatePicker mDatePickerDialogFragment;
                mDatePickerDialogFragment = new com.satdroid.teacherreg_login.DatePicker();
                mDatePickerDialogFragment.show(getSupportFragmentManager(), "DATE PICK");
            }
        });
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
        student_attendence_DetailList.clear();
        FAuth= FirebaseAuth.getInstance();
        dbS.collection("Teacher").document(FAuth.getCurrentUser().getUid())
                .collection("StudentAttendence").document(course_selected.get(0)).collection(course_selected.get(1))
                .document(course_selected.get(2)).collection(date_picked_tv.getText().toString()).orderBy("rollNO", Query.Direction.ASCENDING)
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (error != null) {
                                    Log.w("Firestore error", "Listen failed.", error);
                                    return;
                                }
                                for (DocumentChange dc:value.getDocumentChanges()) {
                                    if (dc.getType() == DocumentChange. Type.ADDED){
                                        Toast.makeText(StudentDetails.this, "Att fetched", Toast.LENGTH_SHORT).show();
                                        student_attendence_DetailList.add(dc.getDocument().toObject (studentAttendenceModal.class));
                                    }
                                }
                                studentDetailsAdapter.notifyDataSetChanged();

                            }
                        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        if(month<=9)
        date_picked_tv.setText(dayOfMonth+"-"+"0"+(month+1)+"-"+year);
        else
            date_picked_tv.setText(dayOfMonth+"-"+(month+1)+"-"+year);
    }

}