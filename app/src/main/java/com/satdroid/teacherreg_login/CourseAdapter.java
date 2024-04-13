package com.satdroid.teacherreg_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CourseAdapter extends ArrayAdapter<CourseModal> {

    public CourseAdapter(@NonNull Context context, ArrayList<CourseModal> courseModalArrayList)
    {
        super(context, 0, courseModalArrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View courseListView=convertView;
        if(courseListView==null)
        {
            courseListView= LayoutInflater.from(getContext()).inflate(R.layout.dashboard_design,parent, false);
        }

        CourseModal courseModal=getItem(position);
        TextView courseNameTv=courseListView.findViewById(R.id.courseName_tv);

        courseNameTv.setText(courseModal.getCourse());
        return courseListView;
    }
}
