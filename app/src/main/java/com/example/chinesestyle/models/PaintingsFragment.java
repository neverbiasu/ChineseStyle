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
import com.example.chinesestyle.Painting;
import com.example.chinesestyle.adapter.PaintingAdapter;
import java.util.ArrayList;
import java.util.List;

public class PaintingsFragment extends Fragment {
    private RecyclerView recyclerViewPaintings;
    private PaintingAdapter adapter;
    private List<Painting> paintings;

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

    private List<Painting> getPaintings() {
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Painting(1, "富春山居图", "黄公望", "元代", "https://example.com/fuchun.jpg"));
        paintings.add(new Painting(2, "千里江山图", "王希孟", "北宋", "https://example.com/qianli.jpg"));
        // 添加更多绘画作品
        return paintings;
    }
}