package com.example.coursemanagerprj.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coursemanagerprj.R;
import com.example.coursemanagerprj.dao.QuanLyDAO;
import com.example.coursemanagerprj.model.QuanLy;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePasswordFragment extends Fragment {
    EditText txtOldPass, txtNewPass, txtRePass;
    Button btnSave, btnCancel;
    QuanLyDAO quanLyDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_change_password, container, false);
        quanLyDAO = new QuanLyDAO(getActivity());
        txtOldPass=v.findViewById(R.id.txtOldPass);
        txtNewPass=v.findViewById(R.id.txtNewPass);
        txtRePass=v.findViewById(R.id.txtRePass);
        btnSave=v.findViewById(R.id.btnSavePass);
        btnCancel=v.findViewById(R.id.btnCancelPass);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtOldPass.setText("");
                txtNewPass.setText("");
                txtRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences=getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
                String user=preferences.getString("USERNAME","");
                if(validate()>0){
                    QuanLy quanLy=quanLyDAO.getID(user);
                    quanLy.setMatKhau(txtNewPass.getText().toString());
                    quanLyDAO.updatePass(quanLy);
                    if(quanLyDAO.updatePass(quanLy) > 0){
                        Toast.makeText(getContext(),"Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        txtOldPass.setText("");
                        txtNewPass.setText("");
                        txtRePass.setText("");
                    }else {
                        Toast.makeText(getContext(),"Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;
    }

    public int validate(){
        int check=1;
        if(txtOldPass.getText().length()==0 || txtNewPass.getText().length()==0 || txtRePass.getText().length()==0){
            Toast.makeText(getContext(),"Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            SharedPreferences preferences= getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String oldpass=preferences.getString("PASSWORD","");
            String pass=txtNewPass.getText().toString();
            String repass=txtRePass.getText().toString();
            if(!oldpass.equals(txtOldPass.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check=-1;
            }
            if(!pass.equals(repass)){
                Toast.makeText(getContext(),"Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }

}