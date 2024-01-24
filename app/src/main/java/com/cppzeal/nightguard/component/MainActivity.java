package com.cppzeal.nightguard.component;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cppzeal.nightguard.R;
import com.cppzeal.nightguard.fg.AdderFragment;
import com.cppzeal.nightguard.fg.HomeFragment;
import com.cppzeal.nightguard.fg.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    String TAG = "sfasf";

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment adderFragment = new AdderFragment();
        Fragment settingFragment = new SettingFragment();
        HomeFragment homeFragment = new HomeFragment();


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                replaceFragment(homeFragment);
                return true;
            } else if (item.getItemId() == R.id.navigation_settings) {
                replaceFragment(settingFragment);
                return true;
            }else if (item.getItemId() == R.id.navigation_adder) {
                replaceFragment(adderFragment);
                return true;
            }
            return false;
        });
        bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);

        replaceFragment(homeFragment);
    }



    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame_container, fragment)
                .commit();
    }
}