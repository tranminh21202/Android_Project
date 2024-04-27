package com.example.coursemanagerprj.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coursemanagerprj.R;
import com.example.coursemanagerprj.adapter.LoaiCourseAdapter;
import com.example.coursemanagerprj.dao.LoaiCourseDAO;
import com.example.coursemanagerprj.model.LoaiCourse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiCourseFragment extends Fragment {
    ListView listView;
    ArrayList<LoaiCourse> list;
    static LoaiCourseDAO dao;
    private LoaiCourseAdapter adapter;
    LoaiCourse item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoaiCourse,edTenLoaiCourse;
    Button btnSaveLoaiCourse,btnCancelLoaiCourse;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_course, container, false);
        listView=v.findViewById(R.id.lvLoaiCourse);
        fab=v.findViewById(R.id.fabLoaiCourse);
        searchView=v.findViewById(R.id.searchLoaiCourse);
        dao=new LoaiCourseDAO(getActivity());
        updateLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                item = list.get(position);
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
                autoupdateLV(newText);
                return true;
            }
        });
        return v;
    }

    void updateLV(){
        list=(ArrayList<LoaiCourse>) dao.getAll();
        adapter=new LoaiCourseAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }

    void autoupdateLV(String s){
        list=(ArrayList<LoaiCourse>) dao.searchLoaiCourse(s);
        adapter=new LoaiCourseAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
    }

    public void delete(final String id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(id);
                updateLV();
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

    public void openDialog(final Context context, final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.fragment_loai_course_dialog);
        edMaLoaiCourse=dialog.findViewById(R.id.edMaLoaiCourse);
        edTenLoaiCourse=dialog.findViewById(R.id.edTenLoaiCourse);
        btnSaveLoaiCourse=dialog.findViewById(R.id.btnSaveLoaiCourse);
        btnCancelLoaiCourse=dialog.findViewById(R.id.btnCancelLoaiCourse);

        edMaLoaiCourse.setEnabled(false);
        if (type!=0){
            edMaLoaiCourse.setText(String.valueOf(item.getMaLoai()));
            edTenLoaiCourse.setText(item.getTenLoai());
        }
        btnCancelLoaiCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSaveLoaiCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item=new LoaiCourse();
                item.setTenLoai(edTenLoaiCourse.getText().toString());
                if(validate() > 0){
                    if(type==0){
                        if(dao.insert(item) > 0){
                            Toast.makeText(context,"Thêm thành công loại khóa học",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context,"Thêm loại khóa học thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaLoai(Integer.parseInt(edMaLoaiCourse.getText().toString()));
                        if(dao.update(item) > 0){
                            Toast.makeText(context,"Cập nhập thành công loại khóa học",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Cập nhập thành viên loại khóa học",Toast.LENGTH_SHORT).show();
                        }
                    }
                    updateLV();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public int validate(){
        int check=1;
        if(edTenLoaiCourse.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}