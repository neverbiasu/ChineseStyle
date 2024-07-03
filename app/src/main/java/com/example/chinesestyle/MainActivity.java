package com.example.chinesestyle;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.view.Menu;

import com.example.chinesestyle.models.PaintingsFragment;
import com.example.chinesestyle.helpers.SharedPrefsManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private SharedPrefsManager prefsManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefsManager = SharedPrefsManager.getInstance(this);
        setTheme(prefsManager.isDarkTheme() ? R.style.AppTheme_Dark : R.style.AppTheme_Light);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_classics) {
                    selectedFragment = new ClassicsFragment();
                }

                if (item.getItemId() == R.id.nav_festivals) {
                    selectedFragment = new FestivalsFragment();
                }

                if (item.getItemId() == R.id.nav_paintings) {
                    selectedFragment = new PaintingsFragment();
                }

                if (item.getItemId() == R.id.nav_opera) {
                    selectedFragment = new OperasFragment();
                }

                if (item.getItemId() == R.id.action_settings) {
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    return true;
                }

                // other conditions
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment).commit();
                }

                return true;
            }
        });

        // default display of ClassicsFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ClassicsFragment()).commit();
    }
}
