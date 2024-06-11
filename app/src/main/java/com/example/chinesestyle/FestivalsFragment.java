package com.example.chinesestyle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        dbHelper = new DatabaseHelper(getContext());
        List<Festival> festivalList = dbHelper.getAllFestivals();

        adapter = new FestivalAdapter(festivalList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
