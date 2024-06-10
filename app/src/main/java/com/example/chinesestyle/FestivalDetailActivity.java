package com.example.chinesestyle;

import android.nfc.Tag;
import android.util.Log;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.Button;
import android.os.Bundle;
import android.widget.Toast;
import android.net.Uri;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.chinesestyle.helpers.DatabaseHelper;
import com.example.chinesestyle.models.Festival;
import com.example.chinesestyle.R;

public class FestivalDetailActivity extends AppCompatActivity {

    private VideoView videoView;
    private SeekBar seekBar;
    private TextView tvFestivalName, tvFestivalDate, tvFestivalOrigin;
    private ImageView ivCustomIcon;
    private TextView tvCustomDesc;

    private DatabaseHelper dbHelper;
    private Festival festival;
    private static final String TAG = "FestivalDetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_detail);

        int festivalId = getIntent().getIntExtra("FESTIVAL_ID", -1);
        if (festivalId == -1) {
            Toast.makeText(this, "节日信息不可用", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        dbHelper = new DatabaseHelper(this);
        festival = dbHelper.getFestivalById(festivalId);
        Log.d(TAG, "onCreate: festival: " + festival);
        Log.d(TAG, "onCreate: festival.getName(): " + festival.getName());
        Log.d(TAG, "onCreate: festival.getIconResourceId(): " + festival.getIconResourceId());
        initViews();
        setupVideo();
        populateFestivalInfo();
        setupCustomAnimation();

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());
    }

    public Festival getFestivalById(int id) {
        // Implementation here...
        return null;
    }

    private void initViews() {
        videoView = findViewById(R.id.videoview_festival);
        seekBar = findViewById(R.id.seekbar_festival_video);
        tvFestivalName = findViewById(R.id.tv_festival_name);
        tvFestivalDate = findViewById(R.id.tv_festival_date);
        tvFestivalOrigin = findViewById(R.id.tv_festival_origin);
        ivCustomIcon = findViewById(R.id.iv_custom_icon);
        tvCustomDesc = findViewById(R.id.tv_custom_desc);
    }

    private void setupVideo() {
//        int videoResId = festival.getVideoResourceName();
//        Log.d(TAG, "setupVideo: videoResId: " + videoResId);
//        String videoPath = "android.resource://" + getPackageName() + "/" +  videoResId;
        int videoResId = R.raw.spring_festival;
//         创建一个指向该资源的 URI
        String videoPath = "android.resource://" + getPackageName() + "/" + videoResId;
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.setOnPreparedListener(mp -> {
            seekBar.setMax(mp.getDuration());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) videoView.seekTo(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            mp.setOnVideoSizeChangedListener((mp1, width, height) -> {
                // 可以根据视频尺寸调整VideoView大小
            });
        });
        videoView.start();
    }

    private void populateFestivalInfo() {
        tvFestivalName.setText(festival.getName());
        tvFestivalDate.setText(festival.getDate());
        tvFestivalOrigin.setText(festival.getOrigin());

        // 这里假设我们只展示一个习俗，实际应用中可以动态生成多个
        ivCustomIcon.setImageResource(getResources().getIdentifier(festival.getCustomIconName(), "drawable", getPackageName()));
        tvCustomDesc.setText(festival.getCustomDescription());
    }

    private void setupCustomAnimation() {
        // 以灯笼为例
        ObjectAnimator animator = ObjectAnimator.ofFloat(ivCustomIcon, "translationY", 0f, -50f, 0f);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.stopPlayback();
    }
}
