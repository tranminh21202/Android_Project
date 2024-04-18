package com.example.coursemanagerprj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coursemanagerprj.dao.QuanLyDAO;

public class LoginActivity extends AppCompatActivity {
    EditText txtUser, txtPass;
    Button btnLogin, btnCancel;
    CheckBox checkBox;
    String username, password;
    QuanLyDAO quanLyDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng nhập");
        initView();
        SharedPreferences preferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user=preferences.getString("USERNAME","");
        String pass=preferences.getString("PASSWORD","");
        Boolean rem=preferences.getBoolean("REMEMBER",false);

        txtUser.setText(user);
        txtPass.setText(pass);
        checkBox.setChecked(rem);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtUser.setText("");
                txtPass.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    public void remUser(String u, String p, Boolean status){
        SharedPreferences preferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if(!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }

    public void checkLogin(){
        username=txtUser.getText().toString();
        password=txtPass.getText().toString();
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Username và Password không được bỏ trống",Toast.LENGTH_SHORT).show();
        }else {
            if(quanLyDAO.checkLogin(username,password)>0){
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                remUser(username,password,checkBox.isChecked());
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Username và Password không tồn tại!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initView() {
        txtUser=findViewById(R.id.txtUsername);
        txtPass=findViewById(R.id.txtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnCancel=findViewById(R.id.btnHuy);
        checkBox=findViewById(R.id.rememberPass);
        quanLyDAO=new QuanLyDAO(this);
    }
}