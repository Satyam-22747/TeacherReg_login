package com.satdroid.teacherreg_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

        if(context instanceof Notice_student) {
            holder.notice_teacer_tv.setVisibility(View.VISIBLE);

            holder.noticeTv.setText(noticeDataModel.getNotice());
            holder.notice_date_Tv.setText((noticeDataModel.getNoticeDate()));
            holder.notice_teacer_tv.setText(noticeDataModel.getTeacherName());
            holder.notice_time.setText(noticeDataModel.getUploadTime());
        }
        if(context instanceof UploadNotice)
        {
            holder.notice_course.setVisibility(View.VISIBLE);
            holder.notice_sem.setVisibility(View.VISIBLE);

            holder.noticeTv.setText(noticeDataModel.getNotice());
            holder.notice_date_Tv.setText((noticeDataModel.getNoticeDate()));
            holder.notice_course.setText(noticeDataModel.getCourseSelected());
            holder.notice_sem.setText(noticeDataModel.getSemesterName());
            holder.notice_time.setText(noticeDataModel.getUploadTime());
        }
    }

    @Override
    public int getItemCount() {
        return NoticeModallist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView noticeTv,notice_date_Tv,notice_teacer_tv,notice_course,notice_sem,notice_time;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            noticeTv=itemView.findViewById(R.id.notice_tv);
            notice_date_Tv=itemView.findViewById(R.id.date_notice_rcv);
            notice_teacer_tv=itemView.findViewById(R.id.notice_teacher_rcv);
            notice_course=itemView.findViewById(R.id.Course_name_notice_rcv);
            notice_sem=itemView.findViewById(R.id.Semester_noticeRcv);
            notice_time=itemView.findViewById(R.id.time_noticeRcv);
        }
    }
}





