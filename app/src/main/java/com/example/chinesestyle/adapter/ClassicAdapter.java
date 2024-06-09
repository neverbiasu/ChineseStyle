package com.example.chinesestyle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chinesestyle.R;
import com.example.chinesestyle.models.Classic;

import java.util.List;

public class ClassicAdapter extends RecyclerView.Adapter<ClassicAdapter.ClassicViewHolder> {
    private List<Classic> classics;

    public ClassicAdapter(List<Classic> classics) {
        this.classics = classics;
    }

    @Override
    public ClassicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classic, parent, false);
        return new ClassicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassicViewHolder holder, int position) {
        Classic classic = classics.get(position);
        holder.titleTextView.setText(classic.getTitle());
        holder.authorTextView.setText(classic.getAuthor());

        holder.itemView.setOnClickListener(v -> {
            // TODO: 显示诗词全文的对话框
        });
    }

    @Override
    public int getItemCount() {
        return classics.size();
    }

    class ClassicViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView authorTextView;

        public ClassicViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_classic_title);
            authorTextView = itemView.findViewById(R.id.tv_classic_author);
        }
    }
}