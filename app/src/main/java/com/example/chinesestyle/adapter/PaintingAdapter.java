package com.example.chinesestyle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.chinesestyle.R;
import com.example.chinesestyle.Painting;
import java.util.List;

public class PaintingAdapter extends RecyclerView.Adapter<PaintingAdapter.PaintingViewHolder> {
    private List<Painting> paintings;
    private Context context;

    public PaintingAdapter(Context context, List<Painting> paintings) {
        this.context = context;
        this.paintings = paintings;
    }

    @NonNull
    @Override
    public PaintingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_painting, parent, false);
        return new PaintingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaintingViewHolder holder, int position) {
        Painting painting = paintings.get(position);
        holder.textViewPaintingTitle.setText(painting.getTitle());

        // 加载图片（这里假设我们使用Glide库）
        Glide.with(context)
                .load(painting.getImageUrl())
                .placeholder(R.drawable.placeholder_painting)
                .into(holder.imageViewPainting);

        holder.itemView.setOnClickListener(v -> {
            // TODO: 实现点击打开绘画详情
        });
    }

    @Override
    public int getItemCount() {
        return paintings.size();
    }

    static class PaintingViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPainting;
        TextView textViewPaintingTitle;

        PaintingViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPainting = itemView.findViewById(R.id.imageViewPainting);
            textViewPaintingTitle = itemView.findViewById(R.id.textViewPaintingTitle);
        }
    }
}