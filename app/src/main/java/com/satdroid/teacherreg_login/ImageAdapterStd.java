package com.satdroid.teacherreg_login;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ImageAdapterStd extends RecyclerView.Adapter<ImageAdapterStd.MyViewHolder> {

    Context context;
    ArrayList<ImageDataModal> ImageArraylist;

    public ImageAdapterStd(Context context, ArrayList<ImageDataModal> imageArraylist) {
        this.context = context;
        ImageArraylist = imageArraylist;
    }

    @NonNull
    @Override
    public ImageAdapterStd.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.image_rcv_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapterStd.MyViewHolder holder, int position) {
        ImageDataModal imageDataModal = ImageArraylist.get(position);
        Picasso.get().load(imageDataModal.getImageUrl()).into(holder.imageView);
        holder.textView.setText(imageDataModal.SubjectName);


        final LinearLayout   linearLayout=new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        final  ImageView imageViewDisp=new ImageView(context);
        Picasso.get().load(imageDataModal.getImageUrl()).into(imageViewDisp);
        linearLayout.addView(imageViewDisp);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Ensure the linearLayout has no parent before adding it to the AlertDialog
                if (linearLayout.getParent() != null) {
                    ((ViewGroup) linearLayout.getParent()).removeView(linearLayout);
                }

                AlertDialog.Builder  builder=new AlertDialog.Builder(context);
                builder.setView(linearLayout);
                AlertDialog alertDialog1=builder.create();
                alertDialog1.show();

            }
        });
        holder.image_dwd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(imageDataModal.getImageUrl()));
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return ImageArraylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        AppCompatButton image_dwd_btn;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_design);
            image_dwd_btn=itemView.findViewById(R.id.download_btn_std);
            textView=itemView.findViewById(R.id.tv_pdf);

        }
    }
}
