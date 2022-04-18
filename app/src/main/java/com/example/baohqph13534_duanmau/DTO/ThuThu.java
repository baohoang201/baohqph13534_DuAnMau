package com.example.baohqph13534_duanmau.DTO;

public class ThuThu {
    private String maTT;
    private String tenTT;
    private String matKhau;
    private int id;

    public ThuThu() {
    }

    public ThuThu(String maTT, String tenTT, String matKhau, int id) {
        this.maTT = maTT;
        this.tenTT = tenTT;
        this.matKhau = matKhau;
        this.id = id;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
