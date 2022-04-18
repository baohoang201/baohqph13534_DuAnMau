package com.example.baohqph13534_duanmau.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.baohqph13534_duanmau.DTO.Sach;
import com.example.baohqph13534_duanmau.DTO.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDAO(Context context) {
        MySQLHelper mySQLHelper = new MySQLHelper(context);
        db = mySQLHelper.getWritableDatabase();
        this.context = context;
    }

    //Thong ke top10
    public List<Top> getTop(){
        String sqlTop = "SELECT maSach, count(maSach) as soLuong From PHIEUMUON Group by maSach ORDER BY soLuong DESC LIMIT 10";
        List<Top> list = new ArrayList<Top>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor cursor = db.rawQuery(sqlTop, null);
        while(cursor.moveToNext()){
            Top top = new Top();
            Sach sach = sachDAO.getID(String.valueOf(cursor.getInt(0)));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(String.valueOf(cursor.getInt(cursor.getColumnIndex("soLuong")))));
            list.add(top);

        }
        cursor.close();
        return list;

    }

    //Thong ke doanh thu
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(tienThue) as doanhThu FROM PHIEUMUON WHERE ngayThue BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cursor = db.rawQuery(sqlDoanhThu,new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()){
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
