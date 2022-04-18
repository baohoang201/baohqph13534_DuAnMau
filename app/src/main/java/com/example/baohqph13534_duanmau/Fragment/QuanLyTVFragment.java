package com.example.baohqph13534_duanmau.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.baohqph13534_duanmau.Adapter.ThanhVienAdapter;
import com.example.baohqph13534_duanmau.DTO.ThanhVien;
import com.example.baohqph13534_duanmau.DTO.ThuThu;
import com.example.baohqph13534_duanmau.Database.ThanhVienDAO;
import com.example.baohqph13534_duanmau.Database.ThuThuDAO;
import com.example.baohqph13534_duanmau.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class QuanLyTVFragment extends Fragment {
    ListView lv;
    ArrayList<ThanhVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edTenTV, edNamSinh, edMaTV,EdSearch;
    Button btn_luu, btn_huy;

    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien thanhVien;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_quanlythanhvien,container, false);
        lv = view.findViewById(R.id.lvThanhVien);
        fab = view.findViewById(R.id.btn_ThemTV);
        EdSearch = view.findViewById(R.id.ed_search);
        EdSearch.setOnEditorActionListener((v, actionId, event) -> {
            this.list.clear();
            list =(ArrayList<ThanhVien>) dao.getSearch(EdSearch.getText().toString());
            adapter = new ThanhVienAdapter(getActivity(), this, list);
            lv.setAdapter(adapter);
            return false;
        });



         dao = new ThanhVienDAO(getActivity());
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
                 thanhVien = list.get(position);
                 openDialog(getActivity(), 1);
             }

         });

      return view;
}


public void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_thanhvien);
        edTenTV = dialog.findViewById(R.id.ed_tenTV);
        edNamSinh = dialog.findViewById(R.id.ed_namSinhTV);
        edMaTV = dialog.findViewById(R.id.ed_maTV);
        btn_huy = dialog.findViewById(R.id.btn_huyTV);
        btn_luu = dialog.findViewById(R.id.btn_luuTV);


        edMaTV.setEnabled(false);

        if (type!= 0){
            edMaTV.setText(String.valueOf(thanhVien.getMaTV()));
            edTenTV.setText(thanhVien.getHoTen());
            edNamSinh.setText(thanhVien.getNamSinh());

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
                thanhVien = new ThanhVien();
                thanhVien.setHoTen(edTenTV.getText().toString());
                thanhVien.setNamSinh(edNamSinh.getText().toString());

                if (validate()>0){
                    if (type==0){

                        if (dao.insert(thanhVien)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại",Toast.LENGTH_LONG).show();
                        }
                    }else {

                        thanhVien.setMaTV(Integer.parseInt(edMaTV.getText().toString()));
                        if (dao.update(thanhVien)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_LONG).show();
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
        list = (ArrayList<ThanhVien>)dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }

   public  int validate(){
        int check = 1;
        if (edTenTV.getText().length()==0 ||edNamSinh.getText().length()==0 ){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
            check=-1;
        }else if (Integer.parseInt(edNamSinh.getText().toString())>2021 || Integer.parseInt(edNamSinh.getText().toString())<1800){
            Toast.makeText(getContext(),"Năm sinh không hợp lệ",Toast.LENGTH_SHORT).show();
              check = -1;
        }else if (edTenTV.getText().length()>70){
            Toast.makeText(getContext(),"Quá 70 kí tự, mời nhập lại",Toast.LENGTH_SHORT).show();
        }
        return check;
   }

}
