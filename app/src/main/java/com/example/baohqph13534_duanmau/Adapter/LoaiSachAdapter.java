package com.example.baohqph13534_duanmau.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baohqph13534_duanmau.DTO.LoaiSach;
import com.example.baohqph13534_duanmau.Database.LoaiSachDAO;
import com.example.baohqph13534_duanmau.Fragment.QuanLyLSFragment;
import com.example.baohqph13534_duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {

    private Context context;
    private ArrayList<LoaiSach> list;
    private QuanLyLSFragment fragment;
    TextView tvMaLoaiSach, tvTenSach,nhaCC;
    LoaiSachDAO loaiSachDAO;
    ImageView imgDel;



    public LoaiSachAdapter(@NonNull Context context, QuanLyLSFragment fragment, @NonNull ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_loaisach, null);
        }



       final LoaiSach loaiSach = list.get(position);

        if (loaiSach != null){
            tvMaLoaiSach = view.findViewById(R.id.tvMaLoaiSach);
            tvMaLoaiSach.setText("Mã loại sách: "+loaiSach.getMaLoaiSach());

            tvTenSach = view.findViewById(R.id.tvTenLoaiSach);
            tvTenSach.setText("Tên loại sách: "+loaiSach.getTenLoaiSach());

            nhaCC = view.findViewById(R.id.tvNhacc);
            nhaCC.setText("Nhà cung cấp: "+loaiSach.getNhaCC());


            imgDel = view.findViewById(R.id.imgDelLoaiSach);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(String.valueOf(loaiSach.getMaLoaiSach()));
                }
            });
            if (loaiSach.getTenLoaiSach().contains("N")==true){
                tvMaLoaiSach.setTextColor(Color.RED);

            }else if (loaiSach.getTenLoaiSach().contains("A")==true){
                tvMaLoaiSach.setTextColor(Color.GREEN);

            }else {
                tvMaLoaiSach.setTextColor(Color.BLACK);
            }
        }


        return view;

    }
}
