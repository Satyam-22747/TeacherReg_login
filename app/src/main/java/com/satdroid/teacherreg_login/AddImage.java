package com.satdroid.teacherreg_login;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.operation.Merge;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
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

public class AddImage extends AppCompatActivity {

    private FloatingActionButton ft_sel_img;
  private  AppCompatButton upd_btn_image;
    private ImageSwitcher imageView;
    private int PICK_IMAGE_MULTIPLE = 1;
    private    ArrayList<Uri> mArrayUri;
    private ArrayList<String> ImageUrl;
    private int position = 0;
    private FirebaseAuth FAuth;
    private FirebaseFirestore db;

private ArrayList<String> course_selected;

    private RecyclerView recyclerView;
    private ImageAdapterStd adapter;
    private ArrayList<ImageDataModal> imagesList;
    private TextView imageCount_tv;
    private ProgressBar imagePgbar;
    private LinearLayout linearLayout;
    private String imageTime;
    private SimpleDateFormat simpleDateFormat;
    private Calendar calendar;
    private String imageTeacher="";
    private String imageCount="";
    private int count;
    private ProgressBar progressBarimage;
    private TextView noImage_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);


        progressBarimage=findViewById(R.id.pgbar_addimage);
        calendar = Calendar.getInstance();
        ft_sel_img = findViewById(R.id.ftbtn_image);
        FAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        imagePgbar=findViewById(R.id.image_upload_pgbar);
        linearLayout=findViewById(R.id.addimage_linearLayout);
        ImageUrl=new ArrayList<>();
        Intent intent=getIntent();
        noImage_textview=findViewById(R.id.NoImage_tv);

        course_selected=(ArrayList<String>)intent.getSerializableExtra("Selected Course");
        //fetchTeacherName
        db.collection("Teacher").document(FAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Teacher_data_Modal teacher_data_modal =documentSnapshot.toObject(Teacher_data_Modal.class);
                imageTeacher=imageTeacher+teacher_data_modal.getName();
                progressBarimage.setVisibility(View.VISIBLE);
                //Toast.makeText(AddImage.this, "Teacher name fetched: "+imageTeacher, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        //recycler
        imagesList=new ArrayList<>();
        recyclerView = findViewById(R.id.image_recycler_teacher);
        recyclerView.setHasFixedSize (true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddImage.this));

        //recycler view adapter
        adapter=new ImageAdapterStd(AddImage.this,imagesList);
        recyclerView.setAdapter(adapter);
        GetImageDetails();
