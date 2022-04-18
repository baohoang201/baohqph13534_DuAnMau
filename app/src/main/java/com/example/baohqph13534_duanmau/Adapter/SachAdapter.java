package com.example.baohqph13534_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baohqph13534_duanmau.DTO.LoaiSach;
import com.example.baohqph13534_duanmau.DTO.Sach;
import com.example.baohqph13534_duanmau.Database.LoaiSachDAO;
import com.example.baohqph13534_duanmau.Fragment.QuanLySachFragment;
import com.example.baohqph13534_duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
    QuanLySachFragment fragment;
    TextView tvMaSach, tvLoaiSach, tvTenSach, tvGiathue, tvKhuyenMai;
    ImageView imgDel;

    public SachAdapter(@NonNull Context context,QuanLySachFragment fragment, @NonNull ArrayList<Sach> list) {
        super(context, 0, list);
        this.context =context;
        this.fragment = fragment;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view = convertView;
       if (view == null){
           LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view = inflater.inflate(R.layout.item_sach, null);
       }
       final  Sach sach = list.get(position);
       if (sach!= null){
           LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
           LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(sach.getMaLoaiSach()));

           tvMaSach = view.findViewById(R.id.tvMaSach);
           tvMaSach.setText("Mã sách: "+sach.getMaSach());

           tvTenSach = view.findViewById(R.id.tvTenSachItem);
           tvTenSach.setText("Tên sách: "+sach.getTenSach());

           tvGiathue = view.findViewById(R.id.tvGiaThueSachItem);
           tvGiathue.setText("Giá thuê: "+sach.getGiaChoThue());

           tvLoaiSach = view.findViewById(R.id.tvTenLoaiSachItem);
           tvLoaiSach.setText("Loại sách: "+sach.getTenLoaiSach());

           tvKhuyenMai = view.findViewById(R.id.tvKhuyenMai);
           tvKhuyenMai.setText("Khuyến mãi: "+sach.getKhuyenMai());

       }
       imgDel = view.findViewById(R.id.imgDelSach);
       imgDel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               fragment.xoa(String.valueOf(sach.getMaSach()));
           }
       });
       return view;
    }
}
