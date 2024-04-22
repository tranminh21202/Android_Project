package com.example.coursemanagerprj.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coursemanagerprj.R;
import com.example.coursemanagerprj.dao.CourseDAO;
import com.example.coursemanagerprj.dao.ThanhVienDAO;
import com.example.coursemanagerprj.fragment.PhieuMuonFragment;
import com.example.coursemanagerprj.model.Course;
import com.example.coursemanagerprj.model.PhieuMuon;
import com.example.coursemanagerprj.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> list;
    TextView tvMaPM, tvTenTV, tvTenCourse, tvTienThue, tvNgay, tvTraCourse;
    ImageView imgDel;
    CourseDAO courseDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> list) {
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.phieumuon_item,null);
        }
        final PhieuMuon item = list.get(position);
        if(item!=null){
            tvMaPM=v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã hóa đơn: " + item.getMaPM());
            courseDAO = new CourseDAO(context);
            Course course = courseDAO.getID(String.valueOf(item.getMaCourse()));
            tvTenCourse = v.findViewById(R.id.tvTenCoursePM);
            tvTenCourse.setText("Khóa học: "+ course.getTenCourse());
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.getMaTV()));
            tvTenTV=v.findViewById(R.id.tvTenTVPM);
            tvTenTV.setText("Thành viên: "+thanhVien.getHoTen());
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
            tvNgay=v.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày mua: "+sdf.format(item.getNgay()));
            tvTraCourse=v.findViewById(R.id.tvTraCourse);
            if(item.getTraCourse()==1){
                tvTraCourse.setTextColor(Color.GREEN);
                tvTraCourse.setText("Đã hoàn thành khóa học");
            }else {
                tvTraCourse.setTextColor(Color.RED);
                tvTraCourse.setText("Chưa hoàn thành khóa học");
            }
            imgDel=v.findViewById(R.id.imgDeletePM);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment.deletePM(String.valueOf(item.getMaPM()));
                }
            });
        }
        return v;
    }
}
