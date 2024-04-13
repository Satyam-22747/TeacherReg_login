package com.satdroid.teacherreg_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

private Context context;
    ArrayList<ImageDataModal> ImageModelArrayList;

    public ImageAdapter(Context context, ArrayList<ImageDataModal> ImageModelArrayList) {
        this.context=context;
        this.ImageModelArrayList=ImageModelArrayList;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_rcv_layout, parent, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {

        ImageDataModal imageDataModal=ImageModelArrayList.get(position);
        holder.imageText.setText(imageDataModal.getImageName());
        Picasso.get().load(imageDataModal.getImageUri()).into(holder.imageV);
    }

    @Override
    public int getItemCount() {
        return ImageModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageV;
        TextView imageText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageV=itemView.findViewById(R.id.image_design);
            imageText=itemView.findViewById(R.id.ImageName_design);
        }
    }
}
