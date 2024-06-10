package com.example.chinesestyle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chinesestyle.helpers.SharedPrefsManager;

public class SettingsActivity extends AppCompatActivity {
    private EditText etContact;
    private SeekBar sbFontSize;
    private Button btnApply;
    private Button btnColorPicker;
    private Button btnBack;
    private SharedPrefsManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefsManager = SharedPrefsManager.getInstance(this);

        etContact = findViewById(R.id.etContact);
        sbFontSize = findViewById(R.id.sbFontSize);
        btnApply = findViewById(R.id.btnApply);
        btnColorPicker = findViewById(R.id.btnColorPicker);

        etContact.setText(prefsManager.getContact());
        sbFontSize.setProgress((int) prefsManager.getFontSize());

        btnBack = findViewById(R.id.btnBack);

        btnApply.setOnClickListener(v -> {
            prefsManager.setContact(etContact.getText().toString());
        });

        btnBack.setOnClickListener(v -> {
            finish();
        });

        sbFontSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prefsManager.setFontSize(progress);
                etContact.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnColorPicker.setOnClickListener(v -> {
//            boolean isDarkTheme = prefsManager.isDarkTheme();
//            prefsManager.setDarkTheme(!isDarkTheme);
//            recreate();  // Recreate the activity to apply the new theme
        });
    }
}