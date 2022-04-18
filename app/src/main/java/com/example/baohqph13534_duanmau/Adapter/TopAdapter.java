package com.example.baohqph13534_duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.baohqph13534_duanmau.DTO.Top;
import com.example.baohqph13534_duanmau.Fragment.Top10Fragment;
import com.example.baohqph13534_duanmau.R;

import java.util.ArrayList;
import java.util.List;

public class TopAdapter extends ArrayAdapter<Top> {

    private Context context;
    private ArrayList<Top> list;
    private Top10Fragment fragment;
    TextView tvTenTop10, tvSLTop10;

    public TopAdapter(@NonNull Context context, Top10Fragment fragment, @NonNull ArrayList<Top> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_top10, null);

        }
        final Top top = list.get(position);
        if (top!= null){
            tvTenTop10 = view.findViewById(R.id.tvTenSachTop10);
            tvTenTop10.setText("Sách: "+top.getTenSach());


        }
        return view;
    }
}
