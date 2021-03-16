package com.example.uts_31720;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView appLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appLogo = findViewById(R.id.AppLogo);
        appLogo.setBackgroundResource(R.drawable.applogo);
    }
}