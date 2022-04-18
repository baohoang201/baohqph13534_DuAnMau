package com.example.baohqph13534_duanmau.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baohqph13534_duanmau.Database.ThuThuDAO;
import com.example.baohqph13534_duanmau.MainActivity;
import com.example.baohqph13534_duanmau.R;

public class LoginActivity extends AppCompatActivity {
EditText name, password;
Button login;
ThuThuDAO dao;
String strUser, strPass;
CheckBox checkRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = findViewById(R.id.name_login);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        checkRemember = findViewById(R.id.checkRemember);
        dao = new ThuThuDAO(this);

        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        name.setText(preferences.getString("USERNAME",""));
        password.setText(preferences.getString("PASSWORD",""));
        checkRemember.setChecked(preferences.getBoolean("REMEMBER",false));


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            checkLogin();

            }
        });


    }
    public void checkLogin(){
        strUser = name.getText().toString();
        strPass = password.getText().toString();
        if(strUser.isEmpty()|| strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Ten dang nhap hoac mat khau khong dc trong",Toast.LENGTH_LONG).show();

        }else {
            if (dao.checkLogin(strUser,strPass)>0||(strUser.equals("admin")&&strPass.equals("admin"))){
                Toast.makeText(getApplicationContext(),"Dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass,checkRemember.isChecked());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user",strUser);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Ten dang nhap hoac mat khau khong dung",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void rememberUser(String u, String p, boolean status){
        SharedPreferences preferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);

        }
        editor.commit();

    }
}