package com.satdroid.teacherreg_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {

    Context context;
    ArrayList<NoticeDataModel> NoticeModallist;

    public NoticeAdapter(Context context, ArrayList<NoticeDataModel> noticeModallist) {
        this.context = context;
        NoticeModallist = noticeModallist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.notice_rcv_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NoticeDataModel noticeDataModel=NoticeModallist.get(position);
        holder.noticeTv.setText(noticeDataModel.getNotice());
        holder.notice_date_Tv.setText((noticeDataModel.getNoticeDate()));
    }

    @Override
    public int getItemCount() {
        return NoticeModallist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView noticeTv,notice_date_Tv;
        // LinearLayout  linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            noticeTv=itemView.findViewById(R.id.notice_tv);
            notice_date_Tv=itemView.findViewById(R.id.date_notice_rcv);
        }
    }
}




