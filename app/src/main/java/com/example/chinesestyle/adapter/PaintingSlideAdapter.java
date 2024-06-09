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
import com.example.chinesestyle.PaintingSlide;
import java.util.List;

public class PaintingSlideAdapter extends RecyclerView.Adapter<PaintingSlideAdapter.SlideViewHolder> {
    private List<PaintingSlide> slides;
    private Context context;

    public PaintingSlideAdapter(Context context, List<PaintingSlide> slides) {
        this.context = context;
        this.slides = slides;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_painting_slide, parent, false);
        return new SlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        PaintingSlide slide = slides.get(position);
        holder.imageView.setImageResource(slide.getImageResourceId());
        holder.textViewDescription.setText(slide.getDescription());
    }

    @Override
    public int getItemCount() {
        return slides.size();
    }

    static class SlideViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewDescription;

        SlideViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewSlide);
            textViewDescription = itemView.findViewById(R.id.textViewSlideDescription);
        }
    }
}