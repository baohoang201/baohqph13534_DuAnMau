package com.example.baohqph13534_duanmau.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.baohqph13534_duanmau.Adapter.LoaiSachSpinnerAdapter;
import com.example.baohqph13534_duanmau.Adapter.SachAdapter;
import com.example.baohqph13534_duanmau.DTO.LoaiSach;
import com.example.baohqph13534_duanmau.DTO.Sach;
import com.example.baohqph13534_duanmau.Database.LoaiSachDAO;
import com.example.baohqph13534_duanmau.Database.SachDAO;
import com.example.baohqph13534_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLySachFragment extends Fragment {
    ListView lv;
    ArrayList<Sach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue;
    Spinner spinner;
    Button btn_save, btn_cancel;
    static SachDAO dao;
    SachAdapter adapter;
    Sach sach;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach> listloaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;
    private int idLoaiSach;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanlysach,container, false);
        lv = view.findViewById(R.id.lvSach);
        fab = view.findViewById(R.id.btn_themSach);
        dao = new SachDAO(getActivity());
        capNhatLv();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openDialog(getActivity(),0);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //list = (ArrayList<Sach>)dao.getAll();
                sach = list.get(position);
                openDialog(getActivity(),1);
            }
        });
        return view;
    }
    protected  void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_sach);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edtenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spSach);
        btn_cancel = dialog.findViewById(R.id.btn_cancelSach);
        btn_save = dialog.findViewById(R.id.btn_addSach);
        listloaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listloaiSach = (ArrayList<LoaiSach>)loaiSachDAO.getAll();
        ArrayAdapter<String> list = new ArrayAdapter(getActivity() , android.R.layout.simple_list_item_1 , this.dao.getListNameLoaiSach());
        spinnerAdapter = new LoaiSachSpinnerAdapter(context, listloaiSach);
        spinner.setAdapter(list);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // maLoaiSach = listloaiSach.get(position).getMaLoaiSach();
                idLoaiSach = dao.getIdLoaiSach(spinner.getSelectedItem().toString());
                Toast.makeText(context, "id laf: " + idLoaiSach, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaSach.setEnabled(false);
        if (type!= 0){
            edMaSach.setText(String.valueOf(sach.getMaSach()));
            edTenSach.setText(sach.getTenSach());
            edGiaThue.setText(String.valueOf(sach.getGiaChoThue()));

            for (int i = 0; i<listloaiSach.size(); i++){
                if (sach.getMaLoaiSach() ==(listloaiSach.get(i).getMaLoaiSach())){
                    position = i;
                }
                Log.i("demo","posSach"+position);
                spinner.setSelection(position);
            }
        }
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sach = new Sach(idLoaiSach , 0,edTenSach.getText().toString() ,spinner.getSelectedItem().toString() ,Integer.parseInt(edGiaThue.getText().toString()));
                if (validate()>0){
                    if (type == 0){
                        if (dao.insert(sach)>0){
                            Toast.makeText(context,"Them thanh cong",Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(context,"Them that bai",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        sach.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
                        if (dao.update(sach)>0){
                            Toast.makeText(context,"Sua thanh cong",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(context,"Sua that bai",Toast.LENGTH_LONG).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }


            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
    public void xoa(final  String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete?");
        builder.setMessage("Ban co muon xoa?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(id);
                capNhatLv();
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

    void capNhatLv(){
        list = (ArrayList<Sach>)dao.getAll();
        adapter = new SachAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);

    }

    public int validate(){
        int check = 1;
        if (edTenSach.getText().length() == 0|| edGiaThue.getText().length() ==0){
            Toast.makeText(getContext(),"Ban phai nhap du thong tin",Toast.LENGTH_LONG).show();
            check =-1;
        }
        return check;
    }
}
