package com.example.coursemanagerprj.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.coursemanagerprj.R;
import com.example.coursemanagerprj.adapter.TopAdapter;
import com.example.coursemanagerprj.dao.PhieuMuonDAO;
import com.example.coursemanagerprj.model.Top;

import java.util.ArrayList;

public class TopFragment extends Fragment {
    ListView listView;
    ArrayList<Top> list;
    TopAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        listView=v.findViewById(R.id.lvTop);
        PhieuMuonDAO phieuMuonDAO=new PhieuMuonDAO(getActivity());
        list=(ArrayList<Top>) phieuMuonDAO.getTop();
        adapter=new TopAdapter(getActivity(),this,list);
        listView.setAdapter(adapter);
        return v;
    }
}