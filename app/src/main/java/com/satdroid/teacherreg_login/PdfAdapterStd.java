package com.satdroid.teacherreg_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class PdfAdapterStd extends RecyclerView.Adapter<PdfAdapterStd.MyPdfViewHolder> {

    Context context;
    ArrayList<ImageDataModal> PdfArraylist;

    public PdfAdapterStd(Context context, ArrayList<ImageDataModal> pdfArraylist) {
        this.context = context;
        PdfArraylist = pdfArraylist;
    }

    @NonNull
    @Override
    public PdfAdapterStd.MyPdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rcv_layout_pdf, parent, false);
        return new PdfAdapterStd.MyPdfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PdfAdapterStd.MyPdfViewHolder holder, int position) {
        ImageDataModal imageDataModal=PdfArraylist.get(position);
        //holder.imageView.setImageResource(R.drawable.addpdf);
        holder.textView.setText(imageDataModal.SubjectName);

    }

    @Override
    public int getItemCount() {
        return PdfArraylist.size();
    }


    public static class MyPdfViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyPdfViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_design);
            textView=itemView.findViewById(R.id.tv_pdf);
        }
    }
}