package com.example.baohqph13534_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baohqph13534_duanmau.DTO.LoaiSach;
import com.example.baohqph13534_duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> list;
    TextView tvMaLoaiSach, tvTenLoaiSach;
    public LoaiSachSpinnerAdapter(@NonNull Context context, @NonNull ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context =context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view = convertView;
       if (view == null){
           LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           view = inflater.inflate(R.layout.item_spinner_loaisach,null);

       }
       final LoaiSach loaiSach = list.get(position);
       if (loaiSach != null){
           tvMaLoaiSach = view.findViewById(R.id.tvMaLoaiSachSP);
           tvMaLoaiSach.setText(loaiSach.getMaLoaiSach()+ ".");
           tvTenLoaiSach = view.findViewById(R.id.tvTenLoaiSachSP);
           tvTenLoaiSach.setText(loaiSach.getTenLoaiSach());


       }
       return view;
    }

    @Override
    public View getDropDownView(int position,View convertView,  ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_spinner_loaisach,null);

        }
        final LoaiSach loaiSach = list.get(position);
        if (loaiSach != null){
            tvMaLoaiSach = view.findViewById(R.id.tvMaLoaiSachSP);
            tvMaLoaiSach.setText(loaiSach.getMaLoaiSach()+ ".");
            tvTenLoaiSach = view.findViewById(R.id.tvTenLoaiSachSP);
            tvTenLoaiSach.setText(loaiSach.getTenLoaiSach());


        }

        return view;
    }
}
