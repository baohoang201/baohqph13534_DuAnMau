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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.baohqph13534_duanmau.DTO.ThuThu;
import com.example.baohqph13534_duanmau.Database.ThuThuDAO;
import com.example.baohqph13534_duanmau.R;

public class AddAccountFragment extends Fragment {
    EditText edUser, edHoten, edPass, edRePass;
    Button btn_addUser, btn_clearText;
    ThuThuDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_add_account,null);
      edUser = view.findViewById(R.id.edTenUser);
      edHoten = view.findViewById(R.id.edHoTenAdd);
      edPass = view.findViewById(R.id.edPass);
      edRePass = view.findViewById(R.id.edRePass);
      btn_addUser = view.findViewById(R.id.btn_addUser);
      btn_clearText = view.findViewById(R.id.btn_clearText);

      dao = new ThuThuDAO(getActivity());

      btn_clearText.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              edUser.setText("");
              edHoten.setText("");
              edPass.setText("");
              edRePass.setText("");
          }
      });

      btn_addUser.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              ThuThu thuThu = new ThuThu();
              thuThu.setMaTT(edUser.getText().toString());
              thuThu.setTenTT(edHoten.getText().toString());
              thuThu.setMatKhau(edPass.getText().toString());


              if (validate()>0){
                  if (dao.insert(thuThu)>0){
                      Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                      edUser.setText("");
                      edHoten.setText("");
                      edPass.setText("");
                      edRePass.setText("");
                  }else {
                      Toast.makeText(getActivity(),"Thêm thất bại",Toast.LENGTH_SHORT).show();
                  }
              }
          }

      });


      return view;
    }
    public int validate(){
        String regex = "^[A-Z].*";
        int check = 1;

        if (edUser.getText().length()== 0 || edHoten.getText().length() ==0 || edPass.getText().length()==0 || edRePass.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if (edUser.getText().toString().length()<5){
            Toast.makeText(getContext(), "Độ dài ngắn hơn 5, mời bạn nhập lại", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if (edUser.getText().toString().length()>15){
            Toast.makeText(getContext(), "Quá 15 kí tự, mời bạn nhập lại", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();

            if (!pass.equals(rePass)){
                Toast.makeText(getContext(),"Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }

        if(edUser.getText().toString().matches(regex)== false){
            Toast.makeText(getContext(), "Chữ cái đầu phải viết hoa", Toast.LENGTH_SHORT).show();
            check = -1;
        }

       

        return check;
    }
}
