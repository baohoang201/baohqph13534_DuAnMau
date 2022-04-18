package com.example.baohqph13534_duanmau;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.baohqph13534_duanmau.DTO.ThuThu;
import com.example.baohqph13534_duanmau.Database.ThuThuDAO;
import com.example.baohqph13534_duanmau.Fragment.AddAccountFragment;
import com.example.baohqph13534_duanmau.Fragment.ChangePassFragment;
import com.example.baohqph13534_duanmau.Fragment.DoanhThuFragment;
import com.example.baohqph13534_duanmau.Fragment.HomeFragment;
import com.example.baohqph13534_duanmau.Fragment.QuanLyLSFragment;
import com.example.baohqph13534_duanmau.Fragment.QuanLyPMFragment;
import com.example.baohqph13534_duanmau.Fragment.QuanLySachFragment;
import com.example.baohqph13534_duanmau.Fragment.QuanLyTVFragment;
import com.example.baohqph13534_duanmau.Fragment.Top10Fragment;
import com.example.baohqph13534_duanmau.SplashScreen.LoginActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawerLayout;
    private static int FRAGMENT_HOME = 0;
    private static int FRAGMENT_QUANLYPHIEUMUON = 1;
    private static int FRAGMENT_QUANLYLOAISACH = 2;
    private static int FRAGMENT_QUANLYSACH = 3;
    private static int FRAGMENT_QUANLYTHANHVIEN = 4;
    private static int FRAGMENT_TOP10 = 5;
    private static int FRAGMENT_DOANHTHU = 6;
    private static int FRAGMENT_DOIMK = 7;
    private static int FRAGMENT_LOGOUT = 8;
    private static int FRAGMENT_ADDACCOUNT = 9;

    private int  mCurrentFragment = FRAGMENT_HOME;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerlayout);
        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationIcon(android.R.color.white);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator();
       // toolbar().setNavitationIconColor
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        

    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.nav_home){
            if (mCurrentFragment!= FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }

        }else if (id==R.id.nav_quanlyphieumuon){
            if (mCurrentFragment!= FRAGMENT_QUANLYPHIEUMUON){
                replaceFragment(new QuanLyPMFragment());
                mCurrentFragment = FRAGMENT_QUANLYPHIEUMUON;
            }

        }else if (id==R.id.nav_quanlyloaisach){
            if (mCurrentFragment!= FRAGMENT_QUANLYLOAISACH){
                replaceFragment(new QuanLyLSFragment());
                mCurrentFragment = FRAGMENT_QUANLYLOAISACH;
            }

        }else if (id==R.id.nav_quanlysach){
            if (mCurrentFragment!= FRAGMENT_QUANLYSACH){
                replaceFragment(new QuanLySachFragment());
                mCurrentFragment = FRAGMENT_QUANLYSACH;
            }

        }else if (id==R.id.nav_quanlythanhvien){
            if (mCurrentFragment!= FRAGMENT_QUANLYTHANHVIEN){
                replaceFragment(new QuanLyTVFragment());
                mCurrentFragment = FRAGMENT_QUANLYTHANHVIEN;
            }

        }else if (id==R.id.nav_doanhthu){
            if (mCurrentFragment!= FRAGMENT_DOANHTHU){
                replaceFragment(new DoanhThuFragment());
                mCurrentFragment = FRAGMENT_DOANHTHU;
            }

        }else if (id==R.id.nav_top10){
            if (mCurrentFragment!= FRAGMENT_TOP10){
                replaceFragment(new Top10Fragment());
                mCurrentFragment = FRAGMENT_TOP10;
            }

        }else if (id==R.id.nav_changepassword){
            if (mCurrentFragment!= FRAGMENT_DOIMK){
                replaceFragment(new ChangePassFragment());
                mCurrentFragment = FRAGMENT_DOIMK;
            }

        }else if (id==R.id.nav_logout){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        } else if (id==R.id.nav_addAccount){
            if (mCurrentFragment!= FRAGMENT_ADDACCOUNT){
                replaceFragment(new AddAccountFragment());
                mCurrentFragment = FRAGMENT_ADDACCOUNT;
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
            moveTaskToBack(false);

    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }




}