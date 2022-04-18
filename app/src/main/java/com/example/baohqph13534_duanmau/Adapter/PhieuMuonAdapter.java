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

import com.example.baohqph13534_duanmau.DTO.PhieuMuon;
import com.example.baohqph13534_duanmau.DTO.Sach;
import com.example.baohqph13534_duanmau.DTO.ThanhVien;
import com.example.baohqph13534_duanmau.Database.PhieuMuonDAO;
import com.example.baohqph13534_duanmau.Database.SachDAO;
import com.example.baohqph13534_duanmau.Database.ThanhVienDAO;
import com.example.baohqph13534_duanmau.Fragment.QuanLyPMFragment;
import com.example.baohqph13534_duanmau.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    QuanLyPMFragment fragment;
    private ArrayList<PhieuMuon> list;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView imgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    PhieuMuonDAO dao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



    public PhieuMuonAdapter(@NonNull Context context, QuanLyPMFragment fragment, @NonNull ArrayList<PhieuMuon> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment  = fragment;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.phieumuon_item,null);
        }
        final PhieuMuon phieuMuon = list.get(position);

        if (phieuMuon!= null){
            tvMaPM = view.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã phiếu: "+phieuMuon.getMaPhieuMuon());

            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(phieuMuon.getMaSach()));
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.getTenSach());

            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(phieuMuon.getMaTV()));
            tvTenTV = view.findViewById(R.id.tenTVPM);
            tvTenTV.setText("Tên thành viên: "+thanhVien.getHoTen());


            tvTienThue = view.findViewById(R.id.tvTienThuePM);
            tvTienThue.setText("Tiền thuê: "+sach.getGiaChoThue());

            tvNgay = view.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày thuê: "+phieuMuon.getNgayThue());
            tvTraSach = view.findViewById(R.id.tvTraSach);
            if (phieuMuon.getTraSach() ==1){
                    tvTraSach.setTextColor(Color.BLUE);
                    tvTraSach.setText("Đã trả sách");
            }else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            imgDel = view.findViewById(R.id.imgDelPM);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.xoa(phieuMuon.getMaPhieuMuon());
                    notifyDataSetChanged();

                }
            });

        }
        return view;

    }
}
