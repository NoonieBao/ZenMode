package com.cppzeal.nightguard.component;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cppzeal.nightguard.R;
import com.cppzeal.nightguard.fg.HomeFragment;

public class ClearHandleActivity extends AppCompatActivity {


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clear_handle);


        HomeFragment homeFragment = new HomeFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.handle_del_frame_container, homeFragment)
                .commit();

    }

}
