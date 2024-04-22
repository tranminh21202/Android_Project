package com.example.coursemanagerprj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coursemanagerprj.R;
import com.example.coursemanagerprj.model.Course;

import java.util.ArrayList;

public class CourseSpinnerAdapter extends ArrayAdapter<Course> {
    private Context context;
    private ArrayList<Course> list;
    TextView tvMaCourse, tvTenCourse;

    public CourseSpinnerAdapter(@NonNull Context context,  ArrayList<Course> list) {
        super(context,0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.spinner_course_item,null);
        }
        final Course item = list.get(position);
        if(item!=null){
            tvMaCourse = v.findViewById(R.id.tvMaCourseSp);
            tvMaCourse.setText(item.getMaCourse() + ". ");
            tvTenCourse = v.findViewById(R.id.tvTenCourseSp);
            tvTenCourse.setText(item.getTenCourse());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v == null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.spinner_course_item,null);
        }
        final Course item = list.get(position);
        if(item!=null){
            tvMaCourse = v.findViewById(R.id.tvMaCourseSp);
            tvMaCourse.setText(item.getMaCourse() + ". ");
            tvTenCourse = v.findViewById(R.id.tvTenCourseSp);
            tvTenCourse.setText(item.getTenCourse());
        }
        return v;
    }
}
