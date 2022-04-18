package com.example.baohqph13534_duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.baohqph13534_duanmau.Adapter.TopAdapter;
import com.example.baohqph13534_duanmau.DTO.Top;
import com.example.baohqph13534_duanmau.Database.ThongKeDAO;
import com.example.baohqph13534_duanmau.R;

import java.util.ArrayList;

public class Top10Fragment extends Fragment {
ListView lv;
ArrayList<Top> list;
TopAdapter adapter;
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_thongketop10,container, false);
        lv = view.findViewById(R.id.lvTop10);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
        list = (ArrayList<Top>)thongKeDAO.getTop();
        adapter = new TopAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);

        return view;
    }
}
