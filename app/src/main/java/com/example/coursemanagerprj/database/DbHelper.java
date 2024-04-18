package com.example.coursemanagerprj.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "COURSEMANAGE";
    private static final int DB_VERSION = 1;
    static final String CREATE_TABLE_QUAN_LY =
        "create table QuanLy (maQL TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL)";

    static final String CREATE_TABLE_THANH_VIEN =
            "create table ThanhVien (maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "hoTen TEXT NOT NULL, " +
                    "namSinh TEXT NOT NULL)";

    static final String CREATE_TABLE_LOAI_COURSE =
            "create table LoaiCourse (maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenLoai TEXT NOT NULL)";
    static final String CREATE_TABLE_COURSE =
            "create table Course (maCourse INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tenCourse TEXT NOT NULL, " +
                    "giaThue INTEGER NOT NULL, " +
                    "maLoai INTEGER REFERENCES LoaiCourse(maLoai))";
    static final String CREATE_TABLE_PHIEU_MUON =
            "create table PhieuMuon (maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "maQL TEXT REFERENCES QuanLy(maQL), " +
                    "maTV INTEGER REFERENCES ThanhVien(maTV), " +
                    "maCourse INTEGER REFERENCES Course(maCourse), " +
                    "tienThue INTEGER NOT NULL, " +
                    "ngay DATE NOT NULL, "+
                    "traCourse INTEGER NOT NULL)";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUAN_LY);
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        db.execSQL(CREATE_TABLE_LOAI_COURSE);
        db.execSQL(CREATE_TABLE_COURSE);
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
        db.execSQL(DataSQLite.INSERT_QUAN_LY);
        db.execSQL(DataSQLite.INSERT_THANH_VIEN);
        db.execSQL(DataSQLite.INSERT_LOAI_COURSE);
        db.execSQL(DataSQLite.INSERT_COURSE);
        db.execSQL(DataSQLite.INSERT_PHIEU_MUON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableQuanLy = "drop table if exists QuanLy";
        db.execSQL(dropTableQuanLy);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiCourse = "drop table if exists LoaiCourse";
        db.execSQL(dropTableLoaiCourse);
        String dropTableCourse = "drop table if exists Course";
        db.execSQL(dropTableCourse);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
        onCreate(db);
    }
}
