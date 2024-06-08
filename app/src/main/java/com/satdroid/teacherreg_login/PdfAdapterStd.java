package com.satdroid.teacherreg_login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class PdfAdapterStd extends RecyclerView.Adapter<PdfAdapterStd.MyPdfViewHolder> {

    Context context;
    ArrayList<PdfDataModal> PdfArraylist;

    public PdfAdapterStd(Context context, ArrayList<PdfDataModal> pdfArraylist) {
        this.context = context;
        PdfArraylist = pdfArraylist;
    }

    @NonNull
    @Override
    public PdfAdapterStd.MyPdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_rcv_layout, parent, false);
        return new PdfAdapterStd.MyPdfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PdfAdapterStd.MyPdfViewHolder holder, int position) {
        PdfDataModal pdfDataModal=PdfArraylist.get(position);
        //holder.imageView.setImageResource(R.drawable.addpdf);
        holder.textView.setText(pdfDataModal.SubjectName);
        holder.dwdPDfbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(pdfDataModal.getPdfUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return PdfArraylist.size();
    }


    public static class MyPdfViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        AppCompatButton dwdPDfbutton;

        public MyPdfViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_design);
            textView=itemView.findViewById(R.id.tv_pdf);
            dwdPDfbutton=itemView.findViewById(R.id.download_btn_std);
        }

//        public void Download_PDF_View_Intent(String url,View view) {
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setData(Uri.parse(url));
//            startActivity(intent);
//        }
    }
}