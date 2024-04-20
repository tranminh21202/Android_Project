package com.example.coursemanagerprj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coursemanagerprj.R;
import com.example.coursemanagerprj.fragment.TopFragment;
import com.example.coursemanagerprj.model.Top;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment topFragment;
    ArrayList<Top> list;
    TextView tvCourse, tvSoLuong;
    ImageView imageView;

    public TopAdapter(@NonNull Context context, TopFragment topFragment, ArrayList<Top> list) {
        super(context, 0, list);
        this.context = context;
        this.topFragment = topFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item,null);
        }
        final Top item = list.get(position);
        if(item != null){
            tvCourse=v.findViewById(R.id.tvCourse);
            tvCourse.setText("Khoá học: " + item.getTenCourse());
            tvSoLuong=v.findViewById(R.id.tvSL);
            tvSoLuong.setText("Số lượng: " + item.getSoLuong());
        }
        return v;
    }
}
