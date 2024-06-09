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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.text.Editable;
import android.text.TextWatcher;
import com.example.chinesestyle.R;
import com.example.chinesestyle.models.Opera;
import com.example.chinesestyle.adapter.OperaAdapter;
import java.util.ArrayList;
import java.util.List;

public class OperaFragment extends Fragment {
    private RecyclerView recyclerViewOpera;
    private OperaAdapter adapter;
    private List<Opera> operas;
    private EditText editTextSearch;
    private Spinner spinnerCategory;

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

        editTextSearch = view.findViewById(R.id.editTextSearch);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);

        setupSearch();
        setupCategorySpinner();
    }

    private void setupSearch() {
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterOperas(s.toString(), spinnerCategory.getSelectedItem().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupCategorySpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,
                new String[]{"全部", OperaCategory.HISTORICAL, OperaCategory.ROMANCE, OperaCategory.LEGENDARY});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterOperas(editTextSearch.getText().toString(), parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void filterOperas(String query, String category) {
        List<Opera> filteredOperas = new ArrayList<>();
        for (Opera opera : getAllOperas()) {
            if ((category.equals("全部") || opera.getCategory().equals(category)) &&
                    opera.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredOperas.add(opera);
            }
        }
        adapter.updateData(filteredOperas);
    }

    private List<Opera> getOperas() {
        List<Opera> operas = new ArrayList<>();
        operas.add(new Opera(1, "霸王别姬", "战败的项羽与虞姬的悲壮爱情", R.drawable.mask_bawang, R.raw.aria_bawang, "霸王之歌", "霸王脸谱以红色为主，象征勇猛、正直。","项羽与虞姬的悲壮爱情",OperaCategory.ROMANCE));
        operas.add(new Opera(2, "负荆请罪", "蔺相如宽宏原谅廉颇负荆请罪", R.drawable.mask_lianpo, R.raw.aria_lianpo, "负荆之歌", "廉颇脸谱以白色为主，象征高洁、廉正。","蔺相如宽宏原谅廉颇负荆请罪",OperaCategory.HISTORICAL));
        // 添加更多京剧剧目
        return operas;
    }

    public List<Opera> getAllOperas() {
        return this.operas;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.releaseMediaPlayer();
        }
    }
}