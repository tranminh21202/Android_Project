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
import com.example.coursemanagerprj.model.LoaiCourse;

import java.util.ArrayList;

public class LoaiCourseSpinnerAdapter extends ArrayAdapter<LoaiCourse> {
    Context context;
    ArrayList<LoaiCourse> list;
    TextView tvMaLoaiCourse, tvTenLoaiCourse;

    public LoaiCourseSpinnerAdapter(@NonNull Context context, ArrayList<LoaiCourse> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.spinner_loaicourse_item,null);
        }
        final LoaiCourse item=list.get(position);
        if(item!=null){
            tvMaLoaiCourse=v.findViewById(R.id.tvMaLoaiCourseSp);
            tvMaLoaiCourse.setText(item.getMaLoai() + ". ");
            tvTenLoaiCourse=v.findViewById(R.id.tvTenLoaiCourseSp);
            tvTenLoaiCourse.setText(item.getTenLoai());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.spinner_loaicourse_item,null);
        }
        final LoaiCourse item=list.get(position);
        if(item!=null){
            tvMaLoaiCourse=v.findViewById(R.id.tvMaLoaiCourseSp);
            tvMaLoaiCourse.setText(item.getMaLoai() + ". ");
            tvTenLoaiCourse=v.findViewById(R.id.tvTenLoaiCourseSp);
            tvTenLoaiCourse.setText(item.getTenLoai());
        }
        return v;
    }
}
