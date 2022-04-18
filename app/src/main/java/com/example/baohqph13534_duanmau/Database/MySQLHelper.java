package com.example.baohqph13534_duanmau.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "QuanLySach17.db";
    public MySQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATETHUTHU;
        CREATETHUTHU = "CREATE TABLE THUTHU(IDTT integer primary key autoincrement,maTT text , hoTen text, matKhau text)";
        db.execSQL(CREATETHUTHU);

        String CREATETHANHVIEN = "CREATE TABLE THANHVIEN(maTV integer primary key autoincrement, hoTen text, namSinh date)";
        db.execSQL(CREATETHANHVIEN);

        String CREATELOAISACH = "CREATE TABLE LOAISACH(maLoaiSach integer primary key autoincrement, tenLoaiSach text,nhaCC text)";
        db.execSQL(CREATELOAISACH);
        String sql = "INSERT INTO LOAISACH VALUES ( null ,'java','GDVN')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAISACH VALUES ( null ,'java2','GDVN2')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAISACH VALUES ( null ,'java3','GDVN3')";
        db.execSQL(sql);



        String CREATESACH = "CREATE TABLE SACH(maSach integer primary key autoincrement, maLoaiSach integer references LOAISACH(maLoaiSach),tenLoaiSach text  , tenSach text, giaThue integer, khuyenMai text)";
        db.execSQL(CREATESACH);

        String sql1 = "INSERT INTO SACH VALUES(null,'1','CNTT','Java3','3000','10%')";
        db.execSQL(sql1);
        sql1 = "INSERT INTO SACH VALUES(null,'2','CNTT','Android','5000','40%')";
        db.execSQL(sql1);
        sql1 = "INSERT INTO SACH VALUES(null,'3','Am thuc','Nau com','4000','60%')";
        db.execSQL(sql1);

        String CREATEPHIEUMUON = "CREATE TABLE PHIEUMUON(" +
                "maPM integer primary key autoincrement," +//0
                " maTV integer references THANHVIEN(maTV) ," +//1
                " IDTT integer references THUTHU(IDTT)," +//2
                " maSach integer references SACH(maSach)," +//3
                " ngayThue DATE," +//4
                "tienThue integer, " +//5
                "ngayTra date)";//6
        db.execSQL(CREATEPHIEUMUON);

        String CREATETOP10 = "CREATE TABLE TOP10(maTop integer, tenSach text, soLuong integer)";
        db.execSQL(CREATETOP10);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS THUTHU");
        db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
        db.execSQL("DROP TABLE IF EXISTS LOAISACH");
        db.execSQL("DROP TABLE IF EXISTS SACH");
        db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
        db.execSQL("DROP TABLE IF EXISTS TOP10");


        onCreate(db);

    }
}
