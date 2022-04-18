package com.example.baohqph13534_duanmau.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baohqph13534_duanmau.DTO.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    Context context;
    private SQLiteDatabase db;


    public ThanhVienDAO(Context context) {
        MySQLHelper mySQLHelper = new MySQLHelper(context);
        db = mySQLHelper.getWritableDatabase();
        this.context = context;
    }
    public long insert(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put("hoTen",thanhVien.getHoTen());
        values.put("namSinh",thanhVien.getNamSinh());
        return  db.insert("THANHVIEN",null,values);
    }

    public int update(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put("hoTen",thanhVien.getHoTen());
        values.put("namSinh",thanhVien.getNamSinh());
        return db.update("THANHVIEN",values,"maTV=?",new String[]{String.valueOf(thanhVien.getMaTV())});

    }

        public int delete(String id){
            return db.delete("THANHVIEN","maTV=?",new String[]{id});
        }


    public List<ThanhVien> getAll(){
        String sql = "SELECT*FROM THANHVIEN";
        return getData(sql);
    }

    public ThanhVien getID(String id){
        String sql = "SELECT*FROM THANHVIEN where maTV=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }


    private List<ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> list = new ArrayList<ThanhVien>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.setMaTV(cursor.getInt(0));
            thanhVien.setHoTen(cursor.getString(1));
            thanhVien.setNamSinh(cursor.getString(2));
            list.add(thanhVien);

        }
        return list;

    }

    public List<ThanhVien> getSearch(String textSearch){
        String sql = "SELECT*FROM THANHVIEN WHERE hoTen LIKE'"+textSearch+"%'";
        return getData(sql);
    }

}

