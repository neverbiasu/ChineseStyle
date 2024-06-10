package com.example.chinesestyle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chinesestyle.models.Classic;
import com.example.chinesestyle.adapter.ClassicAdapter;
import com.example.chinesestyle.helpers.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ClassicsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ClassicAdapter adapter;
    private List<Classic> classicsList;
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classics, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_classics);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseHelper = new DatabaseHelper(getContext());
        classicsList = databaseHelper.getAllClassics();
        adapter = new ClassicAdapter(classicsList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Classic> getDummyData() {
        List<Classic> data = new ArrayList<Classic>();
        // 添加更多示例数据
        return data;
    }
}