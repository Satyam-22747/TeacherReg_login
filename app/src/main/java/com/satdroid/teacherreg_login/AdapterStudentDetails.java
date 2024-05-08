package com.satdroid.teacherreg_login;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class AdapterStudentDetails extends RecyclerView.Adapter<AdapterStudentDetails.MyViewHolder> {

    Context context;
    ArrayList<StudentDetailDataModel> StudeDetailslist;
    ArrayList<studentAttendenceModal> StudentAttendenceModalArrayList;
    AttendenceListener attendenceListener;
    ArrayList<studentAttendenceModal> stdattmodal;
    public AdapterStudentDetails(Context context, ArrayList<studentAttendenceModal> studentAttendenceModalArrayList) {
        this.context = context;
        StudentAttendenceModalArrayList= studentAttendenceModalArrayList;
    }

    public AdapterStudentDetails(Context context, ArrayList<StudentDetailDataModel> studeDetailslist, AttendenceListener attendenceListener) {
        this.context = context;
        StudeDetailslist = studeDetailslist;
        this.attendenceListener = attendenceListener;
    }

    @NonNull
    @Override
    public AdapterStudentDetails.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.student_details_recycler_design,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterStudentDetails.MyViewHolder holder, int position) {
//        studentAttendenceModal studentAttendenceModal =StudentAttendenceModalArrayList.get(position);
//        StudentDetailDataModel studentDetailDataModel=StudeDetailslist.get(position);

        if ( context  instanceof StudentDetails ) {
            studentAttendenceModal studentAttendenceModal =StudentAttendenceModalArrayList.get(position);

            holder.AT_status.setVisibility(View.VISIBLE);

            holder.AT_status.setText(studentAttendenceModal.getAttendence());
            holder.RollSt.setText(studentAttendenceModal.getRollNO());
            holder.nameSt.setText(studentAttendenceModal.getName());

            holder.A_tv.setVisibility(View.GONE);
            holder.radioGroup.setVisibility(View.GONE);
            holder.P_tv.setVisibility(View.GONE);
        }
       else
        {
            StudentDetailDataModel studentDetailDataModel=StudeDetailslist.get(position);
            holder.AT_status.setVisibility(View.GONE);

            holder.RollSt.setText(studentDetailDataModel.getRollNo());
            holder.nameSt.setText(studentDetailDataModel.getName());

            holder.P_tv.setVisibility(View.VISIBLE);
            holder.A_tv.setVisibility(View.VISIBLE);
            holder.radioGroup.setVisibility(View.VISIBLE);

            holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    stdattmodal=new ArrayList<>(StudeDetailslist.size());
                    if(checkedId!=-1)
                    {
                        if(checkedId==R.id.p_rdb)
                        {
                            stdattmodal.add(new studentAttendenceModal(holder.RollSt.getText().toString(),holder.nameSt.getText().toString(),"P"));
                        }
                        if(checkedId==R.id.a_rdb)
                        {
                            stdattmodal.add(new studentAttendenceModal(holder.RollSt.getText().toString(),holder.nameSt.getText().toString(),"A"));
                        }
                        attendenceListener.onAttendenceSelected(stdattmodal);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {

        if ( context  instanceof StudentDetails )
            return StudentAttendenceModalArrayList.size();
        return StudeDetailslist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView nameSt,RollSt,AT_status,P_tv,A_tv;
       public RadioGroup radioGroup;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameSt=itemView.findViewById(R.id.NameSt);
            RollSt=itemView.findViewById(R.id.Roll_no);
            AT_status=itemView.findViewById(R.id.st_att_tv);
            radioGroup=itemView.findViewById(R.id.Rd_pa);
            P_tv=itemView.findViewById(R.id.p_tv);
            A_tv=itemView.findViewById(R.id.a_tv);
        }
    }
}
