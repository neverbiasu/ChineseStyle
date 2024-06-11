package com.example.chinesestyle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
        holder.imageViewPainting.setImageResource(painting.getImageResource());

        // 添加点击事件监听器
        holder.itemView.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(context, com.example.chinesestyle.PaintingDetailActivity.class);
            intent.putExtra("paintingId", painting.getId());
            context.startActivity(intent);
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
