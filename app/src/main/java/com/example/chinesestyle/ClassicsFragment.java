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

import java.util.ArrayList;
import java.util.List;

public class ClassicsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ClassicAdapter adapter;
    private List<Classic> classicsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classics, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_classics);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        classicsList = getDummyData(); // 临时方法，后续替换为真实数据
        adapter = new ClassicAdapter(classicsList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Classic> getDummyData() {
        List<Classic> data = new ArrayList<Classic>();
        data.add(new Classic("春晓", "唐代 孟浩然", "春眠不觉晓，处处闻啼鸟..."));
        data.add(new Classic("静夜思", "唐代 李白", "床前明月光，疑是地上霜..."));
        // 添加更多示例数据
        return data;
    }
}