package com.example.baohqph13534_duanmau.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.baohqph13534_duanmau.Adapter.LoaiSachAdapter;
import com.example.baohqph13534_duanmau.Adapter.ThanhVienAdapter;
import com.example.baohqph13534_duanmau.DTO.LoaiSach;
import com.example.baohqph13534_duanmau.DTO.ThanhVien;
import com.example.baohqph13534_duanmau.Database.LoaiSachDAO;
import com.example.baohqph13534_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLyLSFragment extends Fragment {
    ListView lv;
    ArrayList<LoaiSach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoaiSach, edTenLoaiSach , ed_searchLS;;
    Button btn_luu, btn_huy;

    LoaiSachAdapter adapter;
    LoaiSachDAO dao;
    LoaiSach loaiSach;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_quanlyloaisach,container, false);
        lv = view.findViewById(R.id.lvLoaiSach);
        fab = view.findViewById(R.id.btn_addLoaiSach);
        dao = new LoaiSachDAO(getActivity());
        ed_searchLS = view.findViewById(R.id.ed_searchLS);
        ed_searchLS.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                list.clear();
                list = (ArrayList<LoaiSach>)dao.searchLS(ed_searchLS.getText().toString());
                adapter = new LoaiSachAdapter(getActivity(), QuanLyLSFragment.this, list);
                lv.setAdapter(adapter);
                return false;
            }
        });

        capNhatLV();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loaiSach = list.get(position);
                openDialog(getActivity(),1);
            }
        });

        return view;

    }

    public void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loaisach);
        edMaLoaiSach = dialog.findViewById(R.id.edMaLS);
        edTenLoaiSach = dialog.findViewById(R.id.edTenLS);
        btn_huy = dialog.findViewById(R.id.btn_cancelLoaiSach);
        btn_luu = dialog.findViewById(R.id.btn_themLoaiSach);


        edMaLoaiSach.setEnabled(false);

        if (type!= 0){
            edMaLoaiSach.setText(String.valueOf(loaiSach.getMaLoaiSach()));
            edTenLoaiSach.setText(loaiSach.getTenLoaiSach());

        }
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSach = new LoaiSach();
                loaiSach.setTenLoaiSach(edTenLoaiSach.getText().toString());

                if (validate()>0){
                    if (type==0){

                        if (dao.insert(loaiSach)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }else {

                        loaiSach.setMaLoaiSach(Integer.parseInt(edMaLoaiSach.getText().toString()));
                        if (dao.update(loaiSach)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLV();
                    dialog.dismiss();
                }

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                dao.delete(id);
                capNhatLV();
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

    void capNhatLV(){
        list = (ArrayList<LoaiSach>)dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }

    public  int validate(){
        int check = 1;
        if (edTenLoaiSach.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }


}
