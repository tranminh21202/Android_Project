package com.example.coursemanagerprj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coursemanagerprj.R;
import com.example.coursemanagerprj.fragment.LoaiCourseFragment;
import com.example.coursemanagerprj.model.LoaiCourse;
import com.example.coursemanagerprj.model.LoaiCourse;

import java.util.ArrayList;

public class LoaiCourseAdapter extends ArrayAdapter<LoaiCourse> {
    private Context context;
    LoaiCourseFragment loaiCourseFragment;
    private ArrayList<LoaiCourse> list;
    TextView tvMaLoaiCourse, tvTenLoaiCourse;
    ImageView imageView;


    public LoaiCourseAdapter(@NonNull Context context, LoaiCourseFragment loaiCourseFragment, ArrayList<LoaiCourse> list) {
        super(context, 0, list);
        this.context=context;
        this.loaiCourseFragment=loaiCourseFragment;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaicourse_item,null);
        }
        final LoaiCourse item = list.get(position);
        if(item!=null){
            tvMaLoaiCourse=v.findViewById(R.id.tvMaLoaiCourse);
            tvMaLoaiCourse.setText("Mã khóa học: "+item.getMaLoai());
            tvTenLoaiCourse=v.findViewById(R.id.tvTenLoaiCourse);
            tvTenLoaiCourse.setText("Khóa học: "+item.getTenLoai());
            imageView=v.findViewById(R.id.imgDelete);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaiCourseFragment.delete(String.valueOf(item.getMaLoai()));
            }
        });

        return v;
    }
}
