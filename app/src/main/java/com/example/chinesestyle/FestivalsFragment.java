package com.example.chinesestyle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chinesestyle.adapter.FestivalAdapter;
import com.example.chinesestyle.helpers.DatabaseHelper;
import com.example.chinesestyle.models.Festival;

import java.util.List;

public class FestivalsFragment extends Fragment {
    private RecyclerView recyclerView;
    private FestivalAdapter adapter;
    private List<Festival> festivalList;
    private DatabaseHelper dbHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_festivals, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_festivals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dbHelper = new DatabaseHelper(getContext());
        List<Festival> festivalList = dbHelper.getAllFestivals();

        adapter = new FestivalAdapter(festivalList);
        recyclerView.setAdapter(adapter);

        return view;
    }

//    private List<Festival> getDummyFestivals() {
//        List<Festival> festivals = new ArrayList<>();
//        festivals.add(new Festival("春节", "农历正月初一", R.drawable.icon_spring_festival));
//        festivals.add(new Festival("端午节", "农历五月初五", R.drawable.icon_dragon_boat));
//        festivals.add(new Festival("中秋节", "农历八月十五", R.drawable.icon_mid_autumn));
//        // 添加更多节日
//        return festivals;
//    }
}