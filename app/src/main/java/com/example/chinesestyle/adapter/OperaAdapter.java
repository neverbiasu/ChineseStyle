package com.example.chinesestyle.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chinesestyle.R;
import com.example.chinesestyle.models.Opera;
import com.example.chinesestyle.OperaDetailActivity;
import java.io.IOException;
import java.util.List;

public class OperaAdapter extends RecyclerView.Adapter<OperaAdapter.OperaViewHolder> {
    private List<Opera> operas;
    private Context context;
    private MediaPlayer mediaPlayer;

    public OperaAdapter(Context context, List<Opera> operas) {
        this.context = context;
        this.operas = operas;
        this.mediaPlayer = new MediaPlayer();
    }

    @Override
    public OperaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_opera, parent, false);
        return new OperaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OperaViewHolder holder, int position) {
        Opera opera = operas.get(position);
        holder.textViewOperaTitle.setText(opera.getTitle());
        holder.textViewOperaDescription.setText(opera.getDescription());
        holder.imageViewOperaMask.setImageResource(opera.getMaskResourceId());

        holder.buttonPlayAria.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            try {
                mediaPlayer.reset();
                AssetFileDescriptor afd = context.getResources().openRawResourceFd(opera.getAriaResourceId());
                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OperaDetailActivity.class);
            intent.putExtra("operaId", opera.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return operas.size();
    }

    static class OperaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewOperaMask;
        TextView textViewOperaTitle, textViewOperaDescription;
        Button buttonPlayAria;

        OperaViewHolder(View itemView) {
            super(itemView);
            imageViewOperaMask = itemView.findViewById(R.id.imageViewOperaMask);
            textViewOperaTitle = itemView.findViewById(R.id.textViewOperaTitle);
            textViewOperaDescription = itemView.findViewById(R.id.textViewOperaDescription);
            buttonPlayAria = itemView.findViewById(R.id.buttonPlayAria);
        }
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void updateData(List<Opera> newOperas) {
        this.operas = newOperas;
        notifyDataSetChanged();
    }
}