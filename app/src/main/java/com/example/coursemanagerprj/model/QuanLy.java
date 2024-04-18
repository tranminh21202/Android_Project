package com.example.coursemanagerprj.model;

public class QuanLy {
    private String maQL;
    private String hoTen;
    private String matKhau;

    public QuanLy() {
    }

    public QuanLy(String maQL, String hoTen, String matKhau) {
        this.maQL = maQL;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public String getMaQL() {
        return maQL;
    }

    public void setMaQL(String maQL) {
        this.maQL = maQL;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
