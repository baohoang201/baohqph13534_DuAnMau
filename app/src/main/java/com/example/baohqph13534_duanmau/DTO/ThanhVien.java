package com.example.baohqph13534_duanmau.DTO;

public class ThanhVien {
    private String hoTen;
    private int maTV;
    private String namSinh;

    public ThanhVien() {
    }

    public ThanhVien(String hoTen, int maTV, String namSinh) {
        this.hoTen = hoTen;
        this.maTV = maTV;
        this.namSinh = namSinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