//        db.collection("Courses").document(course_selected.get(0)).collection(course_selected.get(1)).document(course_selected.get(2))
//                .collection("Images").orderBy("imageCounter", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                        imagesList=new ArrayList<>();
//                        if (error != null) {
//                            Log.w(TAG, "Listen failed.", error);
//                            return;
//                        }
//
//                     //   List<String> cities = new ArrayList<>();
//                        for (QueryDocumentSnapshot doc : value) {
////                            if (doc.get("name") != null) {
////                                cities.add(doc.getString("name"));
////                            }
//                            ImageDataModal imageDataModal = doc.toObject(ImageDataModal.class);
//                            imagesList.add(imageDataModal);
//                        }
//                        count=imagesList.size();
//                        Toast.makeText(AddImage.this, "Image collection size: "+count, Toast.LENGTH_SHORT).show();
//                        adapter.notifyDataSetChanged();
//                    //    Log.d(TAG, "Current cites in CA: " + cities);
//                    }
//                });
        ft_sel_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                View alertLayout=inflater.inflate(R.layout.image_dialogue_student,null);
                imageView=alertLayout.findViewById(R.id.image);
                imageCount_tv=alertLayout.findViewById(R.id.image_count);
                upd_btn_image = alertLayout.findViewById(R.id.upload_image_btn);

                AlertDialog.Builder alertDialog=new AlertDialog.Builder(AddImage.this);
                alertDialog.setView(alertLayout);
                AlertDialog alertDialog1=alertDialog.create();
                alertDialog1.show();

                imageView.setFactory(new ViewSwitcher.ViewFactory() {
                    @Override
                    public View makeView() {
                        ImageView imageView1 = new ImageView(getApplicationContext());
                        return imageView1;
                    }
                });

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // initialising intent
                        Intent intent = new Intent();
                        // setting type to select to be image
                        intent.setType("image/*");
                        // allowing multiple image to be selected
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

                    }
                });
                upd_btn_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        alertDialog1.dismiss();
                        imagePgbar.setVisibility(View.VISIBLE);
                        linearLayout.setClickable(false);
                        UploadImagefirebase();

                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        mArrayUri = new ArrayList<Uri>();

        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
            // Get the Image from data
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    // adding imageuri in array
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    mArrayUri.add(imageurl);
                   // Toast.makeText(this, "picked Image", Toast.LENGTH_SHORT).show();

                }
                // setting 1st selected image into image switcher

                imageView.setImageURI(mArrayUri.get(0));
                position = 0;
            } else {
                Uri imageurl = data.getData();
                mArrayUri.add(imageurl);
                imageView.setImageURI(mArrayUri.get(0));
                position = 0;
            }
        } else {
            // show this if no image is selected
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_SHORT).show();
        }
    }

    public void UploadImagefirebase()
    {
        for (int i = 0; i < mArrayUri.size(); i++)
        {
            Uri IndividualImage = mArrayUri.get(i);
            if (IndividualImage != null) {
                StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("ItemImages");
               // Toast.makeText(AddImage.this,"Inside firebase storage 2",Toast.LENGTH_SHORT).show();
                final StorageReference ImageName = ImageFolder.child("Image" + i + ": " + System.currentTimeMillis());

                int finalI = i;
                ImageName.putFile(IndividualImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                            //    Toast.makeText(AddImage.this," 3 Dwd Url obtained "+uri,Toast.LENGTH_SHORT).show();
                                ImageUrlList(finalI, String.valueOf(uri));
                            }
                        });
                    }
                });

            }
        }
    }
     public void UploadImageFirestore(ArrayList<String> dwdURls) {

         if(!dwdURls.isEmpty()&&dwdURls.size()==mArrayUri.size()) {

             CollectionReference images_teacher = db.collection("Courses").document(course_selected.get(0)).collection(course_selected.get(1))
                     .document(course_selected.get(2)).collection("Images");

             String image_date= new SimpleDateFormat("d-MM-yyyy", Locale.getDefault()).format(new Date());
             simpleDateFormat = new SimpleDateFormat("KK:mm aaa ");

             for (int i = 0; i < dwdURls.size(); i++)
             {
                 imageTime = simpleDateFormat.format(calendar.getTime());
                 count=count+1;
                 imageCount= String.valueOf(count);
                 ImageDataModal uploadImagemodal=new ImageDataModal(dwdURls.get(i),course_selected.get(0),
                         course_selected.get(1),course_selected.get(2),image_date,imageTime,count,imageTeacher);

                    images_teacher.add(uploadImagemodal).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            linearLayout.setClickable(true);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)

                        {
                            imagePgbar.setVisibility(View.GONE);
                            linearLayout.setClickable(true);
                            Toast.makeText(AddImage.this, "Image not uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });
             }
             imagePgbar.setVisibility(View.GONE);
             Toast.makeText(AddImage.this, "Image uploaded", Toast.LENGTH_SHORT).show();
         }
        }

    void ImageUrlList(int i,String Urls)
    {

        ImageUrl.add(Urls);
        if(ImageUrl.size()==mArrayUri.size()) {
          //  Toast.makeText(AddImage.this, "Length of list: " + ImageUrl.size(), Toast.LENGTH_SHORT).show();
            UploadImageFirestore(ImageUrl);
        }
    }

    private void GetImageDetails()
    {
        db.collection("Courses").document(course_selected.get(0)).collection(course_selected.get(1)).document(course_selected.get(2))
                .collection("Images").orderBy("imageCounter", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                ImageDataModal imageDataModal = d.toObject(ImageDataModal.class);
                                imagesList.add(imageDataModal);
                            }
                            count=imagesList.size();
                            adapter.notifyDataSetChanged();
                            progressBarimage.setVisibility(View.GONE);
                            noImage_textview.setText("");
                        } else {
//                            Toast.makeText(AddImage.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                            noImage_textview.setText("No image uploaded");
                            progressBarimage.setVisibility(View.GONE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddImage.this, "Image fetching failed", Toast.LENGTH_SHORT).show();
                        progressBarimage.setVisibility(View.GONE);
                    }
                });
    }
    }

