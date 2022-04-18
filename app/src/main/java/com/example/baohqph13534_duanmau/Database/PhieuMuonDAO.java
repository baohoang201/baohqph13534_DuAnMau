package com.example.baohqph13534_duanmau.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baohqph13534_duanmau.DTO.LoaiSach;
import com.example.baohqph13534_duanmau.DTO.PhieuMuon;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    Context context;
    private SQLiteDatabase db;


    public PhieuMuonDAO(Context context) {
        MySQLHelper mySQLHelper = new MySQLHelper(context);
        db = mySQLHelper.getWritableDatabase();
        this.context = context;
    }
    public long insert(PhieuMuon phieuMuon){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues values = new ContentValues();
        values.put("IDTT",phieuMuon.getIDTT());
        values.put("maTV", phieuMuon.getMaTV());
        values.put("maSach",phieuMuon.getMaSach());
        values.put("ngayThue", simpleDateFormat.format(phieuMuon.getNgayThue()));
        values.put("ngayTra",phieuMuon.getTraSach());
        values.put("tienThue",phieuMuon.getTienThue());
        return  db.insert("PHIEUMUON",null,values);
    }

    public int update(PhieuMuon phieuMuon){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues values = new ContentValues();
        values.put("IDTT",phieuMuon.getIDTT());
        values.put("maTV", phieuMuon.getMaTV());
        values.put("maSach",phieuMuon.getMaSach());
        values.put("ngayThue", simpleDateFormat.format(phieuMuon.getNgayThue()));
        values.put("ngayTra",phieuMuon.getTraSach());
        values.put("tienThue",phieuMuon.getTienThue());
        return db.update("PHIEUMUON",values,"maPM=?",new String[]{String.valueOf(phieuMuon.getMaPhieuMuon())});

    }

    public int delete(int id){
        return db.delete("PHIEUMUON","maPM=?",new String[]{String.valueOf(id)});
    }


    public List<PhieuMuon> getAll(){
        String sql = "SELECT*FROM PHIEUMUON";
        return getData(sql);
    }

    public PhieuMuon getID(String id){
        String sql = "SELECT*FROM PHIEUMUON where maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }


    private List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<PhieuMuon>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date startDate = simpleDateFormat.parse(DateOfLastUpdate);
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            try {
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaPhieuMuon(cursor.getInt(0));
            phieuMuon.setMaTV(cursor.getInt(1));
            phieuMuon.setMvTT(cursor.getInt(2));
            phieuMuon.setMaSach(cursor.getInt(3));
            phieuMuon.setNgayThue(simpleDateFormat.parse(cursor.getString(4)));
            phieuMuon.setTienThue(cursor.getInt(5));
            phieuMuon.setTraSach(cursor.getInt(6));

            list.add(phieuMuon);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return list;

    }

}

