package com.example.baohqph13534_duanmau.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.baohqph13534_duanmau.DTO.ThuThu;
import com.example.baohqph13534_duanmau.Database.ThuThuDAO;
import com.example.baohqph13534_duanmau.R;

public class ChangePassFragment extends Fragment {
EditText passOld, passNew, passNew2;
Button btn_luuChange, btnHuyChange;
ThuThuDAO dao;
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doimatkhau,container, false);
        passOld = view.findViewById(R.id.ed_passOld);
        passNew = view.findViewById(R.id.ed_passNew);
        passNew2 = view.findViewById(R.id.ed_passNew2);
        btn_luuChange = view.findViewById(R.id.btn_luuChange);
        btnHuyChange = view.findViewById(R.id.btn_huyChange);
        dao = new ThuThuDAO(getActivity());
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = preferences.getString("USERNAME","");
        btnHuyChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passOld.setText("");
                passNew.setText("");
                passNew2.setText("");
            }
        });

        btn_luuChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()>0){
                    ThuThu thuThu = dao.getID(String.valueOf(dao.getIdByUsername(user)));
                    thuThu.setMatKhau(passNew.getText().toString());
                    dao.updatePass(thuThu);
                    if (dao.updatePass(thuThu)>0){
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công",  Toast.LENGTH_SHORT).show();
                        passOld.setText("");
                        passNew.setText("");
                        passNew2.setText("");
                    }else {
                        Toast.makeText(getActivity(),"Đổi mật khẩu thất bại",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        return view;

    }
    public int validate(){
        int check =1 ;
        if (passOld.getText().length()==0 || passNew.getText().length()==0 || passNew2.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String passOldz = preferences.getString("PASSWORD","");
            String passNewz = passNew.getText().toString();
            String passNew2z = passNew2.getText().toString();
            if (!passOldz.equals(passOld.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ không đúng",Toast.LENGTH_SHORT).show();
                check= -1;
            }
            if (!passNew2z.equals(passNewz)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
                check = -1;
            }

        }
        return check;
    }
}
