package com.example.coursemanagerprj.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coursemanagerprj.database.DbHelper;
import com.example.coursemanagerprj.model.LoaiCourse;
import com.example.coursemanagerprj.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class LoaiCourseDAO {
    private SQLiteDatabase db;

    public LoaiCourseDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiCourse obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.insert("LoaiCourse", null,values);
    }

    public long update(LoaiCourse obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.update("LoaiCourse", values, "maLoai=?", new String[]{String.valueOf(obj.getMaLoai())});
    }

    public long delete(String id){
        return db.delete("LoaiCourse", "maLoai=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<LoaiCourse> getData(String sql, String...selectionArgs){
        List<LoaiCourse> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            LoaiCourse obj = new LoaiCourse();
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }

    public List<LoaiCourse> getAll(){
        String sql = "SELECT * FROM LoaiCourse";
        return getData(sql);
    }

    public LoaiCourse getID(String id){
        String sql = "SELECT * FROM LoaiCourse WHERE maLoai=?";
        List<LoaiCourse> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public List<LoaiCourse> searchLoaiCourse(String key){
        List<LoaiCourse> list = new ArrayList<>();
        String whereClause = "tenLoai like ?";
        String[] whereArgs = {"%"+key+"%"};
        Cursor c = db.query("LoaiCourse",null,whereClause,whereArgs,null,null,null);
        while (c!=null && c.moveToNext()){
            LoaiCourse obj = new LoaiCourse();
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }

}
