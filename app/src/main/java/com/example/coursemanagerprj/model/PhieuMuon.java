package com.example.coursemanagerprj.model;

import java.util.Date;

public class PhieuMuon {
    private int maPM;
    private String maQL;
    private int maTV;
    private int maCourse;
    private Date ngay;
    private int tienThue;
    private int traCourse;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, String maQL, int maTV, int maCourse, Date ngay, int tienThue, int traCourse) {
        this.maPM = maPM;
        this.maQL = maQL;
        this.maTV = maTV;
        this.maCourse = maCourse;
        this.ngay = ngay;
        this.tienThue = tienThue;
        this.traCourse = traCourse;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaQL() {
        return maQL;
    }

    public void setMaQL(String maQL) {
        this.maQL = maQL;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaCourse() {
        return maCourse;
    }

    public void setMaCourse(int maCourse) {
        this.maCourse = maCourse;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTraCourse() {
        return traCourse;
    }

    public void setTraCourse(int traCourse) {
        this.traCourse = traCourse;
    }
}
