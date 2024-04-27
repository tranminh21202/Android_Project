package com.example.coursemanagerprj.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.coursemanagerprj.R;
import com.example.coursemanagerprj.adapter.CourseAdapter;
import com.example.coursemanagerprj.adapter.LoaiCourseSpinnerAdapter;
import com.example.coursemanagerprj.dao.CourseDAO;
import com.example.coursemanagerprj.dao.LoaiCourseDAO;
import com.example.coursemanagerprj.model.Course;
import com.example.coursemanagerprj.model.LoaiCourse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class CourseFragment extends Fragment {
    ListView lvCourse;
    CourseDAO courseDAO;
    CourseAdapter adapter;
    Course item;
    List<Course> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaCourse, edTenCourse, edGiaThue;
    Spinner spinner;
    Button btnSave, btnCancel;
    LoaiCourseSpinnerAdapter loaiCourseSpinnerAdapter;
    ArrayList<LoaiCourse> listLoaiCourse;
    LoaiCourseDAO loaiCourseDAO;
    LoaiCourse loaiCourse;
    int maLoaiCourse, position;
    SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_course, container, false);
        lvCourse=v.findViewById(R.id.lvCourse);
        courseDAO=new CourseDAO(getActivity());
        updateCourse();
        fab=v.findViewById(R.id.fab);
        searchView=v.findViewById(R.id.searchCourse);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });
        lvCourse.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                item=list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                autoupdateCourse(newText);
                return true;
            }
        });
        return v;
    }

    void updateCourse(){
        list=(List<Course>) courseDAO.getAll();
        adapter=new CourseAdapter(getActivity(),this,list);
        lvCourse.setAdapter(adapter);
    }

    void autoupdateCourse(String s){
        list=(List<Course>) courseDAO.searchCourse(s);
        adapter=new CourseAdapter(getActivity(),this,list);
        lvCourse.setAdapter(adapter);
    }

    public void deleteCourse(final String id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                courseDAO.delete(id);
                updateCourse();
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

    protected void openDialog(final Context context, final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.fragment_course_dialog);
        edMaCourse=dialog.findViewById(R.id.edMaCourse);
        edTenCourse=dialog.findViewById(R.id.edTenCourse);
        edGiaThue=dialog.findViewById(R.id.edGiaThue);
        spinner=dialog.findViewById(R.id.spLoaiCourse);
        btnSave=dialog.findViewById(R.id.btnSaveCourse);
        btnCancel=dialog.findViewById(R.id.btnCancelCourse);

        listLoaiCourse=new ArrayList<LoaiCourse>();
        loaiCourseDAO=new LoaiCourseDAO(context);
        listLoaiCourse=(ArrayList<LoaiCourse>) loaiCourseDAO.getAll();
        loaiCourseSpinnerAdapter=new LoaiCourseSpinnerAdapter(context,listLoaiCourse);
        spinner.setAdapter(loaiCourseSpinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                maLoaiCourse=listLoaiCourse.get(position).getMaLoai();
                Toast.makeText(context,"Chọn "+listLoaiCourse.get(position).getTenLoai(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edMaCourse.setEnabled(false);
        if(type!=0){
            edMaCourse.setText(String.valueOf(item.getMaCourse()));
            edTenCourse.setText(item.getTenCourse());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            for(int i=0;i<listLoaiCourse.size();i++){
                if(item.getMaLoai()==(listLoaiCourse.get(i).getMaLoai())){
                    position=i;
                }
                Log.i("demo","posCourse "+position);
                spinner.setSelection(position);
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
                item = new Course();
                item.setTenCourse(edTenCourse.getText().toString());
                item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                item.setMaLoai(maLoaiCourse);
                if (validate()>0){
                    if (type==0){
                        if(courseDAO.insert(item)>0){
                            Toast.makeText(context,"Thêm khóa học thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Thêm khóa học thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaCourse(Integer.parseInt(edMaCourse.getText().toString()));
                        if(courseDAO.update(item)>0){
                            Toast.makeText(context,"Sửa khóa học thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Sửa khóa học thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                updateCourse();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public int validate(){
        int check = 1;
        if(edTenCourse.getText().length()==0 || edGiaThue.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thong tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}