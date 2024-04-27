package com.example.coursemanagerprj.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coursemanagerprj.database.DbHelper;
import com.example.coursemanagerprj.model.Course;
import com.example.coursemanagerprj.model.LoaiCourse;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private SQLiteDatabase db;

    public CourseDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Course obj){
        ContentValues values = new ContentValues();
        values.put("tenCourse", obj.getTenCourse());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.insert("Course", null,values);
    }

    public long update(Course obj){
        ContentValues values = new ContentValues();
        values.put("tenCourse", obj.getTenCourse());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.update("Course", values, "maCourse=?", new String[]{String.valueOf(obj.getMaCourse())});
    }

    public long delete(String id){
        return db.delete("Course", "maCourse=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<Course> getData(String sql, String...selectionArgs){
        List<Course> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            Course obj = new Course();
            obj.setMaCourse(Integer.parseInt(c.getString(c.getColumnIndex("maCourse"))));
            obj.setTenCourse(c.getString(c.getColumnIndex("tenCourse")));
            obj.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            list.add(obj);
        }
        return list;
    }

    public List<Course> getAll(){
        String sql = "SELECT * FROM Course";
        return getData(sql);
    }

    public Course getID(String id){
        String sql = "SELECT * FROM Course WHERE maCourse=?";
        List<Course> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public List<Course> searchCourse(String key){
        List<Course> list = new ArrayList<>();
        String whereClause = "tenCourse like ?";
        String[] whereArgs = {"%"+key+"%"};
        Cursor c = db.query("Course",null,whereClause,whereArgs,null,null,null);
        while (c!=null && c.moveToNext()){
            Course obj = new Course();
            obj.setMaCourse(Integer.parseInt(c.getString(c.getColumnIndex("maCourse"))));
            obj.setTenCourse(c.getString(c.getColumnIndex("tenCourse")));
            obj.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            list.add(obj);
        }
        return list;
    }

}
