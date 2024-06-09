package com.example.chinesestyle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.List;
import java.util.ArrayList;
import com.example.chinesestyle.adapter.PaintingSlideAdapter;

public class PaintingDetailActivity extends AppCompatActivity {
    private TextView textViewPaintingTitle, textViewArtist, textViewDynasty, textViewDescription;
    private ViewPager2 viewPagerPainting;
    private TabLayout tabLayoutPainting;
    private PaintingSlideAdapter slideAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting_detail);

        textViewPaintingTitle = findViewById(R.id.textViewPaintingTitle);
        textViewArtist = findViewById(R.id.textViewArtist);
        textViewDynasty = findViewById(R.id.textViewDynasty);
        textViewDescription = findViewById(R.id.textViewDescription);
        viewPagerPainting = findViewById(R.id.viewPagerPainting);
        tabLayoutPainting = findViewById(R.id.tabLayoutPainting);

        // 从 Intent 中获取画作的 ID
        int paintingId = getIntent().getIntExtra("paintingId", -1);
        Painting painting = getPaintingById(paintingId);
        if (painting != null) {
            setupPaintingDetails(painting);
        }
    }

    private void setupPaintingDetails(Painting painting) {
        textViewPaintingTitle.setText(painting.getTitle());
        textViewArtist.setText(painting.getArtist());
        textViewDynasty.setText(painting.getDynasty());
        textViewDescription.setText("这里是关于" + painting.getTitle() + "的详细介绍...");

        List<PaintingSlide> slides = getPaintingSlides(painting.getId());
        slideAdapter = new PaintingSlideAdapter(this, slides);
        viewPagerPainting.setAdapter(slideAdapter);

        new TabLayoutMediator(tabLayoutPainting, viewPagerPainting,
                (tab, position) -> tab.setText("细节 " + (position + 1))
        ).attach();
    }

    private Painting getPaintingById(int id) {
        // 这应该从数据库获取，现在用硬编码代替
        if (id == 1) {
            return new Painting(1, "富春山居图", "黄公望", "元代", R.drawable.fuchun);
        } else if (id == 2) {
            return new Painting(2, "千里江山图", "王希孟", "北宋", R.drawable.qianli);
        }
        return null;
    }

    private List<PaintingSlide> getPaintingSlides(int paintingId) {
        List<PaintingSlide> slides = new ArrayList<>();
        if (paintingId == 1) {
            slides.add(new PaintingSlide(R.drawable.fuchun_detail1, "富春江两岸的山峦起伏，山腰处云雾缭绕。"));
            slides.add(new PaintingSlide(R.drawable.fuchun_detail2, "江面上点缀着几叶小舟，显示了人与自然的和谐。"));
            // 添加更多细节
        } else if (paintingId == 2) {
            slides.add(new PaintingSlide(R.drawable.qianli_detail1, "远处高耸的山峰，象征着帝国的雄伟。"));
            slides.add(new PaintingSlide(R.drawable.qianli_detail2, "近处的亭台楼阁，展现了中国古典园林的精妙。"));
            // 添加更多细节
        }
        return slides;
    }
}