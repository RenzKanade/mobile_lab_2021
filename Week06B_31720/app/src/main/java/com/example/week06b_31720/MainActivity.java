package com.example.week06b_31720;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable horseAnimation;
    ImageView horseImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horseImg = (ImageView)findViewById(R.id.imageHorse);
        horseImg.setBackgroundResource(R.drawable.horse_run);
        horseAnimation = (AnimationDrawable) horseImg.getBackground();
        horseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horseAnimation.start();
            }
        });
    }
}