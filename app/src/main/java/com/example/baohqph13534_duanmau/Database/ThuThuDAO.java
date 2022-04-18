package com.example.baohqph13534_duanmau.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baohqph13534_duanmau.DTO.Sach;
import com.example.baohqph13534_duanmau.DTO.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    Context context;
    private SQLiteDatabase db;
    MySQLHelper mySQLHelper;

    public ThuThuDAO(Context context) {
         mySQLHelper = new MySQLHelper(context);
        db = mySQLHelper.getWritableDatabase();
        this.context = context;
    }
    public long insert(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("maTT",thuThu.getMaTT());
        values.put("hoTen",thuThu.getTenTT());
        values.put("matKhau",thuThu.getMatKhau());
        return  db.insert("THUTHU",null,values);
    }

    public int update(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("maTT",thuThu.getMaTT());
        values.put("hoTen",thuThu.getTenTT());
        values.put("matKhau",thuThu.getMatKhau());
        return db.update("THUTHU",values,"IDTT=?",new String[]{String.valueOf(thuThu.getId())});

    }

    public int delete(String id){
        return db.delete("THUTHU","IDTT=?",new String[]{id});
    }


    public List<ThuThu> getAll(){
        String sql = "SELECT*FROM THUTHU";
        return getData(sql);
    }

    public ThuThu getID(String id){
        String sql = "SELECT*FROM THUTHU where IDTT=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }


    private List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<ThuThu>();

        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThuThu thuThu = new ThuThu();
            thuThu.setId(cursor.getInt(0));
            thuThu.setMaTT(cursor.getString(1));
            thuThu.setTenTT(cursor.getString(2));
            thuThu.setMatKhau(cursor.getString(3));
            list.add(thuThu);
        }
        cursor.close();
        return list;

    }

    //update pass
    public int updatePass(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("maTT",thuThu.getMaTT());
        values.put("hoTen",thuThu.getTenTT());
        values.put("matKhau",thuThu.getMatKhau());
        return db.update("THUTHU",values,"IDTT=?", new String[]{String.valueOf(thuThu.getId())});

    }

    //check login
    public int checkLogin(String id, String password){
        String sql = "SELECT * FROM THUTHU WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql, id,password);
        if (list.size()==0){
            return -1;
        }
        return 1;

    }
    public int getIdByUsername(String username){
        int id = 0;
        db = mySQLHelper.getWritableDatabase();
        String sql = "select * from THUTHU where  maTT ='" +username+ "'";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }



}

