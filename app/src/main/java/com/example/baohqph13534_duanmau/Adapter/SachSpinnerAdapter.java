package com.example.baohqph13534_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baohqph13534_duanmau.DTO.Sach;
import com.example.baohqph13534_duanmau.DTO.ThanhVien;
import com.example.baohqph13534_duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
    TextView tvMaSach, tvTenSach;

    public SachSpinnerAdapter(@NonNull Context context,  @NonNull ArrayList<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sach_item_spinner, null);
        }

        final Sach sach = list.get(position);
        if (sach!=null){
            tvMaSach = view.findViewById(R.id.tvMaSachSP);
            tvMaSach.setText(sach.getMaSach()+".");

            tvTenSach = view.findViewById(R.id.tvTenSachSP);
            tvTenSach.setText(sach.getTenSach());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull  ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sach_item_spinner, null);
        }

        final Sach sach = list.get(position);
        if (sach!=null){
            tvMaSach = view.findViewById(R.id.tvMaSachSP);
            tvMaSach.setText(sach.getMaSach()+".");

            tvTenSach = view.findViewById(R.id.tvTenSachSP);
            tvTenSach.setText(sach.getTenSach());
        }
        return view;
    }
}
