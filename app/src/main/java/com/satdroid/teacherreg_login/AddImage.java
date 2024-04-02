package com.satdroid.teacherreg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddImage extends AppCompatActivity {

    private FloatingActionButton ft_sel_img;

    AppCompatButton upd_btn;
    ImageSwitcher imageView;
    int PICK_IMAGE_MULTIPLE = 1;
    ArrayList<Uri> mArrayUri;
    HashMap<String,String> ImageUrl;

    int position = 0;
    FirebaseAuth FAuth;
    FirebaseFirestore db;

    String CourseSelected;
private ArrayList<String> course_selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);

        imageView = findViewById(R.id.image);
        upd_btn = findViewById(R.id.upload_img);
        ft_sel_img = findViewById(R.id.ftbtn_image);
        mArrayUri = new ArrayList<Uri>();
        FAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        ImageUrl=new HashMap<>();
        Intent intent=getIntent();
        course_selected=(ArrayList<String>)intent.getSerializableExtra("Selected Course");
        imageView.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView1 = new ImageView(getApplicationContext());
                return imageView1;
            }
        });

        ft_sel_img.setOnClickListener(new View.OnClickListener() {
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

        upd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               UploadImagefirebase();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
            // Get the Image from data
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    // adding imageuri in array
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    mArrayUri.add(imageurl);
                    Toast.makeText(this, "picked Image", Toast.LENGTH_SHORT).show();

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
            Toast.makeText(AddImage.this,"Inside loop 1",Toast.LENGTH_SHORT).show();
            Uri IndividualImage = mArrayUri.get(i);
            if (IndividualImage != null) {
                StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("ItemImages");
                Toast.makeText(AddImage.this,"Inside firebase storage 2",Toast.LENGTH_SHORT).show();
                final StorageReference ImageName = ImageFolder.child("Image" + i + ": " + IndividualImage.getLastPathSegment());

                ImageName.putFile(IndividualImage);
              //  ImageName.getDownloadUrl();
                ImageUrl.put("Image "+i,String.valueOf(ImageName.getDownloadUrl()));

//                ImageName.putFile(IndividualImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                        Toast.makeText(AddImage.this,"Image stored in database 3",Toast.LENGTH_SHORT).show();
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(AddImage.this,"Image not stored in database 3",Toast.LENGTH_SHORT).show();
//                    }
//                });
//                ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        ImageUrl.add(String.valueOf(uri));
//                        Toast.makeText(AddImage.this,"Image dwd url in list 4",Toast.LENGTH_SHORT).show();
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(AddImage.this,"Image dwd url not list 4",Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        }
        UploadImageFirestore(ImageUrl);
    }

     public void UploadImageFirestore(HashMap<String,String> dwdURls) {
      //   we need list that images urls
         Toast.makeText(AddImage.this,"3 dwdURls Size: "+dwdURls.size(),Toast.LENGTH_SHORT).show();
         if(!dwdURls.isEmpty())
         {
                    CollectionReference images_teacher = db.collection("Courses").document(course_selected.get(0)).collection(course_selected.get(1))
                            .document(course_selected.get(2)).collection("Images");
                    images_teacher.add(dwdURls).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddImage.this, "Images url in firestore 4", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddImage.this, "Images url not in firestore 4", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                    else {
                        Toast.makeText(AddImage.this, "dwdURls is empty 4", Toast.LENGTH_SHORT).show();
                    }
        }
    }

