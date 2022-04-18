package com.example.baohqph13534_duanmau.DTO;

public class Sach {
    private int maLoaiSach;
    private int maSach;
    private String tenLoaiSach;
    private String tenSach;
    private int giaChoThue;

    public String getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(String khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    private String khuyenMai;
    public String getTenLoaiSach() {
        return tenLoaiSach;
    }

    public void setTenLoaiSach(String tenLoaiSach) {
        this.tenLoaiSach = tenLoaiSach;
    }


    public Sach() {
    }

    public Sach(int maLoaiSach, int maSach, String tenSach, String tenLoaiSach, int giaChoThue) {
        this.maLoaiSach = maLoaiSach;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaChoThue = giaChoThue;
        this.tenLoaiSach = tenLoaiSach;
    }

    public int getMaLoaiSach() {
        return maLoaiSach;
    }

    public void setMaLoaiSach(int maLoaiSach) {
        this.maLoaiSach = maLoaiSach;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaChoThue() {
        return giaChoThue;
    }

    public void setGiaChoThue(int giaChoThue) {
        this.giaChoThue = giaChoThue;
    }
}
