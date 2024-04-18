package com.satdroid.teacherreg_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
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
        ImageDataModal imageDataModal=ImageArraylist.get(position);
        Picasso.get().load(imageDataModal.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ImageArraylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image_design);
        }
    }
}
