package com.example.chinesestyle;

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
import com.example.chinesestyle.models.Opera;
import com.example.chinesestyle.adapter.OperaAdapter;
import java.util.ArrayList;
import java.util.List;

public class OperaFragment extends Fragment {
    private RecyclerView recyclerViewOpera;
    private OperaAdapter adapter;
    private List<Opera> operas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_opera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewOpera = view.findViewById(R.id.recyclerViewOpera);
        recyclerViewOpera.setLayoutManager(new LinearLayoutManager(getContext()));

        operas = getOperas();
        adapter = new OperaAdapter(getContext(), operas);
        recyclerViewOpera.setAdapter(adapter);
    }

    private List<Opera> getOperas() {
        List<Opera> operas = new ArrayList<>();
        operas.add(new Opera(1, "霸王别姬", "战败的项羽与虞姬的悲壮爱情", R.drawable.mask_bawang, R.raw.aria_bawang));
        operas.add(new Opera(2, "负荆请罪", "蔺相如宽宏原谅廉颇负荆请罪", R.drawable.mask_lianpo, R.raw.aria_lianpo));
        // 添加更多京剧剧目
        return operas;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.releaseMediaPlayer();
        }
    }
}