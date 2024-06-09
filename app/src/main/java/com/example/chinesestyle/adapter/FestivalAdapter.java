package com.example.chinesestyle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chinesestyle.R;
import com.example.chinesestyle.models.Festival;

import java.util.List;

public class FestivalAdapter extends RecyclerView.Adapter<FestivalAdapter.FestivalViewHolder> {
    private List<Festival> festivals;

    public FestivalAdapter(List<Festival> festivals) {
        this.festivals = festivals;
    }

    @NonNull
    @Override
    public FestivalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_festival, parent, false);
        return new FestivalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FestivalViewHolder holder, int position) {
        Festival festival = festivals.get(position);
        holder.nameTextView.setText(festival.getName());
        holder.dateTextView.setText(festival.getDate());
        holder.iconImageView.setImageResource(festival.getIconResourceId());

        holder.itemView.setOnClickListener(v -> {
            // TODO: 跳转到节日详情页或显示详情对话框
        });
    }

    @Override
    public int getItemCount() {
        return festivals.size();
    }

    static class FestivalViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView dateTextView;
        ImageView iconImageView;

        public FestivalViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_festival_name);
            dateTextView = itemView.findViewById(R.id.tv_festival_date);
            iconImageView = itemView.findViewById(R.id.iv_festival_icon);
        }
    }
}