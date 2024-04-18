package com.example.coursemanagerprj.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coursemanagerprj.database.DbHelper;
import com.example.coursemanagerprj.model.QuanLy;

import java.util.ArrayList;
import java.util.List;

public class QuanLyDAO {
    private SQLiteDatabase db;

    public QuanLyDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(QuanLy obj){
        ContentValues values = new ContentValues();
        values.put("maQL", obj.getMaQL());
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.insert("QuanLy", null,values);
    }

    public long updatePass(QuanLy obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("QuanLy", values, "maQL=?", new String[]{String.valueOf(obj.getMaQL())});
    }

    public long delete(String id){
        return db.delete("QuanLy", "maQL=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<QuanLy> getData(String sql, String...selectionArgs){
        List<QuanLy> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            QuanLy obj = new QuanLy();
            obj.setMaQL(c.getString(c.getColumnIndex("maQL")));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setMatKhau(c.getString(c.getColumnIndex("matKhau")));
            list.add(obj);
        }
        return list;
    }

    public List<QuanLy> getAll(){
        String sql = "SELECT * FROM QuanLy";
        return getData(sql);
    }

    public QuanLy getID(String id){
        String sql = "SELECT * FROM QuanLy WHERE maQL=?";
        List<QuanLy> list = getData(sql,id);
        return list.get(0);
    }

    public int checkLogin(String id, String password){
        String sql = "SELECT * FROM QuanLy WHERE maQL=? AND matKhau=?";
        List<QuanLy> list = getData(sql, id, password);
        if(list.size() == 0){
            return -1;
        }
        return 1;
    }
}
