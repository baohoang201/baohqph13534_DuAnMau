package com.example.baohqph13534_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baohqph13534_duanmau.DTO.ThanhVien;
import com.example.baohqph13534_duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV;
    public ThanhVienSpinnerAdapter(Context context, ArrayList<ThanhVien> list){
        super(context,0,list);
        this.context  = context;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.thanhvien_item_spinner, null);
        }

        final ThanhVien thanhVien = list.get(position);
        if (thanhVien!=null){
            tvMaTV = view.findViewById(R.id.tvMaTVSP);
            tvMaTV.setText(thanhVien.getMaTV()+".");

            tvTenTV = view.findViewById(R.id.tvTenTVSP);
            tvTenTV.setText(thanhVien.getHoTen());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position,  View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.thanhvien_item_spinner, null);
        }

        final ThanhVien thanhVien = list.get(position);
        if (thanhVien!=null){
            tvTenTV = view.findViewById(R.id.tvTenTVSP);
            tvTenTV.setText(thanhVien.getHoTen());
        }
        return view;
    }
}
