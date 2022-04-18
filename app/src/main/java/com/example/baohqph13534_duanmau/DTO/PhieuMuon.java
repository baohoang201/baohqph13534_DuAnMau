package com.example.baohqph13534_duanmau.DTO;


import java.util.Date;

public class PhieuMuon {
    private int maPhieuMuon;
    private int IDTT;
    private int maTV;
    private int mvTT;
    private int maSach;
    private Date ngayThue;
    private int tienThue;
    private int traSach;

    public PhieuMuon() {
    }


    public PhieuMuon(int maPhieuMuon, int IDTT, int maTV, int mvTT, int maSach, Date ngayThue, int tienThue, int traSach) {
        this.maPhieuMuon = maPhieuMuon;
        this.IDTT = IDTT;
        this.maTV = maTV;
        this.mvTT = mvTT;
        this.maSach = maSach;
        this.ngayThue = ngayThue;
        this.tienThue = tienThue;
        this.traSach = traSach;
    }


    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMvTT() {
        return mvTT;
    }

    public void setMvTT(int mvTT) {
        this.mvTT = mvTT;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public Date getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(Date ngayThue) {
        this.ngayThue = ngayThue;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public int getIDTT() {
        return IDTT;
    }

    public void setIDTT(int IDTT) {
        this.IDTT = IDTT;
    }
}
