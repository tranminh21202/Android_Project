package com.example.coursemanagerprj.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

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
import com.example.coursemanagerprj.adapter.ThanhVienAdapter;
import com.example.coursemanagerprj.dao.ThanhVienDAO;
import com.example.coursemanagerprj.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ThanhVienFragment extends Fragment {
    ListView listView;
    ArrayList<ThanhVien> list;
    static ThanhVienDAO dao;
    private ThanhVienAdapter adapter;
    ThanhVien item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV,edTenTV,edNamSinh;
    Button btnSaveTV,btnCancelTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        listView=v.findViewById(R.id.lvThanhVien);
        fab=v.findViewById(R.id.fab);
        dao=new ThanhVienDAO(getActivity());
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
        return v;
    }

    void updateLV(){
        list=(ArrayList<ThanhVien>) dao.getAll();
        adapter=new ThanhVienAdapter(getActivity(),this,list);
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
        dialog.setContentView(R.layout.fragment_thanh_vien_dialog);
        edMaTV=dialog.findViewById(R.id.edMaTV);
        edTenTV=dialog.findViewById(R.id.edTenTV);
        edNamSinh=dialog.findViewById(R.id.edNamSinh);
        btnSaveTV=dialog.findViewById(R.id.btnSaveTV);
        btnCancelTV=dialog.findViewById(R.id.btnCancelTV);

        edMaTV.setEnabled(false);
        if (type!=0){
            edMaTV.setText(String.valueOf(item.getMaTV()));
            edTenTV.setText(item.getHoTen());
            edNamSinh.setText(item.getNamSinh());
        }
        btnCancelTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSaveTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item=new ThanhVien();
                item.setHoTen(edTenTV.getText().toString());
                item.setNamSinh(edNamSinh.getText().toString());
                if(validate() > 0){
                    if(type==0){
                        if(dao.insert(item) > 0){
                            Toast.makeText(context,"Thêm thành công thành viên",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context,"Thêm thành viên thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                        if(dao.update(item) > 0){
                            Toast.makeText(context,"Cập nhập thành công thành viên",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Cập nhập thành viên thất bại",Toast.LENGTH_SHORT).show();
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
        if(edTenTV.getText().length()==0 || edNamSinh.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}