package com.example.appshoe.model;

public class Loaisp {
    public int Id;
    public String Tenloaisp;
    public String Anhloaisp;

    public Loaisp(int id, String tenloaisp, String anhloaisp) {
        Id = id;
        Tenloaisp = tenloaisp;
        Anhloaisp = anhloaisp;
    }

    public int getId() {
        return Id;
    }

    public String getTenloaisp() {
        return Tenloaisp;
    }

    public String getAnhloaisp() {
        return Anhloaisp;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTenloaisp(String tenloaisp) {
        Tenloaisp = tenloaisp;
    }

    public void setAnhloaisp(String anhloaisp) {
        Anhloaisp = anhloaisp;
    }
}
