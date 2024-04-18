package com.example.coursemanagerprj.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coursemanagerprj.database.DbHelper;
import com.example.coursemanagerprj.model.Course;
import com.example.coursemanagerprj.model.PhieuMuon;
import com.example.coursemanagerprj.model.Top;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maQL", obj.getMaQL());
        values.put("maTV", obj.getMaTV());
        values.put("maCourse", obj.getMaCourse());
        values.put("ngay", simpleDateFormat.format(obj.getNgay()));
        values.put("tienThue", obj.getTienThue());
        values.put("traCourse", obj.getTraCourse());
        return db.insert("PhieuMuon", null,values);
    }

    public long update(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maQL", obj.getMaQL());
        values.put("maTV", obj.getMaTV());
        values.put("maCourse", obj.getMaCourse());
        values.put("ngay", simpleDateFormat.format(obj.getNgay()));
        values.put("tienThue", obj.getTienThue());
        values.put("traCourse", obj.getTraCourse());
        return db.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(obj.getMaPM())});
    }

    public long delete(String id){
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            PhieuMuon obj = new PhieuMuon();
            obj.setMaPM(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            obj.setMaQL(c.getString(c.getColumnIndex("maQL")));
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setMaCourse(Integer.parseInt(c.getString(c.getColumnIndex("maCourse"))));
            obj.setTienThue(Integer.parseInt(c.getString(c.getColumnIndex("tienThue"))));
            try{
                obj.setNgay(simpleDateFormat.parse(c.getString(c.getColumnIndex("ngay"))));
            } catch (ParseException e){
                e.printStackTrace();
            }
            obj.setTraCourse(Integer.parseInt(c.getString(c.getColumnIndex("traCourse"))));
            list.add(obj);
        }
        return list;
    }

    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    public List<Top> getTop(){
        String sql = "SELECT maCourse, count(maCourse) as soLuong FROM PhieuMuon GROUP BY maCourse ORDER BY soLuong DESC LIMIT 10";
        List<Top> list = new ArrayList<>();
        CourseDAO courseDAO = new CourseDAO(context);
        Cursor c = db.rawQuery(sql,null);
        while (c.moveToNext()){
            Top top = new Top();
            @SuppressLint("Range") Course course = courseDAO.getID(c.getString(c.getColumnIndex("maCourse")));
            top.setTenCourse(course.getTenCourse());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sql = "SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }

}
