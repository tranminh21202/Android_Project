package com.example.coursemanagerprj.model;

public class Course {
    private int maCourse;
    private String tenCourse;
    private int giaThue;
    private int maLoai;

    public Course() {
    }

    public Course(int maCourse, String tenCourse, int giaThue, int maLoai) {
        this.maCourse = maCourse;
        this.tenCourse = tenCourse;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }

    public int getMaCourse() {
        return maCourse;
    }

    public void setMaCourse(int maCourse) {
        this.maCourse = maCourse;
    }

    public String getTenCourse() {
        return tenCourse;
    }

    public void setTenCourse(String tenCourse) {
        this.tenCourse = tenCourse;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
