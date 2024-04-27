package com.example.coursemanagerprj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursemanagerprj.R;
import com.example.coursemanagerprj.fragment.ThanhVienFragment;
import com.example.coursemanagerprj.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment thanhVienFragment;
    private ArrayList<ThanhVien> list;
    TextView tvMaTv, tvTenTv, tvNamSinh;
    ImageView imageView;


    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment thanhVienFragment, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context=context;
        this.thanhVienFragment=thanhVienFragment;
        this.list=list;
    }

    public void setList(ArrayList<ThanhVien> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanhvien_item,null);
        }
        final ThanhVien item = list.get(position);
        if(item!=null){
            tvMaTv=v.findViewById(R.id.tvMaTV);
            tvMaTv.setText("Mã thành viên: "+item.getMaTV());
            tvTenTv=v.findViewById(R.id.tvTenTV);
            tvTenTv.setText("Họ tên: "+item.getHoTen());
            tvNamSinh=v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: "+item.getNamSinh());
            imageView=v.findViewById(R.id.imgDelete);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thanhVienFragment.delete(String.valueOf(item.getMaTV()));
            }
        });

        return v;
    }
}
//public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienViewHolder>{
//    private List<ThanhVien> list;
//    ThanhVienFragment fragment;
//
//    public ThanhVienAdapter() {
//        list=new ArrayList<>();
//    }
//    public ThanhVienAdapter(List<ThanhVien> list){
//        this.list = list;
//        this.fragment=new ThanhVienFragment();
//    }
//
//    @NonNull
//    @Override
//    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.thanhvien_item,parent,false);
//        return new ThanhVienViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ThanhVienViewHolder holder, int position) {
//        ThanhVien thanhVien=list.get(position);
//        if(thanhVien==null)
//            return;
//        holder.tvMaTV.setText("Mã thành viên: " + thanhVien.getMaTV()+"");
//        holder.tvTenTV.setText("Họ tên: " + thanhVien.getHoTen());
//        holder.tvNamSinh.setText("Năm sinh: " + thanhVien.getNamSinh());
//        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragment.delete(String.valueOf(thanhVien.getMaTV()));
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        if(list!=null){
//            return list.size();
//        }
//        return 0;
//    }
//
//    public class ThanhVienViewHolder extends RecyclerView.ViewHolder{
//        private ImageView imageView;
//        private TextView tvMaTV, tvTenTV, tvNamSinh;
//        private ImageView imageViewDelete;
//
//        public ThanhVienViewHolder(@NonNull View view) {
//            super(view);
//            imageView= view.findViewById(R.id.img);
//            tvMaTV=view.findViewById(R.id.tvMaTV);
//            tvTenTV=view.findViewById(R.id.tvTenTV);
//            tvNamSinh=view.findViewById(R.id.tvNamSinh);
//            imageViewDelete=view.findViewById(R.id.imgDelete);
//        }
//    }
//}