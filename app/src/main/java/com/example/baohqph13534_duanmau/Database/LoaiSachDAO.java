package com.example.baohqph13534_duanmau.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baohqph13534_duanmau.DTO.LoaiSach;
import com.example.baohqph13534_duanmau.DTO.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    Context context;
    private SQLiteDatabase db;
    MySQLHelper mySQLHelper;


    public LoaiSachDAO(Context context) {
         mySQLHelper = new MySQLHelper(context);
        db = mySQLHelper.getWritableDatabase();
        this.context = context;
    }
    public long insert(LoaiSach loaiSach){
        ContentValues values = new ContentValues();
        values.put("tenLoaiSach",loaiSach.getTenLoaiSach());
        values.put("nhaCC",loaiSach.getNhaCC());
        return  db.insert("LOAISACH",null,values);
    }

    public int update(LoaiSach loaiSach){
        ContentValues values = new ContentValues();
        values.put("tenLoaiSach",loaiSach.getTenLoaiSach());
        values.put("nhaCC",loaiSach.getNhaCC());
        return db.update("LOAISACH",values,"maLoaiSach=?",new String[]{String.valueOf(loaiSach.getMaLoaiSach())});

    }

    public int delete(String id){
        return db.delete("LOAISACH","maLoaiSach=?",new String[]{id});
    }


    public List<LoaiSach> getAll(){
        String sql = "SELECT*FROM LOAISACH";
        return getData(sql);
    }

    public LoaiSach getID(String id){
        String sql = "SELECT*FROM LOAISACH where maLoaiSach=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }
    public String getName(String name){

        String sql = "SELECT* FROM LOAISACH";
        Cursor cursor = this.db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                name = cursor.getString(1);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return name;

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


    private List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<LoaiSach>();

        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.setMaLoaiSach(cursor.getInt(0));
            loaiSach.setTenLoaiSach(cursor.getString(1));
            loaiSach.setNhaCC(cursor.getString(2));
            list.add(loaiSach);

        }
        cursor.close();
        return list;

    }
    public List<LoaiSach> searchLS(String searchLS){
        String sql = "SELECT*FROM LOAISACH WHERE nhaCC='"+searchLS+"'";
        return getData(sql);
    }

}

