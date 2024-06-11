package com.example.chinesestyle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.res.AssetFileDescriptor;
import com.example.chinesestyle.models.Opera;
import java.io.IOException;
import android.app.AlertDialog;

public class OperaDetailActivity extends AppCompatActivity {
    private TextView textViewOperaTitle, textViewStorySummary, textViewAriaName;
    private ImageView imageViewOperaMask;
    private Button buttonPlay;
    private Button buttonPause;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opera_detail);

        textViewOperaTitle = this.findViewById(R.id.textViewOperaTitle);
        textViewStorySummary = this.findViewById(R.id.textViewStorySummary);
        textViewAriaName = this.findViewById(R.id.textViewAriaName);
        imageViewOperaMask = this.findViewById(R.id.imageViewOperaMask);
        buttonPlay = this.findViewById(R.id.buttonPlay);
        buttonPause = this.findViewById(R.id.buttonPause);

        mediaPlayer = new MediaPlayer();

        int operaId = this.getIntent().getIntExtra("operaId", -1);
        Opera opera = getOperaById(operaId);
        if (opera != null) {
            setupOperaDetails(opera);
        }

        buttonPlay.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });

        buttonPause.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        imageViewOperaMask.setOnClickListener(v -> showMaskDialog(opera.getMaskDescription()));

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());
    }

    private void setupOperaDetails(Opera opera) {
        textViewOperaTitle.setText(opera.getTitle());
        textViewStorySummary.setText(opera.getStorySummary());
        textViewAriaName.setText(opera.getAriaName());
        imageViewOperaMask.setImageResource(opera.getMaskResourceId());

        try {
            AssetFileDescriptor afd = this.getResources().openRawResourceFd(opera.getAriaResourceId());
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMaskDialog(String maskDescription) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.mask_meaning)
                .setMessage(maskDescription)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private Opera getOperaById(int id) {
        // 这里应该从数据库或服务器获取，现在用硬编码代替
        if (id == 1) {
            return new Opera(1, "霸王别姬",
                    "项羽兵败，虞姬为了不连累项羽，在霸王面前自刎。项羽悲痛欲绝，最终也选择了自杀。",
                    R.drawable.mask_bawang, R.raw.aria_bawang,
                    "霸王之歌", "霸王脸谱以红色为主，象征勇猛、正直。眉间一抹墨，表其悲壮命运。",
                    "项羽与虞姬的悲壮爱情",OperaCategory.ROMANCE);
        } else if (id == 2) {
            return new Opera(2, "廉颇负荆请罪",
                    "廉颇因误解蔺相如，后深感愧疚，遂负荆请罪以示诚意。展现了廉颇的勇于认错和蔺相如的大度。",
                    R.drawable.mask_lianpo, R.raw.aria_lianpo,
                    "请罪词", "廉颇脸谱以红黑为主，象征勇猛、刚直。眉宇间透露出其深感愧疚和悔改之情。",
                    "蔺相如宽宏原谅廉颇负荆请罪",OperaCategory.HISTORICAL);
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
