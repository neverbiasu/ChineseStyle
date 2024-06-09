package com.example.chinesestyle;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.chinesestyle.models.PaintingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    selectedFragment = new OperaFragment();
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