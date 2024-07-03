package com.example.chinesestyle.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.chinesestyle.R;
import com.example.chinesestyle.adapter.PaintingAdapter;
import java.util.ArrayList;
import java.util.List;

public class PaintingsFragment extends Fragment {
    private RecyclerView recyclerViewPaintings;
    private PaintingAdapter adapter;
    private List<com.example.chinesestyle.PaintingsFragment> paintings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_paintings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewPaintings = view.findViewById(R.id.recyclerViewPaintings);
        recyclerViewPaintings.setLayoutManager(new LinearLayoutManager(getContext()));

        paintings = getPaintings(); // 模拟数据
        adapter = new PaintingAdapter(getContext(), paintings);
        recyclerViewPaintings.setAdapter(adapter);
    }

    private List<com.example.chinesestyle.PaintingsFragment> getPaintings() {
        List<com.example.chinesestyle.PaintingsFragment> paintings = new ArrayList<>();
        paintings.add(new com.example.chinesestyle.PaintingsFragment(1, "富春山居图", "黄公望", "元代", R.drawable.fuchun));
        paintings.add(new com.example.chinesestyle.PaintingsFragment(2, "千里江山图", "王希孟", "北宋", R.drawable.qianli));
        // 添加更多绘画作品
        return paintings;
    }
}