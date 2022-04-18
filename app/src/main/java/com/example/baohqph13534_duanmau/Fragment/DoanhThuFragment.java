package com.example.baohqph13534_duanmau.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.baohqph13534_duanmau.Database.ThongKeDAO;
import com.example.baohqph13534_duanmau.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DoanhThuFragment extends Fragment {
    ImageView imgNgayStart, imgNgayEnd;
    EditText ed_ngayStart, edNgayEnd;
    Button btn_doanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    TextView tvDoanhThu;
    int mY, mM, mD;


    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_doanhthu,container, false);
        ed_ngayStart = view.findViewById(R.id.edNgayStart);
        edNgayEnd = view.findViewById(R.id.edNgayEnd);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        btn_doanhThu = view.findViewById(R.id.btnTinhDoanhThu);
        imgNgayStart = view.findViewById(R.id.img_NgayStart);
        imgNgayEnd = view.findViewById(R.id.img_ngayEnd);

        imgNgayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mY = c.get(Calendar.YEAR);
                mM = c.get(Calendar.MONTH);
                mD = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),0,mDateTuNgay,mY, mM,mD);
                datePickerDialog.show();
            }
        });

        imgNgayEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mY = c.get(Calendar.YEAR);
                mM = c.get(Calendar.MONTH);
                mD = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),0,mDateDenNgay,mY, mM,mD);
                datePickerDialog.show();
            }
        });

        btn_doanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = ed_ngayStart.getText().toString();
                String denNgay = edNgayEnd.getText().toString();
                ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
                tvDoanhThu.setText("Doanh thu: "+thongKeDAO.getDoanhThu(tuNgay, denNgay)+"VNĐ");

            }
        });



        return view;
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mY = year;
            mM = month;
            mD = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mY, mM, mD);
            ed_ngayStart.setText(sdf.format(c.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mY = year;
            mM = month;
            mD = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mY, mM, mD);
            edNgayEnd.setText(sdf.format(c.getTime()));
        }
    };
}
