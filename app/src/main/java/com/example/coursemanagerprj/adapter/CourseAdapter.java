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
import com.example.coursemanagerprj.dao.LoaiCourseDAO;
import com.example.coursemanagerprj.fragment.CourseFragment;
import com.example.coursemanagerprj.model.Course;
import com.example.coursemanagerprj.model.LoaiCourse;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {
    Context context;
    CourseFragment fragment;
    List<Course> list;
    TextView tvMaCourse, tvTenCourse, tvGiaThue, tvLoai;
    ImageView imgDelete;

    public CourseAdapter(@NonNull Context context, CourseFragment fragment, List<Course> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.course_item,null);
        }
        final Course item = list.get(position);
        if(item != null){
            LoaiCourseDAO loaiCourseDAO = new LoaiCourseDAO(context);
            LoaiCourse loaiCourse = loaiCourseDAO.getID(String.valueOf(item.getMaLoai()));
            tvMaCourse=v.findViewById(R.id.tvMaCourse);
            tvMaCourse.setText("Mã khóa học: "+item.getMaCourse());
            tvTenCourse=v.findViewById(R.id.tvTenCourse);
            tvTenCourse.setText("Tên khóa: "+item.getTenCourse());
            tvGiaThue=v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá thuê: "+item.getGiaThue() +"VNĐ");
            tvLoai=v.findViewById(R.id.tvLoaiCourse);
            tvLoai.setText("Phân loại: "+loaiCourse.getTenLoai());

            imgDelete=v.findViewById(R.id.imgDeleteCourse);
        }
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.deleteCourse(String.valueOf(item.getMaCourse()));
            }
        });
        return v;
    }
}
