package com.example.coursemanagerprj.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursemanagerprj.R;
import com.example.coursemanagerprj.adapter.CourseSpinnerAdapter;
import com.example.coursemanagerprj.adapter.PhieuMuonAdapter;
import com.example.coursemanagerprj.adapter.ThanhVienSpinnerAdapter;
import com.example.coursemanagerprj.dao.CourseDAO;
import com.example.coursemanagerprj.dao.PhieuMuonDAO;
import com.example.coursemanagerprj.dao.ThanhVienDAO;
import com.example.coursemanagerprj.model.Course;
import com.example.coursemanagerprj.model.PhieuMuon;
import com.example.coursemanagerprj.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhieuMuonFragment extends Fragment {
    ListView lvPhieuMuon;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spCourse;
    TextView tvNgay, tvTienThue;
    CheckBox chkHoanThanh;
    Button btnSave, btnCancel;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;

    CourseSpinnerAdapter courseSpinnerAdapter;
    ArrayList<Course> listCourse;
    CourseDAO courseDAO;
    Course course;
    int maCourse, tienThue;
    int positionTV, positionCourse;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon=v.findViewById(R.id.lvPhieuMuon);
        fab=v.findViewById(R.id.fabPM);
        dao = new PhieuMuonDAO(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        updatePM();
        return v;
    }

    void updatePM(){
        list=(ArrayList<PhieuMuon>) dao.getAll();
        adapter=new PhieuMuonAdapter(getActivity(),this,list);
        lvPhieuMuon.setAdapter(adapter);
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_phieu_muon_dialog);
        edMaPM=dialog.findViewById(R.id.edMaPM);
        spTV=dialog.findViewById(R.id.spMaTV);
        spCourse=dialog.findViewById(R.id.spMaCourse);
        tvNgay=dialog.findViewById(R.id.tvNgay);
        tvTienThue=dialog.findViewById(R.id.tvTienThue);
        chkHoanThanh=dialog.findViewById(R.id.chkHoanThanh);
        btnCancel=dialog.findViewById(R.id.btnCancelPM);
        btnSave=dialog.findViewById(R.id.btnSavePM);
        edMaPM.setEnabled(false);

        tvNgay.setText("Ngày thuê: "+sdf.format(new Date()));

        thanhVienDAO=new ThanhVienDAO(context);
        listThanhVien=new ArrayList<ThanhVien>();
        listThanhVien=(ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context,listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaTV();
                //Toast.makeText(context,"Chọn "+listThanhVien.get(position).getHoTen(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        courseDAO=new CourseDAO(context);
        listCourse=new ArrayList<Course>();
        listCourse=(ArrayList<Course>) courseDAO.getAll();
        courseSpinnerAdapter=new CourseSpinnerAdapter(context,listCourse);
        spCourse.setAdapter(courseSpinnerAdapter);
        spCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                maCourse = listCourse.get(position).getMaCourse();
                tienThue=listCourse.get(position).getGiaThue();
                tvTienThue.setText("Tiền thuê: "+tienThue);
                //Toast.makeText(context,"Chọn "+listCourse.get(position).getTenCourse(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // update
        if(type!=0){
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for (int i = 0; i < listThanhVien.size(); i++){
                if(item.getMaTV() == (listThanhVien.get(i).getMaTV())){
                    positionTV = i;
                }
            }
            spTV.setSelection(positionTV);

            for (int i = 0; i < listCourse.size(); i++){
                if(item.getMaCourse() == (listCourse.get(i).getMaCourse())){
                    positionCourse = i;
                }
            }
            spCourse.setSelection(positionCourse);

            tvNgay.setText("Ngày thuê: " + sdf.format(item.getNgay()));
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
            if(item.getTraCourse() == 1){
                chkHoanThanh.setChecked(true);
            }else {
                chkHoanThanh.setChecked(false);
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new PhieuMuon();
                item.setMaCourse(maCourse);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if(chkHoanThanh.isChecked()){
                    item.setTraCourse(1);
                }else {
                    item.setTraCourse(0);
                }
                if(type == 0){
                    //insert
                    if(dao.insert(item)>0){
                        Toast.makeText(context,"Thêm thành công hóa đơn",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Thêm thất bại hóa đơn", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //update
                    item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                    if(dao.update(item)>0){
                        Toast.makeText(context,"Sửa thành công hóa đơn",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Sửa thất bại hóa đơn", Toast.LENGTH_SHORT).show();
                    }
                }
                updatePM();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void deletePM(final String id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(id);
                updatePM();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        builder.show();
    }
}