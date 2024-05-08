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
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private ArrayList<ImageDataModal> imagesList;
    private ImageView pdfImage;
    private TextView pdfCount_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pdf);

        dbF=FirebaseFirestore.getInstance();
//        pdfImage = findViewById(R.id.pdf);
//        upd_btn_pdf = findViewById(R.id.upload_pdf);
        ft_sel_pdf = findViewById(R.id.ftbtn_pdf);
        mArrayUri = new ArrayList<Uri>();
        pdfUrl=new ArrayList<>();


        Intent intent=getIntent();
        course_selected=(ArrayList<String>)intent.getSerializableExtra("Selected Course");
        //recycler
        imagesList=new ArrayList<>();
        recyclerView = findViewById(R.id.pdf_recycler_teacher);
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddPDF.this));

        //recycler view adapter
        adapter=new PdfAdapterStd(AddPDF.this,imagesList);
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
                    Toast.makeText(this, "picked pdf", Toast.LENGTH_SHORT).show();

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
            Toast.makeText(AddPDF.this,"Inside loop 1",Toast.LENGTH_SHORT).show();
            Uri IndividualPdf = mArrayUri.get(i);
            if (IndividualPdf != null) {
                StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("ItemPDFs");
                Toast.makeText(AddPDF.this,"Inside firebase storage 2",Toast.LENGTH_SHORT).show();
                final StorageReference PDFName = ImageFolder.child("Pdf" + i + ": " + System.currentTimeMillis());

                int finalI = i;
                PDFName.putFile(IndividualPdf).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        PDFName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Toast.makeText(AddPDF.this," 3 Dwd Url obtained "+uri,Toast.LENGTH_SHORT).show();
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
            Toast.makeText(AddPDF.this, "Length of list: " + pdfUrl.size(), Toast.LENGTH_SHORT).show();
            UploadPDFFirestore(pdfUrl);
        }
    }

    public void UploadPDFFirestore(ArrayList<String> dwdURls) {
        //   we need list that images urls
        Toast.makeText(AddPDF.this,"4 dwdURls Size: "+dwdURls.size(),Toast.LENGTH_SHORT).show();
        if(!dwdURls.isEmpty()&&dwdURls.size()==mArrayUri.size())
        {
            CollectionReference images_teacher = dbF.collection("Courses").document(course_selected.get(0)).collection(course_selected.get(1))
                    .document(course_selected.get(2)).collection("Pdf");
            HashMap<String, String> imageHash = new HashMap<>();

            for (int i = 0; i < dwdURls.size(); i++) {
                imageHash.put("ImageUrl", dwdURls.get(i));

                imageHash.put("CourseSelected", course_selected.get(0));
                imageHash.put("semesterName", course_selected.get(1));
                imageHash.put("subjectName", course_selected.get(2));

                images_teacher.add(imageHash).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddPDF.this, "Images url in firestore 4", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddPDF.this, "Images url not in firestore 4", Toast.LENGTH_SHORT).show();

                    }
                });}
        }

        else {
            Toast.makeText(AddPDF.this, "dwdURls is empty 5", Toast.LENGTH_SHORT).show();
        }
    }

    private void GetPDFDetails()
    {
        Toast.makeText(AddPDF.this, "Inside get pdf", Toast.LENGTH_SHORT).show();

        dbF.collection("Courses").document(course_selected.get(0)).collection(course_selected.get(1)).document(course_selected.get(2))
                .collection("Pdf").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                            Toast.makeText(AddPDF.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddPDF.this, "Pdf fetching failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}