package com.example.baohqph13534_duanmau.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baohqph13534_duanmau.DTO.ThanhVien;
import com.example.baohqph13534_duanmau.Fragment.QuanLyTVFragment;
import com.example.baohqph13534_duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    QuanLyTVFragment fragment;
    private ArrayList<ThanhVien> list;
    TextView maTV, tvTenTV,  tvNamSinh;
    ImageView imgDel;
    public ThanhVienAdapter(Context context, QuanLyTVFragment fragment, ArrayList<ThanhVien> list){
        super(context,0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;


        if ( view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_thanhvien,null);

        }

        final ThanhVien thanhVien = list.get(position);
        if (thanhVien != null){
            maTV = view.findViewById(R.id.tvMaTV);
            maTV.setText("Mã thành viên: "+thanhVien.getMaTV());

            tvTenTV = view.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên thành viên: "+thanhVien.getHoTen());

            tvNamSinh = view.findViewById(R.id.tvNamSinhTV);
            tvNamSinh.setText("Năm sinh: "+thanhVien.getNamSinh());

            imgDel = view.findViewById(R.id.imgDel);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(String.valueOf(thanhVien.getMaTV()));
                }
            });
        }
        if (position%2==0){
            maTV.setTextColor(Color.RED);
            tvTenTV.setTextColor(Color.RED);
            tvNamSinh.setTextColor(Color.RED);

        }else {
            maTV.setTextColor(Color.GREEN);
            tvTenTV.setTextColor(Color.GREEN);
            tvNamSinh.setTextColor(Color.GREEN);
        }

        return view;

    }
}
