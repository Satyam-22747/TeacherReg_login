package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AddPDF extends AppCompatActivity {

    private FirebaseFirestore dbF;
    private FloatingActionButton ft_sel_pdf;
    private AppCompatButton upd_btn_pdf;
    private int PICK_PDF_MULTIPLE = 1;
    private int position = 0;

    private ArrayList<Uri> mArrayUri;
    private ArrayList<String> pdfUrl;
    private ArrayList<String> course_selected;

    private RecyclerView recyclerView;
    private PdfAdapterStd adapter;
    private ArrayList<PdfDataModal> PdfList;
    private ImageView pdfImage;
    private TextView pdfCount_tv;
    private ProgressBar progressBarPdf;
    private int count;
    private FirebaseAuth FAuth;
    private String PdfTeacher="",PdfTime="";

    private SimpleDateFormat simpleDateFormat;
    private Calendar calendar;
    private TextView noPdf_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pdf);

        FAuth=FirebaseAuth.getInstance();
        progressBarPdf=findViewById(R.id.pgbar_addpdf);
        calendar = Calendar.getInstance();
        noPdf_tv=findViewById(R.id.NoPdf_tv);
        dbF=FirebaseFirestore.getInstance();
        ft_sel_pdf = findViewById(R.id.ftbtn_pdf);
        mArrayUri = new ArrayList<Uri>();
        pdfUrl=new ArrayList<>();


        Intent intent=getIntent();
        course_selected=(ArrayList<String>)intent.getSerializableExtra("Selected Course");

        //fetchTeacherName
        dbF.collection("Teacher").document(FAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Teacher_data_Modal teacher_data_modal =documentSnapshot.toObject(Teacher_data_Modal.class);
                PdfTeacher=PdfTeacher+teacher_data_modal.getName();
                progressBarPdf.setVisibility(View.VISIBLE);
                Toast.makeText(AddPDF.this, "Teacher name fetched: "+PdfTeacher, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        //recycler
        PdfList=new ArrayList<>();
        recyclerView = findViewById(R.id.pdf_recycler_teacher);
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddPDF.this));

        //recycler view adapter
        adapter=new PdfAdapterStd(AddPDF.this,PdfList);
        recyclerView.setAdapter(adapter);



        GetPDFDetails();

        ft_sel_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                View alertLayout=inflater.inflate(R.layout.pdf_dialogue_teacher,null);
                pdfImage=alertLayout.findViewById(R.id.pdf);
                pdfCount_tv=alertLayout.findViewById(R.id.pdf_count);
                upd_btn_pdf = alertLayout.findViewById(R.id.upload_pdf);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(AddPDF.this);
                alertDialog.setView(alertLayout);
                AlertDialog alertDialog1=alertDialog.create();
                alertDialog1.show();
                upd_btn_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                alertDialog1.dismiss();
                progressBarPdf.setVisibility(View.VISIBLE);
                UploadPdffirebase();

            }
        });
                pdfImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // initialising intent
                Intent intent = new Intent();
                // setting type to select to be image
                intent.setType("application/pdf");
                // allowing multiple pdf to be selected
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_MULTIPLE);

                    }
                });
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == PICK_PDF_MULTIPLE && resultCode == RESULT_OK && null != data) {
            // Get the Image from data
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    // adding pdfuri in array
                    Uri pdfurl = data.getClipData().getItemAt(i).getUri();
                    mArrayUri.add(pdfurl);
                //    Toast.makeText(this, "picked pdf", Toast.LENGTH_SHORT).show();

                }
                // setting 1st selected image into image switcher
                pdfCount_tv.setText(""+mArrayUri.size()+" Pdf Selected");

                //  imageView.setImageURI(mArrayUri.get(0));
                position = 0;
            } else {
                Uri imageurl = data.getData();
                mArrayUri.add(imageurl);
               // imageView.setImageURI(mArrayUri.get(0));
//                pdfCount_tv.setText(""+mArrayUri.size()+" Selected");
                position = 0;
            }
        } else {
            // show this if no image is selected
            Toast.makeText(this, "You haven't picked pdf", Toast.LENGTH_SHORT).show();
        }
    }
    public void UploadPdffirebase()
    {
        for (int i = 0; i < mArrayUri.size(); i++)
        {
            Uri IndividualPdf = mArrayUri.get(i);
            if (IndividualPdf != null) {
                StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("ItemPDFs");
                final StorageReference PDFName = ImageFolder.child("Pdf" + i + ": " + System.currentTimeMillis());

                int finalI = i;
                PDFName.putFile(IndividualPdf).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        PDFName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
//                                Toast.makeText(AddPDF.this," 3 Dwd Url obtained "+uri,Toast.LENGTH_SHORT).show();
                                PDFUrlList(finalI, String.valueOf(uri));
                            }
                        });
                    }
                });

            }
        }
    }

    void PDFUrlList(int i,String Urls)
    {
        pdfUrl.add(Urls);
        if(pdfUrl.size()==mArrayUri.size()) {
            UploadPDFFirestore(pdfUrl);
        }
    }

    public void UploadPDFFirestore(ArrayList<String> dwdURls) {
        //   we need list that images urls
        if(!dwdURls.isEmpty()&&dwdURls.size()==mArrayUri.size())
        {
            CollectionReference images_teacher = dbF.collection("Courses").document(course_selected.get(0)).collection(course_selected.get(1))
                    .document(course_selected.get(2)).collection("Pdf");
//            HashMap<String, String> imageHash = new HashMap<>();

            String pdf_date= new SimpleDateFormat("d-MM-yyyy", Locale.getDefault()).format(new Date());
            simpleDateFormat = new SimpleDateFormat("KK:mm aaa ");
            for (int i = 0; i < dwdURls.size(); i++) {

                PdfTime = simpleDateFormat.format(calendar.getTime());
                count=count+1;
                PdfDataModal uploadPdfmodal=new PdfDataModal(course_selected.get(0),count,pdf_date,PdfTime,dwdURls.get(i),
                        course_selected.get(1),course_selected.get(2),PdfTeacher);


                images_teacher.add(uploadPdfmodal).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddPDF.this, "Pdf not uploaded", Toast.LENGTH_SHORT).show();
                        progressBarPdf.setVisibility(View.GONE);

                    }
                });
            }
            Toast.makeText(AddPDF.this, "PDf Uploaded", Toast.LENGTH_SHORT).show();
            progressBarPdf.setVisibility(View.GONE);

        }

        else {
            Toast.makeText(AddPDF.this, "Some error occured", Toast.LENGTH_SHORT).show();
            progressBarPdf.setVisibility(View.GONE);
        }
    }

    private void GetPDFDetails()
    {
//        Toast.makeText(AddPDF.this, "Inside get pdf", Toast.LENGTH_SHORT).show();

        dbF.collection("Courses").document(course_selected.get(0)).collection(course_selected.get(1)).document(course_selected.get(2))
                .collection("Pdf").orderBy("pdfCounter", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                PdfDataModal pdfDataModal = d.toObject(PdfDataModal.class);
                                PdfList.add(pdfDataModal);
                                Toast.makeText(AddPDF.this, "pdf found", Toast.LENGTH_SHORT).show();
                            }
                            count=PdfList.size();
                            adapter.notifyDataSetChanged();
                            progressBarPdf.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                            noPdf_tv.setText("");

                        } else {
//                            Toast.makeText(AddPDF.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                            noPdf_tv.setText("No Pdf Uploaded");
                            progressBarPdf.setVisibility(View.GONE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddPDF.this, "Pdf fetching failed", Toast.LENGTH_SHORT).show();
                        progressBarPdf.setVisibility(View.GONE);
                    }
                });
    }


}