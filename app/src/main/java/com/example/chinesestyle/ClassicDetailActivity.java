package com.example.chinesestyle;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import com.example.chinesestyle.helpers.DatabaseHelper;
import com.example.chinesestyle.models.Classic;
import com.example.chinesestyle.helpers.ClassicCategorizer;
import java.io.IOException;

public class ClassicDetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvAuthor, tvContent, tvCategory;
    private SeekBar sbAudioProgress;
    private Button btnPlayPause;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private DatabaseHelper dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_classic_detail);

        tvTitle = this.findViewById(R.id.tvClassicTitle);
        tvAuthor = this.findViewById(R.id.tvClassicAuthor);
        tvContent = this.findViewById(R.id.tvClassicContent);
        tvCategory = this.findViewById(R.id.tvClassicCategory);
        sbAudioProgress = this.findViewById(R.id.sbAudioProgress);
        btnPlayPause = this.findViewById(R.id.btnPlayPause);

        dbHelper = new DatabaseHelper(this);

        int classicId = this.getIntent().getIntExtra("CLASSIC_ID", -1);
        if (classicId != -1) {
            Classic classic = dbHelper.getClassicById(classicId);
            if (classic != null) {
                tvTitle.setText(classic.getTitle());
                tvAuthor.setText(classic.getAuthor());
                tvContent.setText(classic.getContent());

                String category = ClassicCategorizer.categorize(classic.getTitle(), classic.getAuthor(), classic.getContent());
                tvCategory.setText("类别：" + category);

                setupAudioPlayer(classic.getAudioUrl());
            }
        }

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());
    }

    private void setupAudioPlayer(String audioUrl) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
            sbAudioProgress.setMax(mediaPlayer.getDuration());

            btnPlayPause.setOnClickListener(v -> {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlayPause.setText("播放");
                } else {
                    mediaPlayer.start();
                    btnPlayPause.setText("暂停");
                }
            });

            sbAudioProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) mediaPlayer.seekTo(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            });

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sbAudioProgress.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(this, 500);
                }
            }, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }
}