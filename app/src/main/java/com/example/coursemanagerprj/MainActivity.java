package com.example.coursemanagerprj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.coursemanagerprj.dao.DemoDB;
import com.example.coursemanagerprj.fragment.ChangePasswordFragment;
import com.example.coursemanagerprj.fragment.CourseFragment;
import com.example.coursemanagerprj.fragment.LoaiCourseFragment;
import com.example.coursemanagerprj.fragment.ThanhVienFragment;
import com.example.coursemanagerprj.fragment.TopFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    View headerView;
    TextView txtUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Intent i = getIntent();
        txtUser.setText("Welcome !");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager=getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_PhieuMuon:
                        setTitle("Quản lý Hóa Đơn");
                        break;
                    case R.id.nav_LoaiCourse:
                        setTitle("Quản lý Loại Khóa Học");
                        LoaiCourseFragment loaiCourseFragment=new LoaiCourseFragment();
                        manager.beginTransaction().replace(R.id.content_frame,loaiCourseFragment).commit();
                        break;
                    case R.id.nav_Course:
                        setTitle("Quản lý Khóa Học");
                        CourseFragment courseFragment=new CourseFragment();
                        manager.beginTransaction().replace(R.id.content_frame,courseFragment).commit();
                        break;
                    case R.id.nav_ThanhVien:
                        setTitle("Quản lý Thành Viên");
                        ThanhVienFragment thanhVienFragment=new ThanhVienFragment();
                        manager.beginTransaction().replace(R.id.content_frame,thanhVienFragment).commit();
                        break;
                    case R.id.sub_Top:
                        setTitle("Khóa học đăng ký nhiều nhất");
                        TopFragment topFragment=new TopFragment();
                        manager.beginTransaction().replace(R.id.content_frame,topFragment).commit();
                        break;
                    case R.id.sub_DoanhThu:
                        setTitle("Doanh Thu");
                        break;
                    case R.id.sub_AddUser:
                        setTitle("Thêm người dùng");
                        break;
                    case R.id.sub_Pass:
                        setTitle("Thay đổi mật khẩu");
                        ChangePasswordFragment changePasswordFragment=new ChangePasswordFragment();
                        manager.beginTransaction().replace(R.id.content_frame,changePasswordFragment).commit();
                        break;
                    case R.id.sub_Logout:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        finish();
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home)
            drawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.nav_view);
        headerView=navigationView.getHeaderView(0);
        txtUser=headerView.findViewById(R.id.txtUser);
    }
}