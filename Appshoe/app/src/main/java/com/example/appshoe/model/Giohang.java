package com.example.appshoe.model;

public class Giohang {
    public int idsp;
    public String tensp;
    public long giasp;
    public String hinhsp;
    public int soluongsp;
    public int sizesp;

    public Giohang(int idsp, String tensp, long giasp, String hinhsp, int soluongsp, int sizesp) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhsp = hinhsp;
        this.soluongsp = soluongsp;
        this.sizesp = sizesp;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public int getSizesp() {
        return sizesp;
    }

    public void setSizesp(int sizesp) {
        this.sizesp = sizesp;
    }
}
