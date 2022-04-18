package com.example.baohqph13534_duanmau.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.baohqph13534_duanmau.Adapter.PhieuMuonAdapter;
import com.example.baohqph13534_duanmau.Adapter.SachSpinnerAdapter;
import com.example.baohqph13534_duanmau.Adapter.ThanhVienSpinnerAdapter;
import com.example.baohqph13534_duanmau.DTO.PhieuMuon;
import com.example.baohqph13534_duanmau.DTO.Sach;
import com.example.baohqph13534_duanmau.DTO.ThanhVien;
import com.example.baohqph13534_duanmau.Database.PhieuMuonDAO;
import com.example.baohqph13534_duanmau.Database.SachDAO;
import com.example.baohqph13534_duanmau.Database.ThanhVienDAO;
import com.example.baohqph13534_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static java.time.LocalDateTime.now;

public class QuanLyPMFragment extends Fragment {
    ListView lv;
    ArrayList<PhieuMuon> list;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienthue;
    CheckBox chkTraSach;
    Button btn_save, btn_cancel;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon phieuMuon;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listsach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;
    FloatingActionButton fab;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private int y , m , d;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_quanlyphieumuon,container, false);
        lv = view.findViewById(R.id.lvPhieuMuon);
        fab = view.findViewById(R.id.btn_themPM);
        dao = new PhieuMuonDAO(getActivity());
        CapNhatLv();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                phieuMuon = list.get(position);
                openDialog(getActivity(),1);

            }
        });


        return view;
    }
    public void openDialog(final Context context, final  int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_phieumuon);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spMaTV);
        spSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay2);
        tvTienthue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btn_cancel = dialog.findViewById(R.id.btn_cancelPM);
        btn_save = dialog.findViewById(R.id.btnSavePM);

        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>)thanhVienDAO.getAll();

        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);




//        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" + (String.valueOf(y) + String.valueOf(m+1) + String.valueOf(d)));

        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaTV();
                Toast.makeText(context, "Chọn "+listThanhVien.get(position).getHoTen(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDAO = new SachDAO(context);
        listsach = (ArrayList<Sach>)sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, listsach);
        spSach.setAdapter(sachSpinnerAdapter);

        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listsach.get(position).getMaSach();
                tienThue = listsach.get(position).getGiaChoThue();
                Toast.makeText(context, "Chọn "+listsach.get(position).getTenSach(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaPM.setEnabled(false);
        if (type != 0){
            edMaPM.setText(String.valueOf(phieuMuon.getMaPhieuMuon()));
            for (int i= 0; i<listThanhVien.size();i++) {
                if (phieuMuon.getMaPhieuMuon() == (listThanhVien.get(i).getMaTV())) {
                    positionTV = i;
                }
                spTV.setSelection(positionTV);
            }

                for (int i = 0;i< listsach.size(); i++) {
                    if (phieuMuon.getMaSach() == (listsach.get(i).getMaSach())) {
                        positionSach = i;
                    }
                    spSach.setSelection(positionSach);
                    tvNgay.setText("Ngày thuê: "+String.valueOf(phieuMuon.getNgayThue()));

                    Sach sach = sachDAO.getID(String.valueOf(phieuMuon.getMaSach()));
                    tvTienthue.setText("Tiền thuê: "+tienThue);
                }


                    if (phieuMuon.getTraSach()==1){
                        chkTraSach.setChecked(true);
                    }else {
                        chkTraSach.setChecked(false);
                    }
                }

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
        btn_save.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                y = Calendar.getInstance().get(Calendar.YEAR);
                m = Calendar.getInstance().get(Calendar.MONTH);
                d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                String date = y + "-" + (m+1) + "-" +d;

                phieuMuon = new PhieuMuon();
                phieuMuon.setMaSach(maSach);
                phieuMuon.setMaTV(maThanhVien);
                phieuMuon.setTienThue(tienThue);
                try {
                    phieuMuon.setNgayThue(simpleDateFormat.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (chkTraSach.isChecked()){
                    phieuMuon.setTraSach(1);
                }else {
                    phieuMuon.setTraSach(0);
                }
                if (type==0){
                    if (dao.insert(phieuMuon)>0){
                        Toast.makeText(context,"Them thanh cong",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Them that bai",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    phieuMuon.setMaPhieuMuon(Integer.parseInt(edMaPM.getText().toString()));
                    if (dao.update(phieuMuon)>0){
                        Toast.makeText(context,"Sua thanh cong",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Sua that bai",Toast.LENGTH_SHORT).show();
                    }
                }
                CapNhatLv();
                dialog.dismiss();

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
    public  void  xoa(int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                CapNhatLv();
             //   Toast.makeText(getActivity(), "sdfsdfsdf", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void CapNhatLv(){
        list = (ArrayList<PhieuMuon>)dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(),this, list);
        lv.setAdapter(adapter);
    }
   public int validate(){
        int check = 1;
        return check;
   }
}


