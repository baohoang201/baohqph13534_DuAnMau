package com.example.baohqph13534_duanmau.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baohqph13534_duanmau.DTO.LoaiSach;
import com.example.baohqph13534_duanmau.DTO.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    Context context;
    private SQLiteDatabase db;
    MySQLHelper mySQLHelper;

    public SachDAO(Context context) {
         mySQLHelper = new MySQLHelper(context);
        db = mySQLHelper.getWritableDatabase();
        this.context = context;
    }
    public long insert(Sach sach){
        ContentValues values = new ContentValues();
        values.put("tenSach",sach.getTenSach());
        values.put("maLoaiSach",sach.getMaLoaiSach());
        values.put("tenLoaiSach",sach.getTenLoaiSach());
        values.put("giaThue", sach.getGiaChoThue());
        values.put("khuyenMai",sach.getKhuyenMai());
        return  db.insert("SACH",null,values);
    }

    public int update(Sach sach){
        ContentValues values = new ContentValues();
        values.put("tenSach",sach.getTenSach());
        values.put("maLoaiSach",sach.getMaLoaiSach());
        values.put("tenLoaiSach",sach.getTenLoaiSach());
        values.put("giaThue", sach.getGiaChoThue());
        values.put("khuyenMai",sach.getKhuyenMai());
        return db.update("SACH",values,"maSach=?",new String[]{String.valueOf(sach.getMaSach())});

    }

    public int delete(String id){
        return db.delete("SACH","maSach=?",new String[]{id});
    }


    public List<Sach> getAll(){
        String sql = "SELECT * FROM SACH";
        return getData(sql);
    }

    public Sach getID(String id){
        String sql = "SELECT*FROM SACH where maSach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }


    private List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<Sach>();

        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
           Sach sach = new Sach();
            sach.setMaSach(cursor.getInt(0));
            sach.setMaLoaiSach(cursor.getInt(1));
            sach.setTenLoaiSach(cursor.getString(2));
            sach.setTenSach(cursor.getString(3));
            sach.setGiaChoThue(cursor.getInt(4));
            sach.setKhuyenMai(cursor.getString(5));
            list.add(sach);
        }
        cursor.close();
        return list;

    }
    public List<String> getListNameLoaiSach(){
        List<String> list = new ArrayList<String>();
        this.db = mySQLHelper.getReadableDatabase();
        String sql = "SELECT * FROM LOAISACH" ;
        Cursor cursor = this.db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(1);
                list.add(name);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    public int getIdLoaiSach(String tenLoai){
        int id = 0;
        this.db = mySQLHelper.getReadableDatabase();
        String sql = "SELECT * FROM LOAISACH WHERE LOAISACH.tenLoaiSach = '" +tenLoai+ "'";
        Cursor cursor = this.db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                id = Integer.parseInt(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return id;
    }

}

